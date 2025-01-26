package com.example.JPAdemo;

import com.example.JPAdemo.entity.Student;
import com.example.JPAdemo.jpaRepository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class FirstLevelCacheTest {
    @Autowired
    StudentRepository repo;
    @Test
    @Transactional//within the boundary of transactional FLC is already activated by default
    public void testFLC(){
        final Optional<Student> byId = repo.findById(2001);
        System.out.println("==> course retrived first time: "+byId);

        final Optional<Student> byId2 = repo.findById(2001);
        System.out.println("==> course retrived again: "+byId2);

        //select query will run only once data will be stored in FLC cache and used later
    }
}
