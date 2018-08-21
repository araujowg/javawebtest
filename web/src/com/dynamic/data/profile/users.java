package com.dynamic.data.profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.dynamic.ent.Usuario;

public class users extends com.dynamic.data.DynamoDb {
	
	private String tableName = "users";
	
	public String addUser(List<Usuario> lstUser) throws Exception{
		String data = "";
		init();
		
		try {
			Table table = GetTable();
			for (Usuario usuario : lstUser) {
				Map<String, AttributeValue> item = newItem(usuario);
				PutItemOutcome putItemResult = table.putItem(new Item().withMap("user", item));
				data += putItemResult;
			}
			
		} catch (Exception e) {
			throw e;
		}
		return data;
	}

	private static Map<String, AttributeValue> newItem(Usuario user) {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("id", new AttributeValue().withS(String.valueOf(user.getUsuarioId())));
        item.put("nome", new AttributeValue().withS(user.getNome()));
        item.put("ativo", new AttributeValue().withBOOL(user.isAtivo()));
        return item;
    }

	
	@Override
	public Table GetTable()throws Exception {
		createTable();
		Table table = dynamoDB.getTable(tableName);
		return table;
	}

	private void createTable() throws Exception{
        	CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
                .withKeySchema(new KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH))
                .withAttributeDefinitions(new AttributeDefinition().withAttributeName("id").withAttributeType(ScalarAttributeType.S))
//                .withAttributeDefinitions(new AttributeDefinition().withAttributeName("nome").withAttributeType(ScalarAttributeType.S))
//                .withAttributeDefinitions(new AttributeDefinition().withAttributeName("ativo").withAttributeType(ScalarAttributeType.B))
                .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));
            TableUtils.createTableIfNotExists(client, createTableRequest);
            TableUtils.waitUntilActive(client, tableName);
	}
	
//	private void createTable(){
//		
//	}
	
	
//	private static Map<String, AttributeValue> newItem(String name, int year, String rating, String... fans) {
//        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
//        item.put("name", new AttributeValue(name));
//        item.put("year", new AttributeValue().withN(Integer.toString(year)));
//        item.put("rating", new AttributeValue(rating));
//        item.put("fans", new AttributeValue().withSS(fans));
//
//        return item;
//    }

	
}
