package com.fws.br.profile.databind.dynamoDb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.fws.br.profile.databind.DynamoDbBase;
import com.fws.br.profile.entities.UserInfo;

public class UserData extends DynamoDbBase {

	public UserData() throws Exception {
		super();
		tableName = "users";
		init();
	}

	public Boolean addUser(List<UserInfo> lstUser) throws Exception {
		Boolean isComplete = false;

		try {
			for (UserInfo userInfo : lstUser) {
				table.putItem(new Item().withPrimaryKey("userId", userInfo.getUserId(), "login", userInfo.getLogin())
						.withString("birthDate", userInfo.getBirthDate()).withString("email", userInfo.getEmail())
						.withString("login", userInfo.getLogin()).withString("name", userInfo.getName())
						.withString("ssh", userInfo.getPassword()).withInt("userId", userInfo.getUserId())
						.withBoolean("active", userInfo.isActive()).withBoolean("blocked", userInfo.isBlocked())
						.withInt("counter", 0));
			}

			isComplete = true;
		} catch (Exception e) {
			isComplete = false;
			throw e;
		}
		return isComplete;
	}

	public Boolean addUserWithAttributeValuesMethod(List<UserInfo> lstUsers) throws Exception {
		Boolean isComplete = false;

		try {
			for (UserInfo userInfo : lstUsers) {
				Map<String, AttributeValue> item = newItemWithAttribute(userInfo);
				PutItemRequest putItemRequest = new PutItemRequest(tableName, item);
				client.putItem(putItemRequest);
			}
			isComplete = true;
		} catch (Exception e) {
			isComplete = false;
			throw e;
		}
		return isComplete;
	}

	// Query with GetItemSpec example (using key(s))
	public Usuario getUserByIdAndLogin(int id, String login) throws Exception {
		Usuario user = new Usuario();

		GetItemSpec spec = new GetItemSpec().withPrimaryKey("id", id, "login", login);

		Item out = table.getItem(spec);

		if (out != null) {
			user.setUsuarioId(out.getInt("id"));
			user.setBirthDate(out.getString("birthDate"));
			user.setLogin(out.getString("login"));
			user.setNome(out.getString("nome"));
			user.setAtivo(out.getBoolean("ativo"));
		}
		return user;
	}

	// Query with ScanRequest example
	public List<Usuario> getUserByName(String name) throws Exception {
		List<Usuario> users = new ArrayList<Usuario>();

		try {
			HashMap<String, Condition> scan = new HashMap<String, Condition>();

			Condition condition = new Condition().withComparisonOperator(ComparisonOperator.CONTAINS.toString())
					.withAttributeValueList(new AttributeValue().withS(name));
			scan.put("nome", condition);

			ScanRequest request = new ScanRequest(tableName).withScanFilter(scan);
			ScanResult result = client.scan(request);

			for (Map<String, AttributeValue> item : result.getItems()) {
				Usuario user = new Usuario();
				user.setUsuarioId(Integer.parseInt(item.get("id").getN()));
				user.setNome(item.get("nome").getS());
				user.setLogin(item.get("login").getS());
				user.setBirthDate(item.get("birthDate").getS());
				user.setAtivo(item.get("ativo").getBOOL());
				users.add(user);
			}

		} catch (Exception e) {
			throw e;
		}

		return users;
	}

	// Query with QuerySpec example
	public List<Usuario> getUserByQueryId(int id) throws Exception {

		List<Usuario> users = new ArrayList<Usuario>();

		HashMap<String, String> nameMap = new HashMap<String, String>();
		nameMap.put("#pk", "id");

		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put(":id", id);

		QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#pk = :id").withNameMap(nameMap)
				.withValueMap(valueMap);

		Iterator<Item> iterator = null;
		Item item = null;

		ItemCollection<QueryOutcome> items = table.query(querySpec);
		iterator = items.iterator();

		while (iterator.hasNext()) {
			item = iterator.next();
			Usuario user = new Usuario();
			user.setUsuarioId(item.getInt("id"));
			user.setNome(item.getString("nome"));
			user.setLogin(item.getString("login"));
			user.setBirthDate(item.getString("birthDate"));
			user.setAtivo(item.getBoolean("ativo"));
			users.add(user);
		}

		return users;
	}

	// Query with ScanRequest example
	public List<Usuario> getDisabledUsers() throws Exception {
		List<Usuario> users = new ArrayList<Usuario>();

		try {
			HashMap<String, Condition> scan = new HashMap<String, Condition>();

			Condition condition = new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
					.withAttributeValueList(new AttributeValue().withBOOL(Boolean.FALSE));
			scan.put("ativo", condition);

			ScanRequest request = new ScanRequest(tableName).withScanFilter(scan);
			ScanResult result = client.scan(request);

			for (Map<String, AttributeValue> item : result.getItems()) {
				Usuario user = new Usuario();
				user.setUsuarioId(Integer.parseInt(item.get("id").getN()));
				user.setLogin(item.get("login").getS());
				users.add(user);
			}
		} catch (Exception e) {
			throw e;
		}

		return users;
	}

