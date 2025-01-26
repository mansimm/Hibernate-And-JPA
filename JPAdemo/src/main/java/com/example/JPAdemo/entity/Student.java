package com.example.JPAdemo.entity;

import jakarta.persistence.*;
//import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@javax.persistence.Cacheable//cache student data accross multiple transactions in second level cache
@NamedQueries({
        @NamedQuery(name="FindByStudentName", query = "select s from Student s where s.name=?1"),
        @NamedQuery(name="FindAll", query="select s from Student s")
})
@NamedNativeQuery(name="FindById", query="Select * from Student where id = ?1", resultClass = Student.class)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.LAZY)
    private Passport passport;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    public Student(String name) {
        this.name = name;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passport=" + passport +
                '}';
    }
}
