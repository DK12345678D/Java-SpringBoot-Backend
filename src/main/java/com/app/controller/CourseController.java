package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Course;
import com.app.repository.CourseRepository;
@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {
    @Autowired private CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        // Validate prerequisites
        for (Course prereq : course.getPrerequisites()) {
            if (!courseRepository.findById(prereq.getId()).isPresent()) {
                return ResponseEntity.badRequest().body("Invalid prerequisite ID: " + prereq.getId());
            }
        }
        return ResponseEntity.ok(courseRepository.save(course));
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getCourseByCode(@PathVariable String code) {
        return courseRepository.findByCourseCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteCourse(@PathVariable String code) {
        Optional<Course> courseOpt = courseRepository.findByCourseCode(code);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            boolean isUsedAsPrerequisite = courseRepository.findAll().stream()
                .anyMatch(c -> c.getPrerequisites().contains(course));
            if (isUsedAsPrerequisite) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Cannot delete course. It is used as a prerequisite.");
            }
            courseRepository.delete(course);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
