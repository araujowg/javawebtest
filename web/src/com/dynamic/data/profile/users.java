package com.dynamic.data.profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.dynamic.ent.Usuario;

public class users extends com.dynamic.data.DynamoDb {

	public users() throws Exception {
		super();
		tableName = "users";
		init();
	}

	public Boolean addUser(List<Usuario> lstUser) throws Exception {
		Boolean isComplete = false;
		// init();
		try {
			for (Usuario usuario : lstUser) {
				Map<String, Object> item = newItem(usuario);
				table.putItem(new Item().withPrimaryKey("id", usuario.getUsuarioId(), "login", usuario.getLogin())
						.withMap("user", item));
			}

			isComplete = true;
		} catch (Exception e) {
			isComplete = false;
			throw e;
		}
		return isComplete;
	}

	public Boolean addUserWithAttributeValuesMethod(List<Usuario> lstUsers) throws Exception {
		Boolean isComplete = false;
		// init();
		try {
			for (Usuario usuario : lstUsers) {
				Map<String, AttributeValue> item = newItemWithAttribute(usuario);
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

	public List<Usuario> getUserByName(String name) throws Exception {
		List<Usuario> users = new ArrayList<Usuario>();
		// init();

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

	public List<Usuario> getUserByQueryId(int id) {

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
			user.setAtivo(item.getBOOL("ativo"));
			users.add(user);
		}

		return users;
	}

	private static Map<String, Object> newItem(Usuario user) {
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("id", String.valueOf(user.getUsuarioId()));
		item.put("nome", user.getNome());
		item.put("ativo", user.isAtivo());
		item.put("login", user.getLogin());
		item.put("birthDate", user.getBirthDate());
		return item;
	}

	private static Map<String, AttributeValue> newItemWithAttribute(Usuario user) {
		Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
		item.put("nome", new AttributeValue(user.getNome()));
		item.put("login", new AttributeValue(user.getLogin()));
		item.put("birthDate", new AttributeValue(user.getBirthDate()));
		item.put("id", new AttributeValue().withN(Integer.toString(user.getUsuarioId())));
		item.put("ativo", new AttributeValue().withBOOL(user.isAtivo()));

		return item;
	}

	@Override
	public void createTable() throws Exception {
		// CreateTableRequest createTableRequest = new
		// CreateTableRequest().withTableName(tableName)
		// .withKeySchema(new
		// KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH))
		// .withAttributeDefinitions(
		// new
		// AttributeDefinition().withAttributeName("id").withAttributeType(ScalarAttributeType.N))
		// .withProvisionedThroughput(
		// new
		// ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));
		CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
				.withKeySchema(new KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH))
				.withKeySchema(new KeySchemaElement().withAttributeName("login").withKeyType(KeyType.RANGE))
				.withAttributeDefinitions(
						new AttributeDefinition().withAttributeName("id").withAttributeType(ScalarAttributeType.N))
				.withAttributeDefinitions(
						new AttributeDefinition().withAttributeName("login").withAttributeType(ScalarAttributeType.S))
				.withProvisionedThroughput(new ProvisionedThroughput(10L, 10L));

		TableUtils.createTableIfNotExists(client, createTableRequest);
		TableUtils.waitUntilActive(client, tableName);
	}

	// private static Map<String, AttributeValue> newItem(String name, int year,
	// String rating, String... fans) {
	// Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
	// item.put("name", new AttributeValue(name));
	// item.put("year", new AttributeValue().withN(Integer.toString(year)));
	// item.put("rating", new AttributeValue(rating));
	// item.put("fans", new AttributeValue().withSS(fans));
	//
	// return item;
	// }

}
