package com.portfolioarg.ec.project;

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

import com.portfolioarg.ec.entity.Project;
import com.portfolioarg.ec.security.controller.Msg;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = { "https://estefano-portfolio.web.app", "http://localhost:4200" })
public class ControllerProject {
    @Autowired
    ServProject servProject;

    @GetMapping("/list")
    public ResponseEntity<List<Project>> list() {
        List<Project> list = servProject.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Project> getById(@PathVariable("id") int id){
        if(!servProject.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Project project = servProject.getOne(id).get();
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProject dtoProject) {
        if (StringUtils.isBlank(dtoProject.getProject())) {
            return new ResponseEntity<>(new Msg("Name is required"), HttpStatus.BAD_REQUEST);
        }
        if (servProject.existsByProject(dtoProject.getProject())) {
            return new ResponseEntity<>(new Msg("That project already exists"), HttpStatus.BAD_REQUEST);
        }
        Project project = new Project(dtoProject.getIcon(), dtoProject.getProject(), dtoProject.getText(), dtoProject.getLink_gh(), dtoProject.getLink_page());

        servProject.save(project);
        return new ResponseEntity<>(new Msg("Created project"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!servProject.existsById(id)){
            return new ResponseEntity<>(new Msg("ID does not exist"), HttpStatus.NOT_FOUND);
        }
        servProject.delete(id);
        return new ResponseEntity<>(new Msg("Deleted project"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProject dtoProject){
        if(!servProject.existsById(id)){
            return new ResponseEntity<>(new Msg("ID does not exist"), HttpStatus.NOT_FOUND);
        }
        if(servProject.existsByProject(dtoProject.getProject()) && servProject.getByProject(dtoProject.getProject()).get().getId() != id){
            return new ResponseEntity<>(new Msg("That name already exists"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoProject.getProject())){
            return new ResponseEntity<>(new Msg("Name is required"), HttpStatus.BAD_REQUEST);
        }
        Project project = servProject.getOne(id).get();
        
        project.setIcon(dtoProject.getIcon());
        project.setProject(dtoProject.getProject());
        project.setText(dtoProject.getText());
        project.setLink_gh(dtoProject.getLink_gh());
        project.setLink_page(dtoProject.getLink_page());

        servProject.save(project);
        return new ResponseEntity<>(new Msg("Updated project"), HttpStatus.OK);
    }
}