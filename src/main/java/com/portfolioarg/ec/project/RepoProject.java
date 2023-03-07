package com.portfolioarg.ec.project;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolioarg.ec.entity.Project;

@Repository
public interface RepoProject  extends JpaRepository<Project, Integer>{
    public Optional<Project> findByProject(String project);
    public boolean existsByProject(String project);
}
