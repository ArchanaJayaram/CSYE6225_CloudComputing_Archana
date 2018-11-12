package com.csye6225.fall2018.courseservice3.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice3.Datamodel.Announcements;
import com.csye6225.fall2018.courseservice3.Datamodel.Courses;
import com.csye6225.fall2018.courseservice3.Datamodel.DynamoDbConnector;

public class AnnouncementsService {

	static DynamoDbConnector dynamoDB;
	DynamoDBMapper mapper;
	Courses courseObject = new Courses();
	
	public AnnouncementsService() {		
		dynamoDB = new DynamoDbConnector();
		dynamoDB.init();
		mapper = new DynamoDBMapper(dynamoDB.getClient());		
	}
	
	//Adding an announcement with input as announcement object
		public Announcements addAnnouncement(Announcements announcement) {	
			String text = announcement.getAnnouncementText();
			if (text.length() > 160) {
				text = text.substring(0, 160);
				announcement.setAnnouncementText(text);
			}
			mapper.save(announcement);
			return announcement;
		}
	
	// Getting a list of all Announcements
	public List<Announcements> getAllAnnouncements() {	
		DynamoDBScanExpression expression = new DynamoDBScanExpression();
		expression.setIndexName("boardId-announcementId-index");
		expression.withConsistentRead(false);
		List<Announcements> list = mapper.scan(Announcements.class, expression);
		return list;
	}
	
	// Getting an Announcement by AnnouncementId
	public List<Announcements> getAnnouncement(String boardId_announcementId) {
		String[] input = boardId_announcementId.split("_");
		String boardId = input[0];
		String announcementId = input[1];

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value1", new AttributeValue().withS(boardId));
        valueMap.put(":value2", new AttributeValue().withS(announcementId));
        
		DynamoDBQueryExpression<Announcements> expression = new DynamoDBQueryExpression<Announcements>()
		.withIndexName("boardId-announcementId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("boardId = :value1 and begins_with(announcementId, :value2)")
		.withExpressionAttributeValues(valueMap);
		
		List<Announcements> list = mapper.query(Announcements.class,expression);
		return list;
		
	}
	
	// Updating Announcement Info
	public Announcements updateAnnouncement(String boardId_announcementId, Announcements newAnnouncement) {
		String[] input = boardId_announcementId.split("_");
		String boardId = input[0];
		String announcementId = input[1];

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value1", new AttributeValue().withS(boardId));
        valueMap.put(":value2", new AttributeValue().withS(announcementId));
        
		DynamoDBQueryExpression<Announcements> expression = new DynamoDBQueryExpression<Announcements>()
		.withIndexName("boardId-announcementId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("boardId = :value1 and begins_with(announcementId, :value2)")
		.withExpressionAttributeValues(valueMap);
		
		List<Announcements> list = mapper.query(Announcements.class,expression);
		Announcements announcementRetrieved = list.get(0);
		
		announcementRetrieved.setAnnouncementId(newAnnouncement.getAnnouncementId());
		announcementRetrieved.setBoardId(newAnnouncement.getBoardId());
		
		String text = newAnnouncement.getAnnouncementText();
		if (text.length() > 160) {
			text = text.substring(0, 160);
			newAnnouncement.setAnnouncementText(text);
		}
		announcementRetrieved.setAnnouncementText(newAnnouncement.getAnnouncementText());
		
		mapper.save(announcementRetrieved);
		return announcementRetrieved;
	}

	// Deleting an Announcement
	public Announcements deleteAnnouncement(String boardId_announcementId) {		
		String[] input = boardId_announcementId.split("_");
		String boardId = input[0];
		String announcementId = input[1];

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value1", new AttributeValue().withS(boardId));
        valueMap.put(":value2", new AttributeValue().withS(announcementId));
        
		DynamoDBQueryExpression<Announcements> expression = new DynamoDBQueryExpression<Announcements>()
		.withIndexName("boardId-announcementId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("boardId = :value1 and begins_with(announcementId, :value2)")
		.withExpressionAttributeValues(valueMap);
		
		List<Announcements> list = mapper.query(Announcements.class,expression);
		
		Announcements announcementToDelete = list.get(0);
		mapper.delete(announcementToDelete);
		return announcementToDelete;		
	}
		
}
