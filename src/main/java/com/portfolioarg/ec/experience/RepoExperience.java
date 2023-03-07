package com.portfolioarg.ec.experience;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolioarg.ec.entity.Experience;

@Repository
public interface RepoExperience extends JpaRepository<Experience, Integer>{
    public Optional<Experience> findByExperience(String experience);
    public boolean existsByExperience(String experience);
}
