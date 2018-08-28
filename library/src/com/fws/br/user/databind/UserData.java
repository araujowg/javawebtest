package com.fws.br.user.databind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.amazonaws.services.dynamodbv2.util.TableUtils.TableNeverTransitionedToStateException;
import com.fws.br.user.entity.UserInfo;

/**
 * @author Quantum
 */

public class UserData extends DbBase {

	public UserData() throws Exception {
		super();
		tableName = "users";
		init();
	}

	/**
	 * @param Expects
	 *            user info.
	 * @return Success or failure
	 * @throws Exception
	 * @Details Using table putItem method
	 * 
	 */
	public Boolean addUser(UserInfo userInfo) throws Exception {
		Boolean isComplete = false;
		UUID uuid = UUID.randomUUID();
		try {
			table.putItem(new Item().withPrimaryKey("userId", userInfo.getUserId(), "login", userInfo.getLogin())
					.withString("email", userInfo.getEmail()).withString("login", userInfo.getLogin())
					.withString("name", userInfo.getName()).withString("ssh", userInfo.getPassword())
					.withString("userId", uuid.toString()).withBoolean("active", userInfo.isActive())
					.withBoolean("blocked", userInfo.isBlocked()).withInt("counter", 0));
			isComplete = true;
		} catch (Exception e) {
			isComplete = false;
			throw e;
		}
		return isComplete;
	}

