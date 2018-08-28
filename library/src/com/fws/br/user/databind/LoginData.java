package com.fws.br.user.databind;

import java.util.ArrayList;

import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.amazonaws.services.dynamodbv2.util.TableUtils.TableNeverTransitionedToStateException;

public class LoginData extends DbBase {

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
}
