package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.CourseInstance;

public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
	List<CourseInstance> findByYearAndSemester(int year, int semester);

	Optional<CourseInstance> findByCourse_CourseIdAndYearAndSemester(String cid, int year, int sem);
}
