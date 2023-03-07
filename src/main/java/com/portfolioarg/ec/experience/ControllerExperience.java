package com.portfolioarg.ec.experience;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolioarg.ec.entity.Experience;
import com.portfolioarg.ec.security.controller.Msg;

@RestController
@RequestMapping("/explab")
@CrossOrigin(origins = { "https://estefano-portfolio.web.app", "http://localhost:4200" })
public class ControllerExperience {
    @Autowired
    ServExperience servExperience;

    @GetMapping("/list")
    public ResponseEntity<List<Experience>> list() {
        List<Experience> list = servExperience.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experience> getById(@PathVariable("id") int id){
        if(!servExperience.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Experience experience = servExperience.getOne(id).get();
        return new ResponseEntity<>(experience, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperience dtoExperience) {
        if (StringUtils.isBlank(dtoExperience.getExperience())) {
            return new ResponseEntity<>(new Msg("Name is required"), HttpStatus.BAD_REQUEST);
        }
        if (servExperience.existsByExperience(dtoExperience.getExperience())) {
            return new ResponseEntity<>(new Msg("That experience already exists"), HttpStatus.BAD_REQUEST);
        }
        Experience experience = new Experience(dtoExperience.getExperience(), dtoExperience.getDescription());

        servExperience.save(experience);
        return new ResponseEntity<>(new Msg("Created experience"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!servExperience.existsById(id)){
            return new ResponseEntity<>(new Msg("ID does not exist"), HttpStatus.NOT_FOUND);
        }
        servExperience.delete(id);
        return new ResponseEntity<>(new Msg("Deleted experience"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoExperience dtoExperience){
        if(!servExperience.existsById(id)){
            return new ResponseEntity<>(new Msg("ID does not exist"), HttpStatus.NOT_FOUND);
        }
        if(servExperience.existsByExperience(dtoExperience.getExperience()) && servExperience.getByExperience(dtoExperience.getExperience()).get().getId() != id){
            return new ResponseEntity<>(new Msg("That name already exists"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoExperience.getExperience())){
            return new ResponseEntity<>(new Msg("Name is required"), HttpStatus.BAD_REQUEST);
        }
        Experience experience = servExperience.getOne(id).get();
        
        experience.setExperience(dtoExperience.getExperience());
        experience.setDescription(dtoExperience.getDescription());

        servExperience.save(experience);
        return new ResponseEntity<>(new Msg("Updated experience"), HttpStatus.OK);
    }
}