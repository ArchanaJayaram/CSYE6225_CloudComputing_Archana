package com.csye6225.fall2018.courseservice3.Resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice3.Datamodel.CourseIds;
import com.csye6225.fall2018.courseservice3.Datamodel.Student;
import com.csye6225.fall2018.courseservice3.Service.StudentsService;

@Path("students")
public class StudentsResource {
	
	StudentsService studentService = new StudentsService();
	
	//Add a new student
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student student) {
			return studentService.addStudent(student);
	}
	
	//Registering a student for the courses
	@POST
	@Path("/{studentId}/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student registerStudentToCourse(@PathParam("studentId") String studentId, CourseIds courseId) {
			return studentService.registerStudentToCourse(studentId, courseId);
	}
	
	//Get all students
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudents() {
		return studentService.getAllStudents();			
	}
	
	//Get students by Student Id
	@GET
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudent(@PathParam("studentId") String studentId) {
		return studentService.getStudent(studentId);
	}
	
	//Update information for an existing student
	@PUT
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateProfessor(@PathParam("studentId") String studentId,Student student) {
		return studentService.updateStudentInfo(studentId, student);
	}
	
	//Delete a student by student Id
	@DELETE
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("studentId") String studentId) {
		return studentService.deleteStudent(studentId);
	}
		
}
