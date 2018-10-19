package com.csye6225.fall2018.courseservice3.Datamodel;

import java.util.List;

public class Lecture {

	private List<String> notes;
	private List<String> courseMaterial;
	private String courseId;
	
	public Lecture() {		
	}
	
	public Lecture(String courseId, List<String> notes, List<String> courseMaterial) {
		this.courseId = courseId;
		this.courseMaterial = courseMaterial;
		this.notes = notes;
	}

	public List<String> getNotes() {
		return notes;
	}

	public void setNotes(List<String> notes) {
		this.notes = notes;
	}

	public List<String> getCourseMaterial() {
		return courseMaterial;
	}

	public void setCourseMaterial(List<String> courseMaterial) {
		this.courseMaterial = courseMaterial;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

}
