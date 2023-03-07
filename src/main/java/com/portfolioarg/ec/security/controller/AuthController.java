package com.portfolioarg.ec.security.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolioarg.ec.security.dto.JwtDto;
import com.portfolioarg.ec.security.dto.LoginUser;
import com.portfolioarg.ec.security.dto.CreateUser;
import com.portfolioarg.ec.security.entity.Rol;
import com.portfolioarg.ec.security.entity.User;
import com.portfolioarg.ec.security.enums.RolName;
import com.portfolioarg.ec.security.service.RolService;
import com.portfolioarg.ec.security.service.UserService;
import com.portfolioarg.ec.security.jwt.JwtProvider;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = { "https://fronted-portfolioarg.web.app", "http://localhost:4200" })
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CreateUser newUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Msg("Wrong fields or invalid email"), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByUsername(newUser.getName())) {
            return new ResponseEntity<>(new Msg("That username already exists"), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByEmail(newUser.getEmail())) {
            return new ResponseEntity<>(new Msg("That email already exists"), HttpStatus.BAD_REQUEST);
        }
        User user = new User(newUser.getName(), newUser.getUsername(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());

        if(newUser.getRoles().contains("admin")) {
            roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        }
        user.setRoles(roles);
        userService.save(user);

        return new ResponseEntity<>(new Msg("Saved user"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}
