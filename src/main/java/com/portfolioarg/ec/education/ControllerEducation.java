package com.portfolioarg.ec.education;

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

import com.portfolioarg.ec.entity.Education;
import com.portfolioarg.ec.security.controller.Msg;

@RestController
@RequestMapping("/education")
@CrossOrigin(origins = { "https://estefano-portfolio.web.app", "http://localhost:4200" })
public class ControllerEducation {
    @Autowired
    ServEducation servEducation;

    @GetMapping("/list")
    public ResponseEntity<List<Education>> list() {
        List<Education> list = servEducation.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Education> getById(@PathVariable("id") int id){
        if(!servEducation.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Education education = servEducation.getOne(id).get();
        return new ResponseEntity<>(education, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducation dtoEducation) {
        if (StringUtils.isBlank(dtoEducation.getEducation())) {
            return new ResponseEntity<>(new Msg("Name is required"), HttpStatus.BAD_REQUEST);
        }
        if (servEducation.existsByEducation(dtoEducation.getEducation())) {
            return new ResponseEntity<>(new Msg("That education already exists"), HttpStatus.BAD_REQUEST);
        }
        Education education = new Education(dtoEducation.getEducation(), dtoEducation.getInstitution());

        servEducation.save(education);
        return new ResponseEntity<>(new Msg("Created education"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!servEducation.existsById(id)){
            return new ResponseEntity<>(new Msg("ID does not exist"), HttpStatus.NOT_FOUND);
        }
        servEducation.delete(id);
        return new ResponseEntity<>(new Msg("Deleted education"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducation dtoEducation){
        if(!servEducation.existsById(id)){
            return new ResponseEntity<>(new Msg("ID does not exist"), HttpStatus.NOT_FOUND);
        }
        if(servEducation.existsByEducation(dtoEducation.getEducation()) && servEducation.getByEducation(dtoEducation.getEducation()).get().getId() != id){
            return new ResponseEntity<>(new Msg("That name already exists"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoEducation.getEducation())){
            return new ResponseEntity<>(new Msg("Name is required"), HttpStatus.BAD_REQUEST);
        }
        Education education = servEducation.getOne(id).get();
        
        education.setEducation(dtoEducation.getEducation());
        education.setInstitution(dtoEducation.getInstitution());

        servEducation.save(education);
        return new ResponseEntity<>(new Msg("Updated education"), HttpStatus.OK);
    }
}