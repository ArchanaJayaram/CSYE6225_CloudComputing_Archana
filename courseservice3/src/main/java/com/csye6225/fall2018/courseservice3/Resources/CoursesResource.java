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

import com.csye6225.fall2018.courseservice3.Datamodel.Courses;
import com.csye6225.fall2018.courseservice3.Datamodel.Students;
import com.csye6225.fall2018.courseservice3.Service.CoursesService;

@Path("courses")
public class CoursesResource {
	
	CoursesService courseService = new CoursesService();
	
	//Get all courses
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Courses> getCourses() {
			return courseService.getAllCourses();			
		}
		
		// ... webapi/courses/1 
		//Get courses by course Id
		@GET
		@Path("/{courseId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Courses getStudent(@PathParam("courseId") String courseId) {
			return courseService.getCourse(courseId);
		}
		
		//Delete a course by course Id
		@DELETE
		@Path("/{courseId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Courses deleteStudent(@PathParam("courseId") String courseId) {
			return courseService.deleteCourse(courseId);
		}
		
		//Add a new course
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Courses addCourse(Courses course) {
				return courseService.addCourse(course);
		}
		
		//Update information for an existing student
		@PUT
		@Path("/{courseId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Courses updateCourse(@PathParam("courseId") String courseId, Courses course) {
			return courseService.updateCourseInfo(courseId, course);
		}
		
		//Update enrolledStudents list with newly created student
		/*@PUT
		@Path("/{student}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public void updateEnrolledStudentsList(@PathParam("student") Students student) {
			courseService.addNewlyEnrolledStudent(student);
		}*/
		
}
