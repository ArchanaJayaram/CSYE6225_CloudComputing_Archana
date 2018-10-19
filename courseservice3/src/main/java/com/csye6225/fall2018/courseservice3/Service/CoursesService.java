package com.csye6225.fall2018.courseservice3.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice3.Datamodel.Courses;
import com.csye6225.fall2018.courseservice3.Datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice3.Datamodel.Students;

public class CoursesService {

	static HashMap<String, Courses> coursesMap = InMemoryDatabase.getCoursesDB();
	
	//Adding a course with input as Course object
		public Courses addCourse(Courses course) {	
			String courseId = course.getCourseId();
			coursesMap.put(courseId, course);
			return coursesMap.get(courseId);
		}
	
	// Getting a list of all courses
	public List<Courses> getAllCourses() {	
		ArrayList<Courses> courseList = new ArrayList<>();
		for (Courses course : coursesMap.values()) {
			courseList.add(course);
		}
		return courseList ;
	}
	
	// Getting a course by courseId
	public Courses getCourse(String courseId) {
		return coursesMap.get(courseId);		
	}
	
	//Getting the roster for a course by courseId
	public List<Students> getRoster(String courseId){
		Courses course = coursesMap.get(courseId);
		return course.getRoster();
	}
	
	// Deleting a course
	public Courses deleteCourse(String courseId) {		
		Courses courseToRemove = coursesMap.get(courseId);
		coursesMap.remove(courseId);
		return courseToRemove;		
	}
	
	// Updating Course Info
	public Courses updateCourseInfo(String courseId, Courses course) {
		course.setCourseId(courseId);
		coursesMap.put(courseId, course);
		return course;
	}
	
	//Adding a new student enrolled in a course to the enrolledStudents List
	public void addNewlyEnrolledStudent(Students student) {	
		List<String> courseIds = student.getCoursesEnrolled();
		for(String courseId : courseIds) {			
			Courses course = coursesMap.get(courseId);
			List<Students> enrolledStudents = course.getEnrolledStudents();
			enrolledStudents.add(student);
		}
	}
	//
}
