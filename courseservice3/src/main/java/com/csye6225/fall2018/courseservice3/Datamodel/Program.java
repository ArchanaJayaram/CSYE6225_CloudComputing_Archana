package com.csye6225.fall2018.courseservice3.Datamodel;

import java.util.List;

public class Program {
   
	private String programName;
	private String director;
	private List<Courses> courses;
	private List<Student> enrolledStudents;
	
	public Program() {
		
	}
	
	public Program(String programName, String director, List<Courses>courses, List<Student> enrolledStudents) {
		this.programName = programName;
		this.director = director;
		this.courses = courses;
		this.enrolledStudents = enrolledStudents;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public List<Courses> getCourses() {
		return courses;
	}

	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}
	public List<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(List<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

}
