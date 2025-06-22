package com.app.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String courseId; // e.g. "CS209"
	private String title;
	private String description;

	@ManyToMany
	@JoinTable(name = "course_prerequisites", 
	joinColumns = @JoinColumn(name = "course_id"), 
	inverseJoinColumns = @JoinColumn(name = "prereq_id"))
	private Set<Course> prerequisites = new HashSet<>();
}
