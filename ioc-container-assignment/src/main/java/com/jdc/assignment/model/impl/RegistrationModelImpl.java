package com.jdc.assignment.model.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.domain.Registration;
import com.jdc.assignment.model.RegistrationModel;

public class RegistrationModelImpl implements RegistrationModel {
	
	private static final String GET_ALL_REGISTRATIONS_QUERY = """
				 select c.id courseId,c.name courseName,c.duration courseDuration,oc.id openClassId,oc.start_date openClassStartDate,oc.teacher openClassTeacher,
				 r.id regId,r.student regStudent,r.phone regPhone,r.email regEmail FROM course as c INNER JOIN open_class as oc ON c.id=oc.course_id 
				 INNER JOIN registration as r ON oc.id = r.open_class_id WHERE oc.id=?;
			""";
	private static final String NOT_PROVIDED = "- Not Provided -";
	private static final String REGISTER_STUDENT_QUERY = "insert into registration(open_class_id,student,phone,email) values(?,?,?,?)";
	private DataSource dataSource;
	
	public RegistrationModelImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Registration> getAllRegistrationsByOpenClassId(int openClassId) {
		var registrations = new ArrayList<Registration>();
		try ( var connection = dataSource.getConnection(); var statement = connection.prepareStatement(GET_ALL_REGISTRATIONS_QUERY) ) {
			
			statement.setInt(1, openClassId);
			var resultSet = statement.executeQuery();
			
			while ( resultSet.next() ) {
				Course c = new Course();
				c.setId(resultSet.getInt("courseId"));
				c.setName(resultSet.getString("courseName"));
				c.setFees(0);
				c.setDuration(resultSet.getInt("courseDuration"));
				c.setDescription(NOT_PROVIDED);
				
				OpenClass oc = new OpenClass();
				oc.setId(resultSet.getInt("openClassId"));
				oc.setCourse(c);
				oc.setStart_date(resultSet.getDate("openClassStartDate").toLocalDate());
				oc.setTeacher(resultSet.getString("openClassTeacher"));
				
				Registration r = new Registration();
				r.setId(resultSet.getInt("regId"));
				r.setOpenClass(oc);
				r.setStudentName(resultSet.getString("regStudent"));
				r.setStudentPhone(resultSet.getString("regPhone"));
				r.setStudentEmail(resultSet.getString("regEmail"));
				
				registrations.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return registrations;
	}

	@Override
	public void register(Registration registration) {
		try ( var connection = dataSource.getConnection(); var statement = connection.prepareStatement(REGISTER_STUDENT_QUERY) ) {
			
			statement.setInt(1, registration.getOpenClass().getId());
			statement.setString(2, registration.getStudentName());
			statement.setString(3, registration.getStudentPhone());
			statement.setString(4, registration.getStudentEmail());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
