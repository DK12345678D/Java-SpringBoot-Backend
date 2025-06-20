package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CourseInstance;
import com.app.repository.CourseInstanceRepository;
import com.app.repository.CourseRepository;

@RestController
@RequestMapping("/api/instances")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseInstanceController {
    @Autowired private CourseInstanceRepository instanceRepo;
    @Autowired private CourseRepository courseRepo;

    @PostMapping
    public ResponseEntity<?> createInstance(@RequestBody CourseInstance instance) {
        if (!courseRepo.existsById(instance.getCourse().getId())) {
            return ResponseEntity.badRequest().body("Invalid course ID");
        }
        instance.setUniqueInstanceId(instance.getYear() + "-" + instance.getSemester() + "-" + instance.getCourse().getCourseCode());
        return ResponseEntity.ok(instanceRepo.save(instance));
    }

    @GetMapping("/{year}/{semester}")
    public List<CourseInstance> getInstances(@PathVariable int year, @PathVariable int semester) {
        return instanceRepo.findByYearAndSemester(year, semester);
    }

    @GetMapping("/{year}/{semester}/{code}")
    public ResponseEntity<?> getInstanceDetail(@PathVariable int year, @PathVariable int semester, @PathVariable String code) {
        return instanceRepo.findByCourse_CourseCodeAndYearAndSemester(code, year, semester)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{year}/{semester}/{code}")
    public ResponseEntity<?> deleteInstance(@PathVariable int year, @PathVariable int semester, @PathVariable String code) {
        Optional<CourseInstance> instance = instanceRepo.findByCourse_CourseCodeAndYearAndSemester(code, year, semester);
        if (instance.isPresent()) {
            instanceRepo.delete(instance.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
