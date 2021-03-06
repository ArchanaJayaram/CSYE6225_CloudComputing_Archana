package com.csye6225.fall2018.courseservice3.Datamodel;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.sns.model.CreateTopicResult;

@DynamoDBTable(tableName = "Course")
public class Courses {
	
	private String Id;
	private String courseId;
	private String professorId;
	private String taId;
	private String department;
	private String boardId;
	private List<String> roster;
	private String notificationTopic;
	
	public Courses() {
		
	}
	
	public Courses(String Id, String courseId, String professorId, String taId, String department, String boardId, List<String> roster, String emailId, String notificationTopic) {
		this.Id=Id;
		this.courseId=courseId;
		this.professorId=professorId;
		this.taId = taId;
		this.department = department;
		this.boardId = boardId;
		this.roster = roster;
		this.notificationTopic = notificationTopic;
	}
	
	@DynamoDBHashKey(attributeName="Id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	
	@DynamoDBIndexHashKey(globalSecondaryIndexName = "courseId-index", attributeName = "courseId")
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@DynamoDBAttribute(attributeName = "professorId")
	public String getProfessorId() {
		return professorId;
	}
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}
	
	@DynamoDBAttribute(attributeName = "taId")
	public String getTaId() {
		return taId;
	}
	public void setTaId(String taId) {
		this.taId = taId;
	}
	
	@DynamoDBAttribute(attributeName = "department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@DynamoDBAttribute(attributeName = "boardId")
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	
	@DynamoDBAttribute(attributeName = "roster")
	public List<String> getRoster() {
		return roster;
	}
	public void setRoster(List<String> roster) {
		this.roster = roster;
	}
	
	@DynamoDBAttribute(attributeName = "notificationTopic")
	public String getNotificationTopic() {
		return notificationTopic;
	}

	public void setNotificationTopic(String notificationTopic) {
		this.notificationTopic = notificationTopic;
	}
	

	
	
}
