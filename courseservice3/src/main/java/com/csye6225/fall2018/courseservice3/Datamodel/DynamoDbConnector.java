package com.csye6225.fall2018.courseservice3.Datamodel;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDbConnector {

	static AmazonDynamoDB dynamoDB;
	
	public static void init() {
		
		if(dynamoDB == null) {
			
			InstanceProfileCredentialsProvider credentialsProvider = new InstanceProfileCredentialsProvider(false);
			credentialsProvider.getCredentials();	
		dynamoDB = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-2")
				.build();
		}
	}
	
	public AmazonDynamoDB getClient(){
		return dynamoDB;
		}
	}
