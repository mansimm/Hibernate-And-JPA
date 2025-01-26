package com.example.JPAdemo.hibernateRepository;

import com.example.JPAdemo.entity.Person;
import com.example.JPAdemo.exception.PesronNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PersonRepository {
    @Autowired
    EntityManager entityManager;

    public Person findById(Integer id){
        return entityManager.find(Person.class, id);
    }

    public Person save(Person person){
        entityManager.persist(person);
        return findById(person.getId());
    }

    public Person update(Person person){
        Person p1 = findById(person.getId());
        if(p1 == null){
            throw new PesronNotFoundException("Person not found with id "+person.getId());
        }
        entityManager.merge(person);
        return findById(person.getId());
    }

    public void remove(Person person){
        Person p1 = findById(person.getId());
        if(p1 == null){
            throw new PesronNotFoundException("Person not found with id "+person.getId());
        }
        entityManager.remove(person);
    }
}
