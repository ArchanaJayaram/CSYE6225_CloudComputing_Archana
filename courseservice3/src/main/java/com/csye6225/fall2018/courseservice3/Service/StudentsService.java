package com.csye6225.fall2018.courseservice3.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice3.Datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice3.Datamodel.Students;
import com.csye6225.fall2018.courseservice3.Resources.CoursesResource;
import com.csye6225.fall2018.courseservice3.Datamodel.Courses;
import com.csye6225.fall2018.courseservice3.Service.CoursesService;

public class StudentsService {
	
	static HashMap<Long, Students> studentsMap = InMemoryDatabase.getStudentsDB();
	//static CoursesService courseService = new CoursesService();
	
	// Getting a list of all students
	public List<Students> getAllStudents() {	
		ArrayList<Students> studentList = new ArrayList<>();
		for (Students student : studentsMap.values()) {
			studentList.add(student);
		}
		return studentList ;
	}
	
	// Getting a student by StudentID
	public Students getStudent(long StudentID) {
		return studentsMap.get(StudentID);		
	}
	
	// Get students in a given Program 
	public List<Students> getStudentsByProgram(String programName){
		
		ArrayList<Students> studentList = new ArrayList<>();
		for(Students student : studentsMap.values()) {
			if(student.getProgramName() == programName) {
				studentList.add(student);
			}
		}
		return studentList;
	}
	
	//Adding a student with input as a Students object
	public Students addStudent(Students student) {	
		long nextAvailableId = studentsMap.size() + 1;
		student.setStudentID(nextAvailableId);
		studentsMap.put(nextAvailableId, student);
		return studentsMap.get(nextAvailableId);
	}
	
	// Adding a student  with student details as input params
	/*public void addStudent(String firstName, String programName, String[] coursesEnrolled, String image) {
		long nextAvailableId = studentsMap.size()+1;
		Students student = new Students(firstName, nextAvailableId, programName, coursesEnrolled, image);
		studentsMap.put(nextAvailableId, student);
	}*/

	// Deleting a Student
	public Students deleteStudent(Long studentId) {		
		Students studentToRemove = studentsMap.get(studentId);
		studentsMap.remove(studentId);
		return studentToRemove;		
	}
	
	// Updating Student Info
	public Students updateStudentInfo(long studentId, Students student) {
		student.setStudentID(studentId);
		studentsMap.put(studentId, student);
		return student;
	}
	
}
