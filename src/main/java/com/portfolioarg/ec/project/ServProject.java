package com.portfolioarg.ec.project;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolioarg.ec.entity.Project;

@Service
@Transactional
public class ServProject {
    @Autowired
    RepoProject repoProject;

    public List<Project> list(){
        return repoProject.findAll();
    }

    public Optional<Project> getOne(int id){
        return repoProject.findById(id);
    }

    public Optional<Project> getByProject(String project) {
        return repoProject.findByProject(project);
    }

    public void save(Project project) {
        repoProject.save(project);
    }

    public void delete(int id) {
        repoProject.deleteById(id);
    }

    public boolean existsById(int id){
        return repoProject.existsById(id);
    }

    public boolean existsByProject(String project) {
        return repoProject.existsByProject(project);
    }

}
