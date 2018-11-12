package com.csye6225.fall2018.courseservice3.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice3.Datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice3.Datamodel.Program;
import com.csye6225.fall2018.courseservice3.Datamodel.Courses;
import com.csye6225.fall2018.courseservice3.Datamodel.Student;

public class ProgramService {

	static HashMap<String, Program> programMap = InMemoryDatabase.getProgramDB();
	static CoursesService courseService = new CoursesService();
	
	//Adding a program with input as Program object
			public Program addProgram(Program program) {
				String progName = program.getProgramName();
				programMap.put(progName, program);
				return programMap.get(progName);
			}
		
		// Getting a list of all programs
		public List<Program> getAllPrograms() {	
			ArrayList<Program> programList = new ArrayList<>();
			for (Program program : programMap.values()) {
				programList.add(program);
			}
			return programList ;
		}
		
		// Getting a program by programName
		public Program getProgram(String progName) {
			return programMap.get(progName);		
		}
		
		// Deleting a program
		public Program deleteProgram(String progName) {		
			Program programToRemove = programMap.get(progName);
			programMap.remove(progName);
			return programToRemove;		
		}
		
		// Updating Program Info
		public Program updateProgramInfo(String progName, String director) {
			Program program = programMap.get(progName);
			program.setDirector(director);
			programMap.put(progName, program);
			return program;
		}
}
