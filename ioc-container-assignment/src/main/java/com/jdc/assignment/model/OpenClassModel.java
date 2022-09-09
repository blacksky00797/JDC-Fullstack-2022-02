package com.jdc.assignment.model;

import java.util.List;

import com.jdc.assignment.domain.OpenClass;

public interface OpenClassModel {
	
	public List<OpenClass> findOpenClassesByCourseId(int courseId);
	public void create(OpenClass openClass);

}
