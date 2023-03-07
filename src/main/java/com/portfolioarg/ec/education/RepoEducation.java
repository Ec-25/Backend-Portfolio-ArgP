package com.portfolioarg.ec.education;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolioarg.ec.entity.Education;

@Repository
public interface RepoEducation extends JpaRepository<Education, Integer>{
    public Optional<Education> findByEducation(String education);
    public boolean existsByEducation(String education);
}
