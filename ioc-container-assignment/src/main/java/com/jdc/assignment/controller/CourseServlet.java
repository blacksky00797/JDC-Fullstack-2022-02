package com.jdc.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.model.CourseModel;

@WebServlet (urlPatterns = {
		"/",
		"/course",
		"/course-edit"
})
public class CourseServlet extends AbstractBeanFactoryServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		String page = switch (req.getServletPath()) {
		
			case "/course-edit"-> "/course-edit.jsp";
			
			default -> {
				var courseModel = getBean("courseModel", CourseModel.class);
				getServletContext().setAttribute("courses", courseModel.getAllCourses());
				yield "/index.jsp";
			}
		};
		getServletContext().getRequestDispatcher(page).forward(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		var courseModel = getBean("courseModel", CourseModel.class);
		Course course = new Course();
		course.setName(req.getParameter("name"));
		course.setFees(Integer.parseInt(req.getParameter("fees")));
		course.setDuration(Integer.parseInt(req.getParameter("duration")));
		course.setDescription(req.getParameter("description"));
		courseModel.addCourse(course);
		
		res.sendRedirect("/");
	}
	
}
