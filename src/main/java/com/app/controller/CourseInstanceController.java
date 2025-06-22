package com.app.controller;

import java.util.List;

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

import com.app.dto.InstanceDto;
import com.app.model.Course;
import com.app.model.CourseInstance;
import com.app.repository.CourseInstanceRepository;
import com.app.repository.CourseRepository;

@RestController
@RequestMapping("/api/instances")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseInstanceController {
	@Autowired
	private CourseInstanceRepository insRepo;
	@Autowired
	private CourseRepository courseRepo;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody InstanceDto dto) {
		Course c = courseRepo.findByCourseId(dto.courseId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid course ID"));
		CourseInstance ci = new CourseInstance(null, c, dto.year, dto.semester);
		insRepo.save(ci);
		return ResponseEntity.ok(ci);
	}

	@GetMapping("/{year}/{sem}")
	public List<CourseInstance> list(@PathVariable int year, @PathVariable int sem) {
		return insRepo.findByYearAndSemester(year, sem);
	}

	@GetMapping("/{year}/{sem}/{cid}")
	public CourseInstance one(@PathVariable int year, @PathVariable int sem, @PathVariable String cid) {
		return insRepo.findByCourse_CourseIdAndYearAndSemester(cid, year, sem)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{year}/{sem}/{cid}")
	public ResponseEntity<?> delete(@PathVariable int year, @PathVariable int sem, @PathVariable String cid) {
		CourseInstance inst = insRepo.findByCourse_CourseIdAndYearAndSemester(cid, year, sem)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		insRepo.delete(inst);
		return ResponseEntity.ok().build();
	}
}
