package com.jdc.assignment.model;

import java.util.List;

import com.jdc.assignment.domain.Course;

public interface CourseModel {

	List<Course> getAllCourses();
	void addCourse(Course course);
	Course findCourseById(int id);
}
