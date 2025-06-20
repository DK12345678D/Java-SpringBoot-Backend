package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Course;
import com.app.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    
//    public Course createCourse(Course course) {
//        
//        for (Course prereq : course.getPrerequisites()) {
//            Optional<Course> existingPrereq = courseRepository.findByCourseId(prereq.getCourseId());
//            if (!existingPrereq.isPresent()) {
//                throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "Invalid prerequisite: " + prereq.getCourseId()
//                );
//            } else {
//                prereq.setId(existingPrereq.get().getId());
//            }
//        }
//
//        return courseRepository.save(course);
//    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

//    public Course getCourseById(String courseId) {
//        return courseRepository.findByCourseId(courseId).orElse(null);
//    }
//
//    public void deleteCourse(String courseId) {
//        Course course = courseRepository.findByCourseId(courseId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
//
//        boolean isUsedAsPrereq = courseRepository.existsByPrerequisitesContaining(course);
//        if (isUsedAsPrereq) {
//            throw new ResponseStatusException(
//                HttpStatus.CONFLICT,
//                "Course is a prerequisite for another course and cannot be deleted."
//            );
//        }
//
//        courseRepository.delete(course);
//    }
}
