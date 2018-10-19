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

import com.csye6225.fall2018.courseservice3.Datamodel.Program;
import com.csye6225.fall2018.courseservice3.Service.ProgramService;

@Path("programs")
public class ProgramResource {
ProgramService programService = new ProgramService();
	
	//Get all programs
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Program> getPrograms() {
			return programService.getAllPrograms();			
		}
		
		// ... webapi/programs/infosystems 
		//Get programs by program name
		@GET
		@Path("/{programName}")
		@Produces(MediaType.APPLICATION_JSON)
		public Program getLecture(@PathParam("programName") String programName) {
			return programService.getProgram(programName);
		}
		
		//Delete a program by program name
		@DELETE
		@Path("/{programName}")
		@Produces(MediaType.APPLICATION_JSON)
		public Program deleteLecture(@PathParam("programName") String programName) {
			return programService.deleteProgram(programName);
		}
		
		//Add a new program
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Program addProgram(Program program) {
				return programService.addProgram(program);
		}
		
		//Update information for an existing student
		@PUT
		@Path("/{programName}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Program updateCourse(@PathParam("programName") String progName, String director) {
			return programService.updateProgramInfo(progName, director);
		}
}
