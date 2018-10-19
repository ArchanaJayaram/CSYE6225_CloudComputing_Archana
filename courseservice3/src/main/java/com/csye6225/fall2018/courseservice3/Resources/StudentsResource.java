package com.csye6225.fall2018.courseservice3.Resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice3.Datamodel.Professor;
import com.csye6225.fall2018.courseservice3.Datamodel.Students;
import com.csye6225.fall2018.courseservice3.Service.StudentsService;

@Path("students")
public class StudentsResource {
	
	StudentsService studentService = new StudentsService();
	
	//Get all students by programName
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Students> getStudentsByProgram(@QueryParam("programName") String programName) {
		
		if (programName == null) {
			return studentService.getAllStudents();
		}
		return studentService.getStudentsByProgram(programName);
		
	}
	
	// ... webapi/Students/1 
	//Get students by Student Id
	@GET
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Students getStudent(@PathParam("studentId") long studentId) {
		return studentService.getStudent(studentId);
	}
	
	//Delete a student by student Id
	@DELETE
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Students deleteStudent(@PathParam("studentId") long studentId) {
		return studentService.deleteStudent(studentId);
	}
	
	//Add a new student
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Students addStudent(Students student) {
			return studentService.addStudent(student);
	}
	
	//Update information for an existing student
	@PUT
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Students updateProfessor(@PathParam("studentId") long studentId,Students student) {
		return studentService.updateStudentInfo(studentId, student);
	}
	
	
}
