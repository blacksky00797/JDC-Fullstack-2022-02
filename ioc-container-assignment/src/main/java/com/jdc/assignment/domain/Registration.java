package com.jdc.assignment.domain;

public class Registration {
	
	private int id;
	private OpenClass openClass;
	private String studentName;
	private String studentPhone;
	private String studentEmail;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OpenClass getOpenClass() {
		return openClass;
	}
	public void setOpenClass(OpenClass openClass) {
		this.openClass = openClass;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentPhone() {
		return studentPhone;
	}
	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	
}
