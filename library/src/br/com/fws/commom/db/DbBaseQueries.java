package br.com.fws.commom.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

public abstract class DbBaseQueries<T> {

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

	protected final T scanResultItemToObject(HashMap<String, Condition> scan) {
		List<T> list = scanResultToListObject(scan);
		if (list != null && list.size() > 0)
			return list.get(0);

		return null;
	};

	public abstract T scanResultItemToObejct(Map<String, AttributeValue> item);

	protected final List<T> scanResultToListObject(HashMap<String, Condition> scan) {
		List<T> list = new ArrayList<T>();
		ScanRequest request = null;

		if (scan == null) {
			request = new ScanRequest(tableName);
		} else {
			request = new ScanRequest(tableName).withScanFilter(scan);
		}

		ScanResult result = client.scan(request);
		if (result.getCount() > 0) {
			for (Map<String, AttributeValue> item : result.getItems()) {
				T value = scanResultItemToObejct(item);
				if (value != null)
					list.add(value);
			}
		}
		return list;
	};

	public T querySpecToObejct(QuerySpec querySpec) {
		List<T> list = querySpecToListObejct(querySpec);
		if (list != null && list.size() > 0)
			return list.get(0);

		return null;
	};

	public abstract T specItemToObejct(Item item);

	protected final List<T> querySpecToListObejct(QuerySpec querySpec) {
		List<T> list = new ArrayList<T>();
		ItemCollection<QueryOutcome> items = table.query(querySpec);
		Iterator<Item> iterator = items.iterator();
		Item item = null;
		while (iterator.hasNext()) {
			item = iterator.next();
			T object = specItemToObejct(item);
			if (object != null)
				list.add(object);
		}
		return list;
	}

	protected abstract Map<String, AttributeValue> newItemWithAttribute(T object);

}
