package com.csye6225.fall2018.courseservice3.Datamodel;

import java.util.ArrayList;
import java.util.List;

public class Courses {
	
	private String courseName;
	private String courseId;
	private Students TA;
	private List<Students> enrolledStudents =  new ArrayList<>();
	private Professor prof;
	private List<Students> Roster = new ArrayList<>();
	private Lecture lectures;
	private List<String> board;
	
	public Courses() {
		
	}
	
	public Courses(String courseName, String courseId, Students TA, List<Students> enrolledStudents, Professor prof, List<Students> Roster,Lecture lectures, List<String> board) {
		this.TA = TA;
		this.enrolledStudents = enrolledStudents;
		this.prof = prof;
		this.Roster = Roster;
		this.lectures = lectures;
		this.board = board;	
	}

	public Students getTA() {
		return TA;
	}

	public void setTA(Students TA) {
		this.TA = TA;
	}

	public List<Students> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(List<Students> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	public Professor getProf() {
		return prof;
	}

	public void setProf(Professor prof) {
		this.prof = prof;
	}

	public List<Students> getRoster() {
		return Roster;
	}

	public void setRoster(List<Students> roster) {
		Roster = roster;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String nextAvailableId) {
		this.courseId = nextAvailableId;
	}

	public Lecture getLectures() {
		return lectures;
	}

	public void setLectures(Lecture lectures) {
		this.lectures = lectures;
	}

	public List<String> getBoard() {
		return board;
	}

	public void setBoard(List<String> board) {
		this.board = board;
	}
	
}
