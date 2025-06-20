package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	//List<Course> findByCourseIdIn(List<String> courseIds);

	Optional<Course> findByCourseCode(String code);
	//boolean existsByPrerequisitesContaining(Course course);

	

}
