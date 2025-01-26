package com.example.JPAdemo.jpaRepository;

import com.example.JPAdemo.entity.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends PagingAndSortingRepository<Course, Integer> {
}
