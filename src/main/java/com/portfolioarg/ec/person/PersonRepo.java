package com.portfolioarg.ec.person;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolioarg.ec.entity.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    public Optional<Person> findByName(String name);
    public boolean existsByName(String name);
}
