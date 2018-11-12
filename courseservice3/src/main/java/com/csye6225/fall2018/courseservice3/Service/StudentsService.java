package com.csye6225.fall2018.courseservice3.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice3.Datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice3.Datamodel.Student;

public class StudentsService {
	
	static DynamoDbConnector dynamoDB;
	DynamoDBMapper mapper;
	Student studentObject = new Student();
	
	public StudentsService() {		
		dynamoDB = new DynamoDbConnector();
		dynamoDB.init();
		mapper = new DynamoDBMapper(dynamoDB.getClient());		
	}
		
	//Adding a student with input as a Students object
	public Student addStudent(Student student) {	
		mapper.save(student);
		return student;
	}
	
	// Getting a list of all students
	public List<Student> getAllStudents() {	
		DynamoDBScanExpression expression = new DynamoDBScanExpression();
		expression.setIndexName("studentId-index");
		expression.withConsistentRead(false);
		List<Student> list = mapper.scan(Student.class, expression);
		return list;
	}
	
	// Getting a student by StudentID
	public List<Student> getStudent(String studId) {
		HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#key", "studentId");

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value", new AttributeValue().withS(studId));
        
		DynamoDBQueryExpression<Student> expression = new DynamoDBQueryExpression<Student>()
		.withIndexName("studentId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("#key = :value")
		.withExpressionAttributeNames(nameMap)
		.withExpressionAttributeValues(valueMap);
		
		List<Student> list = mapper.query(Student.class,expression);
		return list;	
	}
	
	// Updating Student Info
	public Student updateStudentInfo(String studId, Student newStudent) {
		HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#key", "studentId");

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value", new AttributeValue().withS(studId));
        
		DynamoDBQueryExpression<Student> expression = new DynamoDBQueryExpression<Student>()
		.withIndexName("studentId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("#key = :value")
		.withExpressionAttributeNames(nameMap)
		.withExpressionAttributeValues(valueMap);
		
		List<Student> list = mapper.query(Student.class,expression);
		
		Student studRetrieved = list.get(0);
		
		studRetrieved.setFirstName(newStudent.getFirstName());
		studRetrieved.setLastName(newStudent.getLastName());
		studRetrieved.setStudentId(newStudent.getStudentId());
		studRetrieved.setJoiningDate(newStudent.getJoiningDate());
		studRetrieved.setDepartment(newStudent.getDepartment());
		studRetrieved.setRegisteredCourses(newStudent.getRegisteredCourses());
		
		mapper.save(studRetrieved);
		return studRetrieved;
	}
	
	// Deleting a Student
	public Student deleteStudent(String studId) {		
		HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#key", "studentId");

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value", new AttributeValue().withS(studId));
        
		DynamoDBQueryExpression<Student> expression = new DynamoDBQueryExpression<Student>()
		.withIndexName("studentId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("#key = :value")
		.withExpressionAttributeNames(nameMap)
		.withExpressionAttributeValues(valueMap);
		
		List<Student> list = mapper.query(Student.class,expression);
		Student studentToDelete = list.get(0);
		
		mapper.delete(studentToDelete);
		return studentToDelete;		
	}
	
	
}
