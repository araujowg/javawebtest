package br.com.fws.user.databind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
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
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.amazonaws.services.dynamodbv2.util.TableUtils.TableNeverTransitionedToStateException;

import br.com.fws.commom.db.DbBaseQueries;
import br.com.fws.user.entity.LoginInfo;
import br.com.fws.user.entity.UserInfo;

/**
 * @author Quantum
 */

public class UserData extends DbBaseQueries<UserInfo> {

	public UserData() throws Exception {
		super();
		tableName = "users";
		init();
	}

	@Override
	public void createTable() {
		ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
		keySchema.add(new KeySchemaElement().withAttributeName("userId").withKeyType(KeyType.HASH));

		ArrayList<AttributeDefinition> attrDefinitions = new ArrayList<AttributeDefinition>();
		attrDefinitions
				.add(new AttributeDefinition().withAttributeName("userId").withAttributeType(ScalarAttributeType.S));

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
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserInfo scanResultItemToObejct(Map<String, AttributeValue> item) {
		UserInfo user = new UserInfo();
		try {

			if (item.containsKey("email"))
				if (item.get("email") != null)
					user.setEmail(item.get("email").getS());

			if (item.containsKey("name"))
				if (item.get("name") != null)
					user.setName(item.get("name").getS());

			if (item.containsKey("userId"))
				if (item.get("userId") != null)
					user.setUserId(item.get("userId").getS());

			if (item.containsKey("birthdate"))
				if (item.get("birthdate") != null)
					user.setBirthDate(item.get("birthdate").getS());

		} catch (Exception e) {
			user = null;
		}
		return user;
	}

	@Override
	public UserInfo specItemToObejct(Item item) {
		UserInfo user = new UserInfo();
		try {
			if (item.isPresent("email"))
				if (item.get("email") != null)
					user.setEmail(item.getString("email"));

			if (item.isPresent("name"))
				if (item.get("name") != null)
					user.setName(item.getString("name"));

			if (item.isPresent("userId"))
				if (item.get("userId") != null)
					user.setUserId(item.getString("userId"));

			if (item.isPresent("birthdate"))
				if (item.get("birthdate") != null)
					user.setBirthDate(item.getString("birthdate"));

		} catch (Exception e) {
			user = null;
		}
		return user;
	}

	public final Map<String, AttributeValue> newItemWithAttribute(UserInfo object) {
		Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
		item.put("email", new AttributeValue(object.getEmail()));
		item.put("name", new AttributeValue(object.getName()));
		item.put("userId", new AttributeValue(object.toString()));
		return item;
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
		try {
			table.putItem(
					new Item().withPrimaryKey("userId", userInfo.getUserId()).withString("email", userInfo.getEmail()));
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
	 * @Details Query with GetItemSpec example (using key(s))
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
	public List<UserInfo> getListOfUserByName(String name) throws Exception {
		List<UserInfo> users = new ArrayList<UserInfo>();
		try {
			HashMap<String, Condition> scan = new HashMap<String, Condition>();

			Condition condition = new Condition().withComparisonOperator(ComparisonOperator.CONTAINS.toString())
					.withAttributeValueList(new AttributeValue().withS(name));
			scan.put("nome", condition);
			users = scanResultToListObject(scan);
		} catch (Exception e) {
			throw e;
		}
		return users;
	}

	/**
	 * @param email
	 * @return User info if data found or null if data not found
	 * @throws Exception
	 * @Details Query with ScanRequest example
	 */
	public UserInfo getUserByName(String name) throws Exception {
		UserInfo user = null;
		try {
			HashMap<String, String> nameMap = new HashMap<String, String>();
			nameMap.put("#key", "name");

			HashMap<String, Object> valueMap = new HashMap<String, Object>();
			valueMap.put(":name", name);

			QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#key = :name").withNameMap(nameMap)
					.withValueMap(valueMap);

			user = querySpecToObejct(querySpec);

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
			HashMap<String, Condition> scan = new HashMap<String, Condition>();

			scan.put("email", new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
					.withAttributeValueList(new AttributeValue().withS(email)));

			user = scanResultItemToObject(scan);

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
	 * @Details Query with QuerySpec example
	 * 
	 */
	public UserInfo getUserByQueryId(int id) throws Exception {
		UserInfo user = null;

		try {
			HashMap<String, String> nameMap = new HashMap<String, String>();
			nameMap.put("#pk", "id");

			HashMap<String, Object> valueMap = new HashMap<String, Object>();
			valueMap.put(":id", id);

			QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#pk = :id").withNameMap(nameMap)
					.withValueMap(valueMap);

			user = querySpecToObejct(querySpec);

		} catch (Exception e) {
			throw e;
		}
		return user;
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
			UpdateItemSpec item = new UpdateItemSpec().withPrimaryKey("userId", user.getUserId())
					.withUpdateExpression("set name = :newNome, birthdate = :birthdate")
					.withValueMap(
							new ValueMap().with(":newName", user.getName()).with(":birthdate", user.getBirthDate()))
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
	 * @return List of all Users
	 * @throws Exception
	 * @Details Query with ScanRequest example
	 */
	public List<UserInfo> getAllUsers() throws Exception {
		List<UserInfo> users = new ArrayList<UserInfo>();
		try {
			users = scanResultToListObject(null);
		} catch (Exception e) {
			throw e;
		}
		return users;
	}

	/**
	 * @return Success or failure
	 * @throws Exception
	 *
	 *             Delete all disabled users
	 *
	 */
	public void deleteDisabledUsers() throws Exception {
		try {
			LoginData loginData = new LoginData();
			List<LoginInfo> logins = loginData.getDisabledUsers();

			for (LoginInfo login : logins) {
				if (deleteUser(new UserInfo(login.getUserId(), null, null, null, null))) {
					loginData.deleteLogin(login);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private Boolean deleteUser(UserInfo user) {
		try {
			DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
					.withPrimaryKey(new PrimaryKey("userId", user.getUserId()));
			table.deleteItem(deleteItemSpec);

			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
