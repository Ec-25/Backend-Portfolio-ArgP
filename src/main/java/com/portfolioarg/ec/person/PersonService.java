package com.portfolioarg.ec.person;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolioarg.ec.entity.Person;

@Service
@Transactional
public class PersonService {
    @Autowired
    PersonRepo personRepo;

    public List<Person> list(){
        return personRepo.findAll();
    }
    
    public Optional<Person> getOne(int id){
        return personRepo.findById(id);
    }
    
    public Optional<Person> getByName(String name){
        return personRepo.findByName(name);
    }
    
    public void save(Person person){
        personRepo.save(person);
    }
    
    public void delete(int id){
        personRepo.deleteById(id);
    }
    
    public boolean existsById(int id){
        return personRepo.existsById(id);
    }
    
    public boolean existsByName(String name){
        return personRepo.existsByName(name);
    }
}
