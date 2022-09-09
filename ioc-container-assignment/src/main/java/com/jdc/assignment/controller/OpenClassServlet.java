package com.jdc.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			case "/classes-edit" -> "/classes-edit.jsp";
			default -> {
				var openClassesModel = getBean("openClassModel", OpenClassModel.class);
				int courseId = Integer.parseInt(req.getParameter("courseId"));
				getServletContext().setAttribute("openClasses", openClassesModel.findOpenClassesByCourseId(courseId));
				yield "/classes.jsp";
			}
		};
		
		getServletContext().getRequestDispatcher(page).forward(req, resp);
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
