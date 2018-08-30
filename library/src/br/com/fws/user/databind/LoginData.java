package br.com.fws.user.databind;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
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
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.amazonaws.services.dynamodbv2.util.TableUtils.TableNeverTransitionedToStateException;

import br.com.fws.commom.Encryption;
import br.com.fws.commom.db.DbBaseQueries;
import br.com.fws.user.entity.LoginInfo;

public class LoginData extends DbBaseQueries<LoginInfo> {
	private static final UUID uuid = UUID.randomUUID();

	public LoginData() throws Exception {
		super();
		tableName = "login";
		init();
	}

	@Override
	public void createTable() {
		ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
		keySchema.add(new KeySchemaElement().withAttributeName("userId").withKeyType(KeyType.HASH));
		keySchema.add(new KeySchemaElement().withAttributeName("login").withKeyType(KeyType.RANGE));

		ArrayList<AttributeDefinition> attrDefinitions = new ArrayList<AttributeDefinition>();
		attrDefinitions
				.add(new AttributeDefinition().withAttributeName("userId").withAttributeType(ScalarAttributeType.S));
		attrDefinitions
				.add(new AttributeDefinition().withAttributeName("login").withAttributeType(ScalarAttributeType.S));

		CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
				.withKeySchema(keySchema).withAttributeDefinitions(attrDefinitions)
				.withProvisionedThroughput(new ProvisionedThroughput(10L, 10L));

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
	public LoginInfo scanResultItemToObejct(Map<String, AttributeValue> item) {
		LoginInfo login = new LoginInfo();
		try {

			if (item.containsKey("userId"))
				if (!item.get("userId").equals(null))
					login.setUserId(item.get("userId").getS());

			if (item.containsKey("login"))
				if (!item.get("login").equals(null))
					login.setLogin(item.get("login").getS());

			if (item.containsKey("ssh"))
				if (!item.get("ssh").equals(null))
					login.setPassword(item.get("ssh").getS());

			if (item.containsKey("counter"))
				if (!item.get("counter").equals(null))
					login.setAccessCounter(Integer.parseInt(item.get("counter").getN()));

			if (item.containsKey("active"))
				if (!item.get("active").equals(null))
					login.setActive(item.get("active").getBOOL());

			if (item.containsKey("blocked"))
				if (!item.get("blocked").equals(null))
					login.setBlocked(item.get("blocked").getBOOL());

			if (item.containsKey("ultimoAcesso"))
				if (!item.get("ultimoAcesso").equals(null))
					login.setUltimoAcesso(item.get("ultimoAcesso").getS());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return login;
	}

	@Override
	public LoginInfo specItemToObejct(Item item) {
		LoginInfo login = null;
		try {
			if (item != null) {
				login = new LoginInfo();

				if (item.isPresent("login"))
					if (item.get("login") != null)
						login.setLogin(item.getString("login"));

				if (item.isPresent("ssh"))
					if (item.get("ssh") != null)
						login.setPassword(item.getString("ssh"));

				if (item.isPresent("userId"))
					if (item.get("userId") != null)
						login.setUserId(item.getString("userId"));

				if (item.isPresent("acessos"))
					if (item.get("acessos") != null)
						login.setAccessCounter(item.getInt("acessos"));

				if (item.isPresent("active"))
					if (item.get("active") != null)
						login.setActive(item.getBoolean("active"));

				if (item.isPresent("blocked"))
					if (item.get("blocked") != null)
						login.setBlocked(item.getBoolean("blocked"));

				if (item.isPresent("ultimoAcesso"))
					if (item.get("ultimoAcesso") != null)
						login.setUltimoAcesso(item.getString("ultimoAcesso"));

			}
		} catch (Exception e) {
			login = null;
		}
		return login;
	}

	/**
	 * @param login
	 * @return Login Info if data found or null if data not found
	 * @throws Exception
	 * @Details Query with ScanRequest example
	 */
	public LoginInfo getUserByLogin(String login) throws Exception {
		LoginInfo data = null;
		try {
			HashMap<String, Condition> scan = new HashMap<String, Condition>();
			scan.put("login", new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
					.withAttributeValueList(new AttributeValue().withS(login)));

			data = scanResultItemToObject(scan);

		} catch (Exception e) {
			throw e;
		}

		return data;
	}

	protected Map<String, AttributeValue> newItemWithAttribute(LoginInfo object) {
		Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
		item.put("login", new AttributeValue(object.getLogin()));
		item.put("ssh", new AttributeValue(object.getPassword()));
		item.put("userId", new AttributeValue(object.getUserId()));
		item.put("active", new AttributeValue().withBOOL(object.isActive()));
		item.put("acessos", new AttributeValue().withN(Integer.toString(object.getAccessCounter())));
		item.put("blocked", new AttributeValue().withBOOL(object.isBlocked()));
		return item;
	}

	/**
	 * @return List of active users
	 * @throws Exception
	 * @Details Query with ScanRequest example
	 */
	public List<LoginInfo> getDisabledUsers() throws Exception {
		List<LoginInfo> users = new ArrayList<LoginInfo>();

		try {
			HashMap<String, Condition> scan = new HashMap<String, Condition>();

			Condition condition = new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
					.withAttributeValueList(new AttributeValue().withBOOL(Boolean.FALSE));
			scan.put("active", condition);
			users = scanResultToListObject(scan);
		} catch (Exception e) {
			throw e;
		}
		return users;
	}

	/**
	 * @param user
	 *            info (user id, login)
	 * @return Number of times that login was verify
	 * @throws Exception
	 * @Details incremental update example
	 * 
	 */
	public final Integer addCountAccess(LoginInfo login) throws Exception {
		Integer counter = 1;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		try {
			UpdateItemSpec item = new UpdateItemSpec()
					.withPrimaryKey("userId", login.getUserId(), "login", login.getLogin())

					.withUpdateExpression("set ultimoAcesso = :data, acessos = acessos + :i")
					.withValueMap(
							new ValueMap().withString(":data", dateFormat.format(date).toString()).withNumber(":i", 1))
					.withReturnValues(ReturnValue.UPDATED_NEW);

			UpdateItemOutcome out = table.updateItem(item);
			if (out != null) {
				counter = out.getItem().getInt("acessos");
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
	final Boolean deleteLogin(LoginInfo login) throws Exception {
		try {
			DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
					.withPrimaryKey(new PrimaryKey("userId", login.getUserId(), "login", login.getLogin()));
			table.deleteItem(deleteItemSpec);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public Boolean addLogin(LoginInfo loginInfo) throws Exception {
		Boolean isComplete = false;

		loginInfo.setUserId(uuid.toString().replaceAll("-", ""));
		loginInfo.getUser().setUserId(loginInfo.getUserId());

		try {
			table.putItem(
					new Item().withPrimaryKey("userId", loginInfo.getUserId()).withString("login", loginInfo.getLogin())
							.withString("ssh", Encryption.Generate(loginInfo.getPassword())).withInt("acessos", 0)
							.withBoolean("active", true).withBoolean("blocked", false));

			UserData userData = new UserData();
			userData.addUser(loginInfo.getUser());

			isComplete = true;
		} catch (Exception e) {
			isComplete = false;
			throw e;
		}
		return isComplete;
	}

}
