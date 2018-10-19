package com.csye6225.fall2018.courseservice3.Datamodel;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class Professor {	
	private String firstName;
	private String department;
	private long ProfessorId;
	private Date joiningDate;
	
	public Professor() {
		
	}
	
	public Professor(long ProfessorId, String firstName, String department, Date joiningDate) {
		this.ProfessorId = ProfessorId;
		this.firstName = firstName;
		this.department= department;
		this.joiningDate = joiningDate;		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getProfessorId() {
		return ProfessorId;
	}

	public void setProfessorId(long professorId) {
		ProfessorId = professorId;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	

}
