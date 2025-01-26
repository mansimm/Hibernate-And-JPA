package com.example.JPAdemo;

import com.example.JPAdemo.entity.Course;
import com.example.JPAdemo.entity.Student;
import com.example.JPAdemo.jpaRepository.CourseRepository;
import com.example.JPAdemo.jpaRepository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class JPARepositoryTest {
    @Autowired
    StudentRepository studentRepo;
    @Autowired
    CourseRepository courseRepo;

    @Test
    public void testFindById(){
        Optional<Student> byId = studentRepo.findById(2001);
        Assertions.assertTrue(byId.isPresent());
    }

    @Test
    public void testCount(){
        long count = studentRepo.count();
        Assertions.assertEquals(4,count);
    }
    @Test
    public void testExists(){
        Assertions.assertTrue(studentRepo.existsById(2002));
    }

    @Test
    public void testAnd(){
        Optional<Student> mickey = studentRepo.findByIdAndName(2001, "Mickey");
        Assertions.assertTrue(mickey.isPresent());
    }

    @Test
    public void testOrderBy(){
        List<Student> result = studentRepo.findAllByOrderByNameDesc();
        System.out.println(result);
        Assertions.assertEquals("Shikamaru", result.get(0).getName());
    }

    @Test
    public void testQuery(){
        List<Student> result = studentRepo.findStudentWithoutCourse();
        System.out.println(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void testQueryWithNull(){
        List<Student> result = studentRepo.findStudentsWithoutPassport();
        System.out.println(result);
        Assertions.assertEquals(4, result.size());
    }

    @Test
    public void testNativeQuery(){
        List<Student> result = studentRepo.findStudentsWithNameStatsWithM();
        System.out.println(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testSort(){
        Sort sort = Sort.by("name").ascending().and(Sort.by("id").ascending());
        Iterable<Course> all = courseRepo.findAll(sort);
        for(Course c: all){
            System.out.println("==> course: "+c);
        }
        Assertions.assertEquals("Cassandra in 50 steps", all.iterator().next().getName());
    }

    @Test
    public void testPaging(){
        Sort sort = Sort.by("name").ascending().and(Sort.by("id").ascending());
        Pageable pageable = PageRequest.of(0, 3, sort);
        final Page<Course> pages = courseRepo.findAll(pageable);
        final List<Course> content = pages.getContent();
        for(Course c: content){
            System.out.println("===> first page: "+c);
        }

        Assertions.assertEquals(3, content.size());
        final int totalPages =  pages.getTotalPages();
        Assertions.assertEquals(2, totalPages);
        final long totalElements = pages.getTotalElements();
        Assertions.assertEquals(6, totalElements);

        final Pageable pageable1 = pages.nextPageable();//second page
        final Page<Course> page2 = courseRepo.findAll(pageable1);
        final List<Course> content1 = page2.getContent();
        for(Course c: content1){
            System.out.println("==> second page: "+ c);
        }


    }



}