	// Query with ScanRequest example
	public List<Usuario> getAllUsers() throws Exception {
		List<Usuario> users = new ArrayList<Usuario>();

		try {
			ScanRequest request = new ScanRequest(tableName);
			ScanResult result = client.scan(request);

			for (Map<String, AttributeValue> item : result.getItems()) {
				Usuario user = new Usuario();
				user.setUsuarioId(Integer.parseInt(item.get("id").getN()));
				user.setNome(item.get("nome").getS());
				user.setLogin(item.get("login").getS());
				user.setBirthDate(item.get("birthDate").getS());
				user.setAtivo(item.get("ativo").getBOOL());
				users.add(user);
			}

		} catch (Exception e) {
			throw e;
		}
		return users;
	}

	// Update with UpdateItemSpec example
	public UserInfo updateUser(UserInfo user) throws Exception {

		UserInfo updatedUser = new UserInfo();

		try {
			UpdateItemSpec item = new UpdateItemSpec()
					.withPrimaryKey("userId", user.getUserId(), "login", user.getLogin())
					.withUpdateExpression("set name = :newNome, active= :newAtivo")
					.withValueMap(
							new ValueMap().with(":newName", user.getName()).withBoolean(":newActive", user.isActive()))
					.withReturnValues(ReturnValue.UPDATED_NEW);

			// Conditional Update example
			// UpdateItemSpec updateItemSpec = new UpdateItemSpec()
			// .withPrimaryKey(new PrimaryKey("year", year, "title",
			// title)).withUpdateExpression("remove info.actors[0]")
			// .withConditionExpression("size(info.actors) >
			// :num").withValueMap(new
			// ValueMap().withNumber(":num", 3))
			// .withReturnValues(ReturnValue.UPDATED_NEW);

			// Update field with Increment option
			// UpdateItemSpec updateItemSpec = new
			// UpdateItemSpec().withPrimaryKey("year", year, "title", title)
			// .withUpdateExpression("set info.rating = info.rating + :val")
			// .withValueMap(new ValueMap().withNumber(":val",
			// 1)).withReturnValues(ReturnValue.UPDATED_NEW);

			UpdateItemOutcome out = table.updateItem(item);

			if (out != null) {
				updatedUser.setName(out.getItem().getString("name"));
				updatedUser.setActive(out.getItem().getBoolean("active"));
			}
		} catch (Exception e) {
			throw e;
		}
		return updatedUser;
	}

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

	private static Map<String, AttributeValue> newItemWithAttribute(UserInfo userInfo) {
		Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
		item.put("birthDate", new AttributeValue(userInfo.getBirthDate()));
		item.put("email", new AttributeValue(userInfo.getEmail()));
		item.put("login", new AttributeValue(userInfo.getLogin()));
		item.put("name", new AttributeValue(userInfo.getName()));
		item.put("ssh", new AttributeValue(userInfo.getPassword()));
		item.put("userId", new AttributeValue().withN(Integer.toString(userInfo.getUserId())));
		item.put("active", new AttributeValue().withBOOL(userInfo.isActive()));
		item.put("blocked", new AttributeValue().withBOOL(userInfo.isBlocked()));
		item.put("counter", new AttributeValue().withN(Integer.toString(0)));
		return item;
	}

	@Override
	public void createTable() {
		CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
				.withKeySchema(new KeySchemaElement().withAttributeName("userId").withKeyType(KeyType.HASH))
				.withKeySchema(new KeySchemaElement().withAttributeName("login").withKeyType(KeyType.RANGE))
				.withAttributeDefinitions(
						new AttributeDefinition().withAttributeName("userId").withAttributeType(ScalarAttributeType.N))
				.withAttributeDefinitions(
						new AttributeDefinition().withAttributeName("login").withAttributeType(ScalarAttributeType.S))
				.withProvisionedThroughput(new ProvisionedThroughput(10L, 10L));

		TableUtils.createTableIfNotExists(client, createTableRequest);
		try {

			TableUtils.waitUntilActive(client, tableName);
		} catch (TableNeverTransitionedToStateException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Override
	// public void createTable() {
	// // CreateTableRequest createTableRequest = new
	// // CreateTableRequest().withTableName(tableName)
	// // .withKeySchema(new
	// // KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH))
	// // .withAttributeDefinitions(
	// // new
	// //
	// AttributeDefinition().withAttributeName("id").withAttributeType(ScalarAttributeType.N))
	// // .withProvisionedThroughput(
	// // new
	// //
	// ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));
	// }

}
