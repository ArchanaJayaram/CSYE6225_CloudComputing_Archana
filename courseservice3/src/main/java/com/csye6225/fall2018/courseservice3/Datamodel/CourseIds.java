package com.csye6225.fall2018.courseservice3.Datamodel;

import java.util.List;

public class CourseIds {
	
	private List<String> courseIds;
	
	public CourseIds() {
		
	}
	
	public CourseIds(List<String> courseIds) {		
		this.courseIds = courseIds;		
	}

	public List<String> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(List<String> courseIds) {
		this.courseIds = courseIds;
	}

}
