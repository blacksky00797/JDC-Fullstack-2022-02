package com.jdc.assignment.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.model.CourseModel;
import com.jdc.assignment.model.OpenClassModel;

@WebServlet(urlPatterns = {
		"/classes",
		"/classes-edit"
})
public class OpenClassServlet extends AbstractBeanFactoryServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = switch (req.getServletPath())	{
			case "/classes-edit" -> {
				var courseModel = getBean("courseModel", CourseModel.class);
				req.setAttribute("course", courseModel.findCourseById(Integer.parseInt(req.getParameter("courseId"))));
				yield "/classes-edit.jsp";
			}
			default -> {
				var openClassesModel = getBean("openClassModel", OpenClassModel.class);
				var courseModel = getBean("courseModel", CourseModel.class);
				int courseId = Integer.parseInt(req.getParameter("courseId"));
				getServletContext().setAttribute("openClasses", openClassesModel.findOpenClassesByCourseId(courseId));
				getServletContext().setAttribute("course", courseModel.findCourseById(courseId));
				yield "/classes.jsp";
			}
		};
		
		getServletContext().getRequestDispatcher(page).forward(req, resp);
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var courseModel = getBean("courseModel", CourseModel.class);
		var openClassesModel = getBean("openClassModel", OpenClassModel.class);		
		OpenClass openClass = new OpenClass();
		Course course = courseModel.findCourseById(Integer.parseInt(req.getParameter("courseId")));
		openClass.setCourse(course);
		openClass.setStart_date(LocalDate.parse(req.getParameter("startDate")));
		openClass.setTeacher(req.getParameter("teacher"));
		openClassesModel.create(openClass);
		
		resp.sendRedirect("/classes?courseId="+course.getId());
		
	}

}
