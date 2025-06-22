package com.app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.server.ResponseStatusException;

import com.app.dto.CourseDto;
import com.app.model.Course;
import com.app.repository.CourseRepository;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {
	@Autowired
	private CourseRepository repo;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody CourseDto dto) {
		Set<Course> prereqs = new HashSet<>();
		for (String pid : dto.prerequisiteIds) {
			Course p = repo.findByCourseId(pid).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid prerequisite: " + pid));
			prereqs.add(p);
		}
		if (repo.existsByCourseId(dto.courseId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course ID exists");
		}
		Course c = new Course(null, dto.courseId, dto.title, dto.description, prereqs);
		c.setPrerequisites(prereqs);
		repo.save(c);
		return ResponseEntity.ok(c);
	}

	@GetMapping
	public List<Course> all() {
		return repo.findAll();
	}

	@GetMapping("/{cid}")
	public Course one(@PathVariable String cid) {
		return repo.findByCourseId(cid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{cid}")
	public ResponseEntity<?> delete(@PathVariable String cid) {
		Course c = repo.findByCourseId(cid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		boolean inUse = repo.findAll().stream().anyMatch(x -> x.getPrerequisites().contains(c));
		if (inUse) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot delete; course is a prerequisite");
		}
		repo.delete(c);
		return ResponseEntity.ok().build();
	}
}
