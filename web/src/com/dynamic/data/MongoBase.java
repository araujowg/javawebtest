package com.dynamic.data;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoConfigurationException;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author Quantum
 *
 */
public class MongoBase {

	MongoClientURI client = null;
	MongoClient mongoClient = null;
	
	public MongoBase(){
		
	}	
	
	/**
	 * Get all documents from Collection
	 *
	 */
	
	private void ConnectionInitialize(){
		
//		String uri = "mongodb://araujo_wg:12fatima34wag@cluster0-shard-00-00-xbs5a.mongodb.net:27017,cluster0-shard-00-01-xbs5a.mongodb.net:27017,cluster0-shard-00-02-xbs5a.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true";
		String uri = "mongodb://araujo_wg:12fatima34wag@ds127899.mlab.com:27899/gac";
		client = new MongoClientURI(uri);
		mongoClient = new MongoClient(client);
	}

	private void CloseConnection(){
		client = null;
		mongoClient = null;
	}

	
	public List<Document> getAll() throws MongoConfigurationException{
		List<Document>  data  = new ArrayList<Document>();
		try {
			ConnectionInitialize();
			
			MongoDatabase dbase = mongoClient.getDatabase(client.getDatabase());
			MongoCollection<Document> collection =  dbase.getCollection("usuario");
			data = (List<Document>)collection.find().into(new ArrayList<Document>());
			
		}catch(MongoException mex){
			throw mex;
		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		finally {
			CloseConnection();
		}
		
		
		return data;
	}
	
	 
	
}
