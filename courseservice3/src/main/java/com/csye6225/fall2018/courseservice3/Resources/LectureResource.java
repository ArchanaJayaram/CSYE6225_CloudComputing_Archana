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
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice3.Datamodel.Courses;
import com.csye6225.fall2018.courseservice3.Datamodel.Lecture;
import com.csye6225.fall2018.courseservice3.Service.CoursesService;
import com.csye6225.fall2018.courseservice3.Service.LectureService;

@Path("lectures")
public class LectureResource {

LectureService lectureService = new LectureService();
	
	//Get all lectures
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Lecture> getLectures() {
			return lectureService.getAllLectures();			
		}
		
		// ... webapi/lectures/1 
		//Get lectures by course Id
		@GET
		@Path("/{courseId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Lecture getLecture(@PathParam("courseId") String courseId) {
			return lectureService.getLecture(courseId);
		}
		
		//Delete a lecture by course Id
		@DELETE
		@Path("/{courseId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Lecture deleteLecture(@PathParam("courseId") String courseId) {
			return lectureService.deleteLecture(courseId);
		}
		
		//Add a new course
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Lecture addLecture(Lecture lecture) {
				return lectureService.addLecture(lecture);
		}
		
		//Update information for an existing student
		@PUT
		@Path("/{courseId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Lecture updateCourse(@PathParam("courseId") String courseId, List<String>updatedNotes) {
			return lectureService.updateLectureInfo(courseId, updatedNotes);
		}
}