	/**
	 * @param Expects
	 *            user info.
	 * @return Success or failure
	 * @throws Exception
	 * @Details Using PutItemRequest method
	 */
	public Boolean addUserWithAttributeValuesMethod(UserInfo userInfo) throws Exception {
		try {
			Map<String, AttributeValue> item = newItemWithAttribute(userInfo);
			PutItemRequest putItemRequest = new PutItemRequest(tableName, item);
			client.putItem(putItemRequest);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param user
	 *            id
	 * @param login
	 * @return User info
	 * @throws Exception
	 * 
	 *             Query with GetItemSpec example (using key(s))
	 * 
	 */
	public UserInfo getUserByIdAndLogin(int id, String login) throws Exception {
		GetItemSpec spec = new GetItemSpec().withPrimaryKey("id", id, "login", login);
		Item item = table.getItem(spec);
		return specItemToObejct(item);
	}

	/**
	 * @param name
	 * @return
	 * @throws Exception
	 * 
	 * @Details Query with ScanRequest example
	 * 
	 */
	public List<UserInfo> getUserByName(String name) throws Exception {
		List<UserInfo> users = new ArrayList<UserInfo>();

		try {
			HashMap<String, Condition> scan = new HashMap<String, Condition>();

			Condition condition = new Condition().withComparisonOperator(ComparisonOperator.CONTAINS.toString())
					.withAttributeValueList(new AttributeValue().withS(name));
			scan.put("nome", condition);

			ScanRequest request = new ScanRequest(tableName).withScanFilter(scan);
			ScanResult result = client.scan(request);

			users = scanResultToObject(result);

		} catch (Exception e) {
			throw e;
		}

		return users;
	}

	/**
	 * @param login
	 * @return User info if data found or null if data not found
	 * @throws Exception
	 * @Details Query with ScanRequest example
	 */
	public UserInfo getUserByLoginOrEmail(String login, String email) throws Exception {
		UserInfo user = null;
		try {

			HashMap<String, Condition> scan = new HashMap<String, Condition>();

			if (!login.isEmpty())
				scan.put("login", new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
						.withAttributeValueList(new AttributeValue().withS(login)));

			if (!email.isEmpty())
				scan.put("email", new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
						.withAttributeValueList(new AttributeValue().withS(email)));

			ScanRequest request = new ScanRequest(tableName).withScanFilter(scan);
			ScanResult result = client.scan(request);

			if (result != null && result.getCount() > 0)
				user = scanResultToObject(result).get(0);

		} catch (Exception e) {
			throw e;
		}

		return user;
	}

	/**
	 * @param email
	 * @return User info if data found or null if data not found
	 * @throws Exception
	 * @Details Query with ScanRequest example
	 */
	public UserInfo getUserByEmail(String email) throws Exception {
		UserInfo user = null;
		try {
			HashMap<String, String> nameMap = new HashMap<String, String>();
			nameMap.put("#key", "email");

			HashMap<String, Object> valueMap = new HashMap<String, Object>();
			valueMap.put(":email", email);

			QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#key = :email").withNameMap(nameMap)
					.withValueMap(valueMap);

			ItemCollection<QueryOutcome> items = table.query(querySpec);
			Iterator<Item> iterator = items.iterator();

			Item item = null;

			while (iterator.hasNext()) {
				item = iterator.next();
				user = specItemToObejct(item);
			}
		} catch (Exception e) {
			throw e;
		}

		return user;
	}

	/**
	 * @param user
	 *            id
	 * @return User info
	 * @throws Exception
	 * 
	 *             Query with QuerySpec example
	 * 
	 */
	public List<UserInfo> getUserByQueryId(int id) throws Exception {

		List<UserInfo> users = new ArrayList<UserInfo>();

		HashMap<String, String> nameMap = new HashMap<String, String>();
		nameMap.put("#pk", "id");

		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put(":id", id);

		QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#pk = :id").withNameMap(nameMap)
				.withValueMap(valueMap);

		ItemCollection<QueryOutcome> items = table.query(querySpec);
		Iterator<Item> iterator = items.iterator();

		Item item = null;

		while (iterator.hasNext()) {
			item = iterator.next();
			UserInfo user = specItemToObejct(item);
			if (user != null)
				users.add(user);
		}

		return users;
	}

	/**
	 * @return List of active users
	 * @throws Exception
	 * @Details Query with ScanRequest example
	 */
	public List<UserInfo> getDisabledUsers() throws Exception {
		List<UserInfo> users = new ArrayList<UserInfo>();

		try {
			HashMap<String, Condition> scan = new HashMap<String, Condition>();

			Condition condition = new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
					.withAttributeValueList(new AttributeValue().withBOOL(Boolean.FALSE));
			scan.put("active", condition);

			ScanRequest request = new ScanRequest(tableName).withScanFilter(scan);
			ScanResult result = client.scan(request);

			users = scanResultToObject(result);

		} catch (Exception e) {
			throw e;
		}

		return users;
	}

	/**
	 * @return List of all Users
	 * @throws Exception
	 * @Details Query with ScanRequest example
	 */
	public List<UserInfo> getAllUsers() throws Exception {
		List<UserInfo> users = new ArrayList<UserInfo>();
		try {
			ScanRequest request = new ScanRequest(tableName);
			ScanResult result = client.scan(request);
			users = scanResultToObject(result);

		} catch (Exception e) {
			throw e;
		}
		return users;
	}

	/**
	 * @param user
	 *            info
	 * @return Success or failure
	 * @throws Exception
	 * @Details Update with UpdateItemSpec example
	 */
	public Boolean updateUser(UserInfo user) throws Exception {

		try {
			UpdateItemSpec item = new UpdateItemSpec()
					.withPrimaryKey("userId", user.getUserId(), "login", user.getLogin())
					.withUpdateExpression("set name = :newNome")
					.withValueMap(new ValueMap().with(":newName", user.getName()))
					// .withBoolean(":newActive", user.isActive()))
					.withReturnValues(ReturnValue.UPDATED_NEW);

			UpdateItemOutcome out = table.updateItem(item);

			if (out != null) {
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

	/**
	 * @param user
	 *            info (user id, login)
	 * @return Number of times that login was verify
	 * @throws Exception
	 * @Details incremental update example
	 * 
	 */
	public Integer addCountAccess(UserInfo user) throws Exception {
		Integer counter = 1;
		try {
			UpdateItemSpec item = new UpdateItemSpec()
					.withPrimaryKey("userId", user.getUserId(), "login", user.getLogin())

					.withUpdateExpression("set counter = counter + :i")
					.withValueMap(new ValueMap().withNumber(":i", counter)).withReturnValues(ReturnValue.UPDATED_NEW);

			UpdateItemOutcome out = table.updateItem(item);
			if (out != null) {
				counter = out.getItem().getInt("counter");
			} else {
				counter = 0;
			}

		} catch (Exception e) {
			throw e;
		}
		return counter;
	}

	/**
	 * @return Success or failure
	 * @throws Exception
	 * 
	 *             Delete all disabled users
	 * 
	 */
	public Boolean deleteDisabledUsers() throws Exception {
		try {
			List<UserInfo> users = getDisabledUsers();
			for (UserInfo user : users) {
				DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
						.withPrimaryKey(new PrimaryKey("userId", user.getUserId(), "login", user.getLogin()));
				table.deleteItem(deleteItemSpec);
			}
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param userInfo
	 * @return mapped and processed data to insert in table
	 */
	private static Map<String, AttributeValue> newItemWithAttribute(UserInfo userInfo) {
		UUID uuid = UUID.randomUUID();
		Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
		item.put("email", new AttributeValue(userInfo.getEmail()));
		item.put("login", new AttributeValue(userInfo.getLogin()));
		item.put("name", new AttributeValue(userInfo.getName()));
		item.put("ssh", new AttributeValue(userInfo.getPassword()));
		item.put("userId", new AttributeValue().withS(uuid.toString()));
		item.put("active", new AttributeValue().withBOOL(userInfo.isActive()));
		item.put("blocked", new AttributeValue().withBOOL(userInfo.isBlocked()));
		item.put("counter", new AttributeValue().withN(Integer.toString(0)));
		return item;
	}

	@Override
	public void createTable() {

		ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
		keySchema.add(new KeySchemaElement().withAttributeName("userId").withKeyType(KeyType.HASH));
		keySchema.add(new KeySchemaElement().withAttributeName("login").withKeyType(KeyType.RANGE));
		// keySchema.add(new
		// KeySchemaElement().withAttributeName("email").withKeyType(KeyType.RANGE));

		ArrayList<AttributeDefinition> attrDefinitions = new ArrayList<AttributeDefinition>();
		attrDefinitions
				.add(new AttributeDefinition().withAttributeName("userId").withAttributeType(ScalarAttributeType.S));
		attrDefinitions
				.add(new AttributeDefinition().withAttributeName("login").withAttributeType(ScalarAttributeType.S));
		// attrDefinitions .add(new
		// AttributeDefinition().withAttributeName("email").withAttributeType(ScalarAttributeType.S));

		CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
				.withKeySchema(keySchema).withAttributeDefinitions(attrDefinitions)
				.withProvisionedThroughput(new ProvisionedThroughput(10L, 10L));

		// CreateTableRequest createTableRequest = new
		// CreateTableRequest().withTableName(tableName)
		// .withKeySchema(new
		// KeySchemaElement().withAttributeName("userId").withKeyType(KeyType.HASH))
		// .withAttributeDefinitions(
		// new
		// AttributeDefinition().withAttributeName("login").withAttributeType(ScalarAttributeType.S))
		// .withAttributeDefinitions(
		// new
		// AttributeDefinition().withAttributeName("email").withAttributeType(ScalarAttributeType.S))
		// .withProvisionedThroughput(new ProvisionedThroughput(10L, 10L));

		TableUtils.createTableIfNotExists(client, createTableRequest);
		try {
			TableUtils.waitUntilActive(client, tableName);
		} catch (TableNeverTransitionedToStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param Result
	 *            of dynamoDb query executed using ScanResult object
	 * @param List
	 *            of UserInfo objects
	 * @throws Exception
	 */
	private List<UserInfo> scanResultToObject(ScanResult result) throws Exception {
		List<UserInfo> users = new ArrayList<UserInfo>();
		try {
			for (Map<String, AttributeValue> item : result.getItems()) {
				UserInfo user = new UserInfo();

				if (!item.get("email").isNULL())
					user.setEmail(item.get("email").getS());

				if (!item.get("login").isNULL())
					user.setLogin(item.get("login").getS());

				if (!item.get("name").isNULL())
					user.setName(item.get("name").getS());

				if (!item.get("ssh").isNULL())
					user.setPassword(item.get("ssh").getS());

				if (!item.get("userId").isNULL())
					user.setUserId(item.get("userId").getS());

				if (!item.get("counter").isNULL())
					user.setAccessCounter(Integer.getInteger(item.get("counter").getN()));

				if (!item.get("active").isNULL())
					user.setActive(item.get("active").getBOOL());

				if (!item.get("blocked").isNULL())
					user.setBlocked(item.get("blocked").getBOOL());

				users.add(user);
			}

		} catch (Exception e) {
			users.clear();
		}
		return users;
	}

	/**
	 * @param item
	 * @return User info attributes
	 * @throws Exception
	 */
	private UserInfo specItemToObejct(Item item) throws Exception {
		UserInfo user = null;
		try {
			if (item != null) {
				user = new UserInfo();

				if (item.get("email") != null)
					user.setEmail(item.getString("email"));

				if (item.get("login") != null)
					user.setLogin(item.getString("login"));

				if (item.get("name") != null)
					user.setName(item.getString("name"));

				if (item.get("ssh") != null)
					user.setPassword(item.getString("ssh"));

				if (item.get("userId") != null)
					user.setUserId(item.getString("userId"));

				if (item.get("counter") != null)
					user.setAccessCounter(item.getInt("counter"));

				if (item.get("active") != null)
					user.setActive(item.getBoolean("active"));

				if (item.get("blocked") != null)
					user.setBlocked(item.getBoolean("blocked"));
			}
		} catch (Exception e) {
			throw e;
		}
		return user;
	}
}
