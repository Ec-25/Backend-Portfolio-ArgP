package com.portfolioarg.ec.skill;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolioarg.ec.entity.Skill;

public interface RepoSkill extends JpaRepository<Skill, Integer>{
    Optional<Skill> findByName(String name);
    public boolean existsByName(String name);
}
