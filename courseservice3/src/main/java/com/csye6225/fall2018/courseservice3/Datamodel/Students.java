package com.csye6225.fall2018.courseservice3.Datamodel;

import java.util.ArrayList;
import java.util.List;

public class Students {

	private String firstName;
	private long studentID;
	private String programName;
	//An array of type long to hold the courseIds of the courses enrolled in
	private List<String> coursesEnrolled = new ArrayList<>();
	private String image;
	
	public Students() {
		
	}
	
	public Students(String firstName, long studentID, String programName, List<String> coursesEnrolled, String image) {
		this.firstName = firstName;
		this.studentID = studentID;
		this.programName = programName;
		this.coursesEnrolled = coursesEnrolled;
		this.setImage(image);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public long getStudentID() {
		return studentID;
	}

	public void setStudentID(long studentID) {
		this.studentID = studentID;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public List<String> getCoursesEnrolled() {
		return coursesEnrolled;
	}

	public void setCoursesEnrolled(List<String> coursesEnrolled) {
		this.coursesEnrolled = coursesEnrolled;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
