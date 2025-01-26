package com.example.JPAdemo;

import com.example.JPAdemo.entity.Course;
import com.example.JPAdemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
@Transactional
public class QueryTests {

    @Autowired
    EntityManager em;


    @Test
    @DirtiesContext
    public void testNativeQuery(){
        Query query = em.createNativeQuery("Select * from Student where id = ?1");
        Query query1 = query.setParameter(1, 2001);
        List<Student> resultList = (List<Student>) query1.getResultList();
        Assertions.assertEquals(1, resultList.size());
    }

    @Test
    @DirtiesContext
    public void testNativeNamedQuery(){
        Query query = em.createNamedQuery("FindById", Student.class);
        Query query1 = query.setParameter(1, 2001);
        Student result = (Student) query1.getSingleResult();
        Assertions.assertEquals("Mickey", result.getName());
    }

    @Test
    @DirtiesContext
    public void testQuery(){
        Query query = em.createQuery("select s from Student s where s.id = :id", Student.class);
        Query query1 = query.setParameter("id", 2001);
        Student result = (Student) query1.getSingleResult();
        Assertions.assertEquals("Mickey", result.getName());
    }

    @Test
    @DirtiesContext
    public void testNamedQuery(){
        Query query = em.createNamedQuery("FindAll", Student.class);
        List<Student> result = query.getResultList();
        Assertions.assertEquals(3, result.size());
    }

    @Test
    @DirtiesContext
    public void testCriteriaQuery(){
        //1. create criteria builder to create criteria query to return expected object in return
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        //2. define root table which are involved in query
        Root<Student> from = query.from(Student.class);
        //3. define predicate using criteria builder
        //4. add predicate etc to criteria query
        //5. build the typed query using criteria builder and entity manager
        TypedQuery<Student> typeQuery = em.createQuery(query.select(from));
        List<Student> resultList = typeQuery.getResultList();
        for (Student student : resultList) {
            System.out.println("===> student: "+student);
        }
        Assertions.assertEquals(3, resultList.size());
    }

    @Test
    @DirtiesContext
    public void testCriteriaQueryLike(){
        //1. create criteria builder to create criteria query to return expected object in return
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        //2. define root table which are involved in query
        Root<Course> from = query.from(Course.class);
        //3. define predicate using criteria builder
        Predicate nameLike = criteriaBuilder.like(from.get("name"), "% 100 steps");
        //4. add predicate etc to criteria query
        query.where(nameLike);
        //5. build the typed query using criteria builder and entity manager
        TypedQuery<Course> typeQuery = em.createQuery(query.select(from));
        List<Course> resultList = typeQuery.getResultList();
        for (Course course : resultList) {
            System.out.println("===> Course: "+course);
        }
        Assertions.assertEquals(4, resultList.size());
    }
    @Test
    @DirtiesContext
    public void testCriteriaQueryCoursesWithoutStudent(){
        //1. create criteria builder to create criteria query to return expected object in return
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        //2. define root table which are involved in query
        Root<Course> from = query.from(Course.class);
        //3. define predicate using criteria builder
        Predicate emptyStudents = criteriaBuilder.isEmpty(from.get("students"));
        //4. add predicate etc to criteria query
        query.where(emptyStudents);
        //5. build the typed query using criteria builder and entity manager
        TypedQuery<Course> typeQuery = em.createQuery(query.select(from));
        List<Course> resultList = typeQuery.getResultList();
        for (Course course : resultList) {
            System.out.println("===> Course: "+course);
        }
        Assertions.assertEquals(4, resultList.size());
    }

    @Test
    @DirtiesContext
    public void testCriteriaQueryJoins(){
        //1. create criteria builder to create criteria query to return expected object in return
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        //2. define root table which are involved in query
        Root<Course> from = query.from(Course.class);
        //3. define join on root
        Join<Object, Object> students = from.join("students");

        //4. build the typed query using criteria builder and entity manager
        TypedQuery<Course> typeQuery = em.createQuery(query.select(from));
        List<Course> resultList = typeQuery.getResultList();
        for (Course record : resultList) {
            System.out.println("===> Record: "+record);
        }
        Assertions.assertEquals(2, resultList.size());
    }

    @Test
    @DirtiesContext
    public void testCriteriaQueryLeftJoin(){
        //1. create criteria builder to create criteria query to return expected object in return
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        //2. define root table which are involved in query
        Root<Course> from = query.from(Course.class);
        //3. define join on root
        Join<Object, Object> students = from.join("students", JoinType.LEFT);

        //4. build the typed query using criteria builder and entity manager
        TypedQuery<Course> typeQuery = em.createQuery(query.select(from));
        List<Course> resultList = typeQuery.getResultList();
        for (Course record : resultList) {
            System.out.println("===> Record: "+record);
        }
        Assertions.assertEquals(6, resultList.size());
    }
}
