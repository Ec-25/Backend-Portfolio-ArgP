package com.portfolioarg.ec.education;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolioarg.ec.entity.Education;

@Service
@Transactional
public class ServEducation {
    @Autowired
    RepoEducation repoEducation;

    public List<Education> list() {
        return repoEducation.findAll();
    }

    public Optional<Education> getOne(int id) {
        return repoEducation.findById(id);
    }

    public Optional<Education> getByEducation(String education) {
        return repoEducation.findByEducation(education);
    }

    public void save(Education education) {
        repoEducation.save(education);
    }

    public void delete(int id) {
        repoEducation.deleteById(id);
    }

    public boolean existsById(int id) {
        return repoEducation.existsById(id);
    }

    public boolean existsByEducation(String education) {
        return repoEducation.existsByEducation(education);
    }

}
