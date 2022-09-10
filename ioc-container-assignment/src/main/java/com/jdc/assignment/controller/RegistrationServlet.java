package com.jdc.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.domain.Registration;
import com.jdc.assignment.model.OpenClassModel;
import com.jdc.assignment.model.RegistrationModel;

@WebServlet(urlPatterns = {
		"/registration",
		"/registration-edit"
})
public class RegistrationServlet extends AbstractBeanFactoryServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException , IOException {
		String page = switch (req.getServletPath())	{
		
			case "/registration-edit" -> {
				var openClassModel = getBean("openClassModel", OpenClassModel.class);
				req.setAttribute("openClass", openClassModel.getOpenClassByClassId(Integer.parseInt(req.getParameter("openClassId"))));
				yield "/registration-edit.jsp";
			}
			
			default-> {
				var registrationModel = getBean("registrationModel", RegistrationModel.class);
				var openClassModel = getBean("openClassModel", OpenClassModel.class);
				int openClassId = Integer.parseInt(req.getParameter("openClassId"));
				req.setAttribute("registrations", registrationModel.getAllRegistrationsByOpenClassId(openClassId));
				req.setAttribute("openClass", openClassModel.getOpenClassByClassId(openClassId));
				yield "/registration.jsp";
			}
		};
		
		getServletContext().getRequestDispatcher(page).forward(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException , IOException {
		var registrationModel = getBean("registrationModel", RegistrationModel.class);
		var openClassModel = getBean("openClassModel", OpenClassModel.class);
		OpenClass openClass = (OpenClass) openClassModel.getOpenClassByClassId(Integer.parseInt(req.getParameter("openClassId")));
		
		Registration registration = new Registration();
		registration.setOpenClass(openClass);
		registration.setStudentName(req.getParameter("name"));
		registration.setStudentPhone(req.getParameter("phone"));
		registration.setStudentEmail(req.getParameter("email"));
		
		registrationModel.register(registration);
		res.sendRedirect("/registration?openClassId="+openClass.getId());
	}

}
