package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.CourseInstance;
import com.app.repository.CourseInstanceRepository;

@Service
public class CourseInstanceService {

	@Autowired
	private CourseInstanceRepository instanceRepository;

	public CourseInstance createInstance(CourseInstance instance) {
		return instanceRepository.save(instance);
	}

	public List<CourseInstance> getInstances(int year, int semester) {
		return instanceRepository.findByYearAndSemester(year, semester);
	}
//
//	public CourseInstance getInstanceByCourseId(int year, int semester, String courseId) {
//		return instanceRepository.findByYearAndSemesterAndCourse_CourseId(year, semester, courseId).orElse(null);
//	}
//
//	public boolean deleteByCourseIdAndYearSemester(String courseId, int year, int semester) {
//		Optional<CourseInstance> instanceOpt = instanceRepository.findByYearAndSemesterAndCourse_CourseId(year,
//				semester, courseId);
//		if (instanceOpt.isPresent()) {
//			instanceRepository.delete(instanceOpt.get());
//			return true;
//		}
//		return false;
//	}
}
