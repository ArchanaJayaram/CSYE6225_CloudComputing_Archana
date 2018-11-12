package com.csye6225.fall2018.courseservice3.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice3.Datamodel.Board;
import com.csye6225.fall2018.courseservice3.Datamodel.DynamoDbConnector;

public class BoardService {

	static DynamoDbConnector dynamoDB;
	DynamoDBMapper mapper;
	Board boardObject = new Board();
	
	public BoardService() {		
		dynamoDB = new DynamoDbConnector();
		dynamoDB.init();
		mapper = new DynamoDBMapper(dynamoDB.getClient());		
	}
	
	//Adding a board with input as board object
		public Board addBoard(Board board) {	
			mapper.save(board);
			return board;
		}
	
	// Getting a list of all board
	public List<Board> getAllboards() {	
		DynamoDBScanExpression expression = new DynamoDBScanExpression();
		expression.setIndexName("boardId-index");
		expression.withConsistentRead(false);
		List<Board> list = mapper.scan(Board.class, expression);
		return list;
	}
	
	// Getting a board by boardId
	public List<Board> getBoard(String boardId) {
		HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#key", "boardId");

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value", new AttributeValue().withS(boardId));
        
		DynamoDBQueryExpression<Board> expression = new DynamoDBQueryExpression<Board>()
		.withIndexName("boardId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("#key = :value")
		.withExpressionAttributeNames(nameMap)
		.withExpressionAttributeValues(valueMap);
		
		List<Board> list = mapper.query(Board.class,expression);
		return list;		
	}
	
	// Updating Board Info
	public Board updateBoard(String boardId, Board newBoard) {
		HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#key", "boardId");

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value", new AttributeValue().withS(boardId));
        
		DynamoDBQueryExpression<Board> expression = new DynamoDBQueryExpression<Board>()
		.withIndexName("boardId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("#key = :value")
		.withExpressionAttributeNames(nameMap)
		.withExpressionAttributeValues(valueMap);
		
		List<Board> list = mapper.query(Board.class,expression);		
		Board boardRetrieved = list.get(0);
		
		boardRetrieved.setBoardId(newBoard.getBoardId());
		boardRetrieved.setCourseId(newBoard.getCourseId());		
		mapper.save(boardRetrieved);
		return boardRetrieved;
	}
	
	// Deleting a board
	public Board deleteBoard(String boardId) {		
		HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#key", "boardId");

        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":value", new AttributeValue().withS(boardId));
        
		DynamoDBQueryExpression<Board> expression = new DynamoDBQueryExpression<Board>()
		.withIndexName("boardId-index")
		.withConsistentRead(false)
		.withKeyConditionExpression("#key = :value")
		.withExpressionAttributeNames(nameMap)
		.withExpressionAttributeValues(valueMap);
		
		List<Board> list = mapper.query(Board.class,expression);
		
		Board boardToDelete = list.get(0);
		mapper.delete(boardToDelete);
		return boardToDelete;		
	}

}
