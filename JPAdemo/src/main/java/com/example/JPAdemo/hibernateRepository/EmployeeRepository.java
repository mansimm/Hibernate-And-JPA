package com.example.JPAdemo.hibernateRepository;

import com.example.JPAdemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {
    @Autowired
    EntityManager em;
    public void insertEmployee(Employee employee){
        em.persist(employee);
    }

    public void findAll(){
        final Query selectEmployees = em.createQuery("select e from Employee e", Employee.class);
        final List<Employee> resultList = selectEmployees.getResultList();
        for(Employee e:resultList){
            System.out.println("===> Employee: "+e);
        }
    }

    public void findAllPartTimeEmployee(){
        final Query selectEmployees = em.createQuery("select e from PartTimeEmployee e", Employee.class);
        final List<Employee> resultList = selectEmployees.getResultList();
        for(Employee e:resultList){
            System.out.println("===> Employee: "+e);
        }
    }


    public void findAllFullTimeEmployee(){
        final Query selectEmployees = em.createQuery("select e from FullTimeEmployee e", Employee.class);
        final List<Employee> resultList = selectEmployees.getResultList();
        for(Employee e:resultList){
            System.out.println("===> Employee: "+e);
        }
    }
}
