package com.portfolioarg.ec.person;

import java.util.List;

import com.portfolioarg.ec.entity.Person;

public interface InterPerson {
    //get list of persons
    public List<Person> getPerson();

    //save person object
    public void savePerson(Person person);

    //delete person object by id
    public void deletePerson(Long id);

    //find person object by id
    public Person findPerson(Long id);
}
