package com.example.JPAdemo.jpaRepository;

import com.example.JPAdemo.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Cacheable;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path="students")//exposed this repo methods as endpoints
public interface StudentRepository extends CrudRepository<Student, Integer> {
    Optional<Student> findByIdAndName(Integer id, String name);
    List<Student> findAllByOrderByName();
    List<Student> findAllByOrderByNameDesc();

    @Query("select s from Student s where s.courses is empty")
    List<Student> findStudentWithoutCourse();

    @Query(value = "select s from Student s where s.passport is null", nativeQuery = false)
    List<Student> findStudentsWithoutPassport();

    @Query(value = "select * from student where name like 'M%'", nativeQuery = true)
    List<Student> findStudentsWithNameStatsWithM();
}
