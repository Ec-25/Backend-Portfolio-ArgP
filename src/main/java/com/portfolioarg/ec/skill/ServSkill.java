package com.portfolioarg.ec.skill;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolioarg.ec.entity.Skill;

@Transactional
@Service
public class ServSkill {
    @Autowired
    RepoSkill repoSkill;

    public List<Skill> list(){
        return repoSkill.findAll();
    }
    
    public Optional<Skill> getOne(int id){
        return repoSkill.findById(id);
    }

    public Optional<Skill> getByName(String name){
        return repoSkill.findByName(name);
    }
    
    public void save(Skill skill){
        repoSkill.save(skill);
    }
    
    public void delete(int id){
        repoSkill.deleteById(id);
    }
    
    public boolean existsById(int id){
        return repoSkill.existsById(id);
    }
    
    public boolean existsByName(String name){
        return repoSkill.existsByName(name);
    }

}
