package com.portfolioarg.ec.person;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolioarg.ec.entity.Person;
import com.portfolioarg.ec.security.controller.Msg;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = { "https://estefano-portfolio.web.app", "http://localhost:4200" })
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/all")
    public ResponseEntity<List<Person>> list(){
        List<Person> list = personService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id")int id){
        if(!personService.existsById(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Person person = personService.getOne(id).get();
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
    
    /*
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!personService.existsById(id)){
            return new ResponseEntity<>(new Msg("ID does not exist"), HttpStatus.NOT_FOUND);
        }
        personService.delete(id);
        return new ResponseEntity<>(new Msg("Deleted person"), HttpStatus.OK);
    }
    */
    /*
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoPerson dtoPerson){
        if(StringUtils.isBlank(dtoPerson.getName())){
            return new ResponseEntity<>(new Msg("Name is required"), HttpStatus.BAD_REQUEST);
        }
        if(personService.existsByName(dtoPerson.getName())){
            return new ResponseEntity<>(new Msg("That name already exists"), HttpStatus.BAD_REQUEST);
        }
        
        Person person = new Person(dtoPerson.getName(), dtoPerson.getTitle(), dtoPerson.getLabels(), dtoPerson.getPhoto(), dtoPerson.getAbout());
        personService.save(person);
        return new ResponseEntity<>(new Msg("Created person"), HttpStatus.OK);
    }
    */

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoPerson dtoPerson){
        if(!personService.existsById(id)){
            return new ResponseEntity<>(new Msg("ID does not exist"), HttpStatus.NOT_FOUND);
        }
        if(personService.existsByName(dtoPerson.getName()) && personService.getByName(dtoPerson.getName()).get().getId() != id){
            return new ResponseEntity<>(new Msg("That name already exists"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoPerson.getName())){
            return new ResponseEntity<>(new Msg("Name is required"), HttpStatus.BAD_REQUEST);
        }
        
        Person person = personService.getOne(id).get();
        
        person.setName(dtoPerson.getName());
        person.setTitle(dtoPerson.getTitle());
        person.setLabels(dtoPerson.getLabels());
        person.setPhoto(dtoPerson.getPhoto());
        person.setAbout(dtoPerson.getAbout());

        personService.save(person);
        
        return new ResponseEntity<>(new Msg("Updated person"), HttpStatus.OK);
    }
}