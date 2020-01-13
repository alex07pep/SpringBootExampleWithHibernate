package com.exercise.tema1.service;

import com.exercise.tema1.domain.Course;
import com.exercise.tema1.domain.Employee;
import com.exercise.tema1.repository.CourseRepository;
import com.exercise.tema1.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course addCourse(String name, int hours, int value, boolean diploma, String year, Employee employee) {
        Course newCourse = new Course();
        newCourse.setName(name);
        newCourse.setHours(hours);
        newCourse.setValue(value);
        newCourse.setDiploma(diploma);
        newCourse.setYear(year);
        newCourse.setEmployee(employee);
        courseRepository.save(newCourse);
        return newCourse;
    }

    public Optional<Course> findCourse(Long id){
        return courseRepository.findById(id);
    }

    public void deleteCourse(Long id) {
        courseRepository.findById(id).ifPresent(course -> {
            courseRepository.delete(course);
        });
    }

    public Course updateCourse(Course course, String name, int hours, int value, boolean diploma, String year, Employee employee) {
        course.setName(name);
        course.setHours(hours);
        course.setValue(value);
        course.setDiploma(diploma);
        course.setYear(year);
        course.setEmployee(employee);
        courseRepository.save(course);
        return course;
    }
}