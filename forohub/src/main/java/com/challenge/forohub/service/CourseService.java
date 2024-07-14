package com.challenge.forohub.service;

import com.challenge.forohub.domain.course.Course;
import com.challenge.forohub.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Optional<Course> findCourseById(Long id){
        return courseRepository.findById(id);
    }
}
