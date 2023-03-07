package com.portfolioarg.ec.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolioarg.ec.security.entity.User;
import com.portfolioarg.ec.security.repository.iUserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    iUserRepository iuserRepository;

    public Optional<User>getByUsername(String username) {
        return iuserRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return iuserRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return iuserRepository.existsByEmail(email);
    }

    public void save(User user) {
        iuserRepository.save(user);
    }
}
