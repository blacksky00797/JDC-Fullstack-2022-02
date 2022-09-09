package com.jdc.assignment.model.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.model.OpenClassModel;

// Another Query
/*
 select oc.id id,oc.start_date startDate,oc.teacher teacher,c.id courseId,c.name courseName,c.fees courseFees,c.duration courseDuration,c.description courseDescription 
 FROM open_class as oc INNER JOIN course as c ON oc.course_id=c.id;
 */

public class OpenClassModelImpl implements OpenClassModel {
	
	private static final String GET_ALL_CLASSES_QUERY = """
			select oc.id id,oc.start_date startDate,oc.teacher teacher,c.id courseId,c.name courseName,c.fees courseFees,c.duration courseDuration,c.description courseDescription 
			FROM open_class as oc INNER JOIN course as c ON oc.course_id=c.id where c.id=?;
			""";
	
	private DataSource dataSource;

	public OpenClassModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<OpenClass> findOpenClassesByCourseId(int courseId) {
		var openClassesList = new ArrayList<OpenClass>();
		try ( var connection = dataSource.getConnection(); var statement = connection.prepareStatement(GET_ALL_CLASSES_QUERY) ) {
			
			statement.setInt(1, courseId);
			var resultSet = statement.executeQuery();
			
			while ( resultSet.next() ) {
				Course c = new Course();
				c.setId(resultSet.getInt("courseId"));
				c.setName(resultSet.getString("courseName"));
				c.setFees(resultSet.getInt("courseFees"));
				c.setDuration(resultSet.getInt("courseDuration"));
				c.setDescription(resultSet.getString("courseDescription"));
				
				OpenClass oc = new OpenClass();
				oc.setId(resultSet.getInt("id"));
				oc.setCourse(c);
				oc.setStart_date(resultSet.getDate("startDate").toLocalDate());
				oc.setTeacher(resultSet.getString("teacher"));
				
				openClassesList.add(oc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return openClassesList;
	}

	@Override
	public void create(OpenClass openClass) {
		// TODO Auto-generated method stub
		
	}
	
	

}
