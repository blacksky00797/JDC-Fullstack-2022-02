package com.jdc.assignment.model.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.model.CourseModel;

public class CourseModelImpl implements CourseModel {
	
	private static final String GET_ALL_COURSES_QUERY = "select * from course";
	private static final String INSERT_COURSE = "insert into course(name,fees,duration,description) values(?,?,?,?)";
	private static final String GET_ONE_COURSE = "select * from course where id=?";
	private DataSource dataSource;
	
	public CourseModelImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Course> getAllCourses() {
		var coursesList = new ArrayList<Course>();
		try ( var connection = dataSource.getConnection(); var statement = connection.prepareStatement(GET_ALL_COURSES_QUERY) ) {
			
			var resultSet = statement.executeQuery();
			
			while ( resultSet.next() ) {
				Course c = new Course();
				c.setId(resultSet.getInt("id"));
				c.setName(resultSet.getString("name"));
				c.setFees(resultSet.getInt("fees"));
				c.setDuration(resultSet.getInt("duration"));
				c.setDescription(resultSet.getString("description"));
				
				coursesList.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coursesList;
	}

	@Override
	public void addCourse(Course course) {
		try ( var connection = dataSource.getConnection(); var statement = connection.prepareStatement(INSERT_COURSE) ) {
			
			statement.setString(1, course.getName());
			statement.setInt(2, course.getFees());
			statement.setInt(3, course.getDuration());
			statement.setString(4, course.getDescription());
			statement.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Course findCourseById(int id) {
		Course course = null;
		try ( var connection = dataSource.getConnection(); var statement = connection.prepareStatement(GET_ONE_COURSE) ) {
			
			statement.setInt(1, id);
			var resultSet = statement.executeQuery();
			
			while ( resultSet.next() ) {
				course = new Course();
				course.setId(resultSet.getInt("id"));
				course.setName(resultSet.getString("name"));
				course.setFees(resultSet.getInt("fees"));
				course.setDuration(resultSet.getInt("duration"));
				course.setDescription(resultSet.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}
	
	

}
