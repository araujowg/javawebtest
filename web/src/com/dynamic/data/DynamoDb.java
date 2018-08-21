package com.dynamic.data;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;


public abstract class DynamoDb {

	protected AmazonDynamoDB client;
	protected DynamoDB dynamoDB;

	
	protected final void init()throws Exception{
//		ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider("C:\\_aws","default");
		ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
		try{
			
		}catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (~/.aws/credentials), and is in valid format.",
                    e);
		}
		client = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(credentialsProvider)
				.withRegion("us-east-2")
				.build();
		dynamoDB = new DynamoDB(client);
	}
	
	public abstract Table GetTable() throws Exception;
	
//	public String GetAll()throws Exception {
//		init();
//		
//		String tableName = "users";
//		
//		try {
//			
//			
//			
//        } catch (AmazonServiceException ase) {
//            System.out.println("Caught an AmazonServiceException, which means your request made it "
//                    + "to AWS, but was rejected with an error response for some reason.");
//            System.out.println("Error Message:    " + ase.getMessage());
//            System.out.println("HTTP Status Code: " + ase.getStatusCode());
//            System.out.println("AWS Error Code:   " + ase.getErrorCode());
//            System.out.println("Error Type:       " + ase.getErrorType());
//            System.out.println("Request ID:       " + ase.getRequestId());
//        } catch (AmazonClientException ace) {
//            System.out.println("Caught an AmazonClientException, which means the client encountered "
//                    + "a serious internal problem while trying to communicate with AWS, "
//                    + "such as not being able to access the network.");
//            System.out.println("Error Message: " + ace.getMessage());
//        }
//		
//		return tableName;
//	}
	
}
