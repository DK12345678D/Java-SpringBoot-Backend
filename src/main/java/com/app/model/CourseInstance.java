package com.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity

public class CourseInstance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int year;
	private int semester;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	@Column(unique = true)
	private String uniqueInstanceId; // e.g., "2025-2-COURSEID"
}
