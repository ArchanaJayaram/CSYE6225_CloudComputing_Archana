package com.csye6225.fall2018.courseservice3.Datamodel;

import java.util.HashMap;

public class InMemoryDatabase {
	
	private static HashMap<Long, Professor> professorDB = new HashMap<> ();
	private static HashMap<Long, Student> studentsDB = new HashMap<> ();
	private static HashMap<String, Courses> coursesDB = new HashMap<> ();
	private static HashMap<String, Lecture> lectureDB = new HashMap<> ();
	private static HashMap<String, Program> programDB = new HashMap<> ();

	public static HashMap<Long, Professor> getProfessorDB() {
		return professorDB;
	}
		
	public static HashMap<Long, Student> getStudentsDB() {
		return studentsDB;
	}
	
	public static HashMap<String, Courses> getCoursesDB() {
		return coursesDB;
	}
	
	public static HashMap<String, Lecture> getLecturesDB() {
		return lectureDB;
	}
	

	public static HashMap<String, Program> getProgramDB() {
		return programDB;
	}
}
