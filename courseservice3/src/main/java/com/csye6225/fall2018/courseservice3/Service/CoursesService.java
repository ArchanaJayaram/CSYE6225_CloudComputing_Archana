package com.csye6225.fall2018.courseservice3.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.csye6225.fall2018.courseservice3.Datamodel.Courses;
import com.csye6225.fall2018.courseservice3.Datamodel.DynamoDbConnector;

public class CoursesService {

	static DynamoDbConnector dynamoDB;
	DynamoDBMapper mapper;
	String notificationTopic;
	Courses courseObject = new Courses();
	AmazonSNSClientBuilder snsClientBuilder = AmazonSNSClient.builder();
	AmazonSNS sns = snsClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
			.withRegion("us-east-2")
			.build();

	
	public CoursesService() {		
		dynamoDB = new DynamoDbConnector();
		dynamoDB.init();
		mapper = new DynamoDBMapper(dynamoDB.getClient());		
	}
	
	//Adding a course with input as Course object
		public Courses addCourse(Courses course) {			
			CreateTopicRequest createTopicRequest = new CreateTopicRequest("Topic_"+course.getCourseId());
			CreateTopicResult createTopicResult = sns.createTopic(createTopicRequest);
			notificationTopic = createTopicResult.getTopicArn();			
			course.setNotificationTopic(notificationTopic);
			mapper.save(course);
			return course;			
		}
	
	// Getting a list of all courses
	public List<Courses> getAllCourses() {	
		DynamoDBScanExpression expression = new DynamoDBScanExpression();
		expression.setIndexName("courseId-index");
		expression.withConsistentRead(false);
		List<Courses> list = mapper.scan(Courses.class, expression);
		return list;
	}
	
	// Getting a course by courseId
	public List<Courses> getCourse(String courseId) {

		HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#key", "courseId");

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value", new AttributeValue().withS(courseId));
        
		DynamoDBQueryExpression<Courses> expression = new DynamoDBQueryExpression<Courses>()
		.withIndexName("courseId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("#key = :value")
		.withExpressionAttributeNames(nameMap)
		.withExpressionAttributeValues(valueMap);
		
		List<Courses> list = mapper.query(Courses.class,expression);
		return list;
		
	}

	// Deleting a course
	public Courses deleteCourse(String courseId) {		
		HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#key", "courseId");

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value", new AttributeValue().withS(courseId));
        
		DynamoDBQueryExpression<Courses> expression = new DynamoDBQueryExpression<Courses>()
		.withIndexName("courseId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("#key = :value")
		.withExpressionAttributeNames(nameMap)
		.withExpressionAttributeValues(valueMap);
		
		List<Courses> list = mapper.query(Courses.class,expression);
		
		Courses courseToDelete = list.get(0);
		mapper.delete(courseToDelete);
		return courseToDelete;		
	}
	
	// Updating Course Info
	public Courses updateCourseInfo(String courseId, Courses newCourse) {
		HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#key", "courseId");

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value", new AttributeValue().withS(courseId));
        
		DynamoDBQueryExpression<Courses> expression = new DynamoDBQueryExpression<Courses>()
		.withIndexName("courseId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("#key = :value")
		.withExpressionAttributeNames(nameMap)
		.withExpressionAttributeValues(valueMap);
		
		List<Courses> list = mapper.query(Courses.class,expression);
		
		Courses courseRetrieved = list.get(0);
		
		courseRetrieved.setBoardId(newCourse.getBoardId());
		courseRetrieved.setCourseId(newCourse.getCourseId());
		courseRetrieved.setProfessorId(newCourse.getProfessorId());
		courseRetrieved.setTaId(newCourse.getTaId());
		courseRetrieved.setDepartment(newCourse.getDepartment());
		courseRetrieved.setRoster(newCourse.getRoster());
		
		mapper.save(courseRetrieved);
		return courseRetrieved;
	}
	
}
