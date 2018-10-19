package com.csye6225.fall2018.courseservice3.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice3.Datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice3.Datamodel.Professor;

public class ProfessorsService {
	
static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();
	
	// Getting a list of all professor 
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			list.add(prof);
		}
		return list ;
	}

	// Adding a professor
	public void addProfessor(String name, String department, Date joiningDate) {
		// Next Id 
		long nextAvailableId = prof_Map.size() + 1;
		
		//Create a Professor Object
		Professor prof = new Professor(nextAvailableId, name, department, joiningDate);
		prof_Map.put(nextAvailableId, prof);
	}
	
	public Professor addProfessor(Professor prof) {	
		long nextAvailableId = prof_Map.size() + 1;
		prof.setProfessorId(nextAvailableId);
		prof_Map.put(nextAvailableId, prof);
		return prof_Map.get(nextAvailableId);
	}
	
	// Getting One Professor
	public Professor getProfessor(Long profId) {
		
		Professor prof1 = new Professor(1L,"Adam", "InformationSystems",new Date());
		Professor prof2 = new Professor(2L,"Isaac", "ComputerScience",new Date());
		prof_Map.put(1L,prof1);
		prof_Map.put(2L,prof2);
		return prof_Map.get(profId);
	}
	
	// Deleting a professor
	public Professor deleteProfessor(Long profId) {
		Professor deletedProfDetails = prof_Map.get(profId);
		prof_Map.remove(profId);
		return deletedProfDetails;
	}
	
	// Updating Professor Info
	public Professor updateProfessorInformation(Long profId, Professor prof) {	
		Professor oldProfObject = prof_Map.get(profId);
		profId = oldProfObject.getProfessorId();
		prof.setProfessorId(profId);
		// Publishing New Values
		prof_Map.put(profId, prof) ;
		
		return prof;
	}
	
	// Get professors in a department 
	public List<Professor> getProfessorsByDepartment(String department) {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			if (prof.getDepartment().equals(department)) {
				list.add(prof);
			}
		}
		return list ;
	}
	
	// Get professors for a year with a size limit
}
