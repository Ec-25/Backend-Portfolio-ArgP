package com.portfolioarg.ec.experience;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolioarg.ec.entity.Experience;

@Service
@Transactional
public class ServExperience {
    @Autowired
    RepoExperience repoExperience;

    public List<Experience> list(){
        return repoExperience.findAll();
    }

    public Optional<Experience> getOne(int id){
        return repoExperience.findById(id);
    }

    public Optional<Experience> getByExperience(String experience) {
        return repoExperience.findByExperience(experience);
    }

    public void save(Experience experience) {
        repoExperience.save(experience);
    }

    public void delete(int id) {
        repoExperience.deleteById(id);
    }

    public boolean existsById(int id){
        return repoExperience.existsById(id);
    }

    public boolean existsByExperience(String experience) {
        return repoExperience.existsByExperience(experience);
    }

}
