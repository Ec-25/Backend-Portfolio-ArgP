package com.portfolioarg.ec.skill;

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

import com.portfolioarg.ec.entity.Skill;
import com.portfolioarg.ec.security.controller.Msg;

@RestController
@RequestMapping("/skill")
@CrossOrigin(origins = { "https://estefano-portfolio.web.app", "http://localhost:4200" })
public class ControllerSkill {
    @Autowired
    ServSkill servSkill;

    @GetMapping("/list")
    public ResponseEntity<List<Skill>> list() {
        List<Skill> list = servSkill.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skill> getById(@PathVariable("id") int id) {
        if (!servSkill.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Skill skill = servSkill.getOne(id).get();
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoSkill dtoSkill) {
        if (StringUtils.isBlank(dtoSkill.getName())) {
            return new ResponseEntity<>(new Msg("Name is required"), HttpStatus.BAD_REQUEST);
        }
        if (servSkill.existsByName(dtoSkill.getName())) {
            return new ResponseEntity<>(new Msg("That Skill already exists"), HttpStatus.BAD_REQUEST);
        }
        Skill skill = new Skill(dtoSkill.getName(), dtoSkill.getPercentage());

        servSkill.save(skill);
        return new ResponseEntity<>(new Msg("Created skill"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!servSkill.existsById(id)){
            return new ResponseEntity<>(new Msg("ID does not exist"), HttpStatus.NOT_FOUND);
        }
        servSkill.delete(id);
        return new ResponseEntity<>(new Msg("Deleted skill"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoSkill dtoSkill){
        if(!servSkill.existsById(id)){
            return new ResponseEntity<>(new Msg("ID does not exist"), HttpStatus.NOT_FOUND);
        }
        if(servSkill.existsByName(dtoSkill.getName()) && servSkill.getByName(dtoSkill.getName()).get().getId() != id){
            return new ResponseEntity<>(new Msg("That name already exists"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoSkill.getName())){
            return new ResponseEntity<>(new Msg("Name is required"), HttpStatus.BAD_REQUEST);
        }
        Skill skill = servSkill.getOne(id).get();
        
        skill.setName(dtoSkill.getName());
        skill.setPercentage(dtoSkill.getPercentage());

        servSkill.save(skill);
        return new ResponseEntity<>(new Msg("Updated skill"), HttpStatus.OK);
    }
}
