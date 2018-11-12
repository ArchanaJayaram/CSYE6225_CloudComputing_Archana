package com.csye6225.fall2018.courseservice3.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.csye6225.fall2018.courseservice3.Datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice3.Datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice3.Datamodel.Professor;

public class ProfessorsService {

	static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();
	static DynamoDbConnector dynamoDB;
	DynamoDBMapper mapper;
	Professor profObject = new Professor();
	
	public ProfessorsService() {		
		dynamoDB = new DynamoDbConnector();
		dynamoDB.init();
		mapper = new DynamoDBMapper(dynamoDB.getClient());		
	}
		
	// Adding a professor
	public Professor addProfessor(Professor prof) {		
		mapper.save(prof);
		return prof;
	}
	
	// Getting all the professors
	public List<Professor> getAllProfessors() {	
		DynamoDBScanExpression expression = new DynamoDBScanExpression();
		expression.setIndexName("professorId-index");
		expression.withConsistentRead(false);
		List<Professor> list = mapper.scan(Professor.class, expression);
		return list;
	}
	
	// Getting One Professor
	public List<Professor> getProfessor(String profId) {
		
		HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#key", "professorId");

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value", new AttributeValue().withS(profId));
        
		DynamoDBQueryExpression<Professor> expression = new DynamoDBQueryExpression<Professor>()
		.withIndexName("professorId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("#key = :value")
		.withExpressionAttributeNames(nameMap)
		.withExpressionAttributeValues(valueMap);
		
		List<Professor> list = mapper.query(Professor.class,expression);
		return list;
	}
	
	// Updating Professor Info
	public Professor updateProfessorInformation(String profId, Professor newProf) {
		
		HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#key", "professorId");

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value", new AttributeValue().withS(profId));
        
		DynamoDBQueryExpression<Professor> expression = new DynamoDBQueryExpression<Professor>()
		.withIndexName("professorId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("#key = :value")
		.withExpressionAttributeNames(nameMap)
		.withExpressionAttributeValues(valueMap);
		
		List<Professor> list = mapper.query(Professor.class,expression);
		
		Professor profRetrieved = list.get(0);
		
		profRetrieved.setFirstName(newProf.getFirstName());
		profRetrieved.setLastName(newProf.getLastName());
		profRetrieved.setProfessorId(newProf.getProfessorId());
		profRetrieved.setJoiningDate(newProf.getJoiningDate());
		profRetrieved.setDepartment(newProf.getDepartment());
		
		mapper.save(profRetrieved);
		return profRetrieved;
	}
	
	// Deleting a professor
	public Professor deleteProfessor(String profId) {
		
		HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#key", "professorId");

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value", new AttributeValue().withS(profId));
        
		DynamoDBQueryExpression<Professor> expression = new DynamoDBQueryExpression<Professor>()
		.withIndexName("professorId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("#key = :value")
		.withExpressionAttributeNames(nameMap)
		.withExpressionAttributeValues(valueMap);
		
		List<Professor> list = mapper.query(Professor.class,expression);
		
		Professor profToDelete = list.get(0);
		mapper.delete(profToDelete);
		return profToDelete;
	}
	
	// Get professors in a department 
	public List<Professor> getProfessorsByDepartment(String department) {	
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			if (prof.getDepartment().equals(department)) {
				list.add(prof);
			}
		}
		return list ;
	}

}
