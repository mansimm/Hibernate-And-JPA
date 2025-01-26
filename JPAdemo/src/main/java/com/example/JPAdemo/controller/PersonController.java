package com.example.JPAdemo.controller;

import com.example.JPAdemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.JPAdemo.hibernateRepository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Integer id){
        return  new ResponseEntity<>(repository.findById(id),
                HttpStatus.OK);
    }
}
