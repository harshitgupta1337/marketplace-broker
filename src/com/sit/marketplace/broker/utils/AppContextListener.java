package com.sit.marketplace.broker.utils;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.sit.marketplace.broker.provider.AbstractProvider;
import com.sit.marketplace.broker.provider.EucalyptusProvider;
import com.sit.marketplace.broker.registry.ProviderRegistry;
 
@WebListener
public class AppContextListener implements ServletContextListener {
 
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	try{
			MongoClient client = new MongoClient(Utils.MONGODB_HOST, 27017);
			DB database = client.getDB(Utils.MONGODB_DB);
			DBCollection providers= database.getCollection(Utils.MONGODB_PROVIDERS_COLLECTION);
			
			DBCursor cursor = providers.find();
			while(cursor.hasNext()){
				DBObject mongoProvider = cursor.next();
				if(mongoProvider.get("cloudType").equals("EUCALYPTUS")){
					insertEucalyptusProvider(mongoProvider);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }
    private void insertEucalyptusProvider(DBObject mongoProvider){
    	String providerId = mongoProvider.get("providerId").toString();
    	String name = mongoProvider.get("name").toString();
    	String url = mongoProvider.get("url").toString();
    	int port = Integer.parseInt(mongoProvider.get("port").toString());
    	String awsAccessKeyId = mongoProvider.get("awsAccessKeyId").toString();
    	String secretAccessKey = mongoProvider.get("secretAccessKey").toString();
    	try{
    		AbstractProvider eucaProvider = new EucalyptusProvider(providerId, name, url, port, awsAccessKeyId, secretAccessKey);
    		ProviderRegistry.getInstance().getProviders().add(eucaProvider);
    		System.out.println("Added provider "+name);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
 
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Context destroyed."); 
    }
     
}