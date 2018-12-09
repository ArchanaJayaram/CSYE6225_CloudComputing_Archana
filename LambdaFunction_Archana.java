package com.amazonaws.lambda.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemUtils;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.regions.Region;

public class Assignment3 implements RequestHandler<DynamodbEvent, String> {
	
	DefaultAWSCredentialsProviderChain credentialsProvider = new DefaultAWSCredentialsProviderChain();
	AmazonSNSClientBuilder snsClientBuilder = AmazonSNSClient.builder();	
	AmazonSNS sns = AmazonSNSClientBuilder.standard().withCredentials(credentialsProvider)
			.withRegion("us-east-2")
			.build();
	String courseId, Id, notificationTopic, boardId;
	
    @Override
    public String handleRequest(DynamodbEvent ddbEvent, Context context) { 
    	
	       for (DynamodbStreamRecord record : ddbEvent.getRecords()){
	    	   if (record.getEventName().equalsIgnoreCase("INSERT")){ 
	    		   System.out.println(record.getEventName());
		           System.out.println(record.getEventID());		           
		           System.out.println(record.getDynamodb().getNewImage().toString());
		           
		           Map<String, AttributeValue> map = record.getDynamodb().getNewImage();
		           String message = map.get("announcementText").getS();
		           boardId = map.get("boardId").getS();
		           
					try {
						URL urlBoard = new URL("http://cloudcomputing-env.i4uhkqy8qx.us-east-2.elasticbeanstalk.com/webapi/board/"+ boardId);
						HttpURLConnection con1 = (HttpURLConnection) urlBoard.openConnection();
						con1.setRequestProperty("Content-Type", "application/json");
						con1.setRequestMethod("GET");
						System.out.println("Response Code : " + con1.getResponseCode());
						BufferedReader in1 = new BufferedReader(
						        new InputStreamReader(con1.getInputStream()));
						String inputLine1;
						StringBuffer boardResponse = new StringBuffer();

						while ((inputLine1 = in1.readLine()) != null) {
							boardResponse.append(inputLine1);
						}
						
						in1.close();
						
						System.out.println(boardResponse.toString());
		
						JSONArray arrBoard = new  JSONArray(boardResponse.toString());
						
						for(int i = 0; i < arrBoard.length(); i++) {	
						System.out.println(arrBoard.length());
						   JSONObject obj = arrBoard.getJSONObject(i);
						   boardId = obj.getString("boardId");
						   courseId = obj.getString("courseId");
						   Id = obj.getString("id");
						   break;
						}

						System.out.println("CourseId:" + courseId);

						URL urlCourse = new URL("http://cloudcomputing-env.i4uhkqy8qx.us-east-2.elasticbeanstalk.com/webapi/courses/"+courseId);
						HttpURLConnection con2 = (HttpURLConnection) urlCourse.openConnection();
						con2.setRequestProperty("Content-Type", "application/json");
						con2.setRequestMethod("GET");
						System.out.println("Response Code : " + con2.getResponseCode());
						BufferedReader in2 = new BufferedReader(
						        new InputStreamReader(con2.getInputStream()));
						String inputLine2;
						StringBuffer courseResponse = new StringBuffer();

						while ((inputLine2 = in2.readLine()) != null) {
							courseResponse.append(inputLine2);
						}
						in2.close();
						
						System.out.println(courseResponse.toString());
						
						JSONArray arrCourse = new  JSONArray(courseResponse.toString());
						
						for(int i = 0; i < arrCourse.length(); i++) {	
						   JSONObject obj = arrCourse.getJSONObject(i);
						   notificationTopic = obj.getString("notificationTopic");
						   break;
						}
						
						System.out.println("NotificationTopic:" +notificationTopic);
						PublishRequest publishRequest = new PublishRequest(notificationTopic, message);
				        PublishResult publishResult = sns.publish(publishRequest);
						
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (JSONException e) {
						e.printStackTrace();
					}
									
	    	   }
	       }
        return "Successfully processed " + ddbEvent.getRecords().size() + " records.";
    }
}
