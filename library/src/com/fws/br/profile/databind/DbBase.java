package com.fws.br.profile.databind;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

public abstract class DbBase {

	protected AmazonDynamoDB client;
	protected DynamoDB dynamoDB;
	protected Table table = null;
	public String tableName = "";

	protected final void init() throws AmazonServiceException, AmazonClientException {
		ProfileCredentialsProvider credentialsProvider;

		try {
			credentialsProvider = new ProfileCredentialsProvider();
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (~/.aws/credentials), and is in valid format.", e);
		}

		try {

			client = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-2")
					.build();
			dynamoDB = new DynamoDB(client);
			table = GetTable();

		} catch (AmazonServiceException ase) {
			throw new AmazonServiceException("Caught an AmazonServiceException, which means your request made it "
					+ "to AWS, but was rejected with an error response for some reason." + "HTTP Status Code: "
					+ ase.getStatusCode() + "AWS Error Code: " + ase.getErrorCode() + "Error Type: "
					+ ase.getErrorType() + "Request ID: " + ase.getRequestId() + "Error Message: " + ase.getMessage());
		} catch (AmazonClientException ace) {
			throw new AmazonClientException("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with AWS, "
					+ "such as not being able to access the network.  Error Message: " + ace.getMessage());
		}
	}

	protected final Table GetTable() {
		if (!tableName.isEmpty()) {
			createTable();
		}

		return dynamoDB.getTable(tableName);
	}

	public abstract void createTable();

}
