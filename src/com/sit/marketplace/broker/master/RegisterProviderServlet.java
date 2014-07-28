package com.sit.marketplace.broker.master;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.sit.marketplace.broker.provider.AbstractProvider;
import com.sit.marketplace.broker.provider.EucalyptusProvider;
import com.sit.marketplace.broker.registry.ProviderRegistry;
import com.sit.marketplace.broker.utils.Utils;

public class RegisterProviderServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("cloudType").equals("EUCALYPTUS")){
			registerEucalyptusProvider(request);
		}
	}
	private void registerEucalyptusProvider(HttpServletRequest request){
		String clcHost = request.getParameter("clcHost");
		int clcPort = Integer.parseInt(request.getParameter("clcPort"));
		String awsAccessKeyId = request.getParameter("awsAccessKeyId");
		String secretAccessKey = request.getParameter("secretAccessKey");
		String name = request.getParameter("name");
		
		// TODO
		//CALL THE PROVIDER'S WEBSERVICE AND GET THE PARAMETERS
		try{
			AbstractProvider provider = new EucalyptusProvider(UUID.randomUUID().toString(), name, clcHost, clcPort, awsAccessKeyId, secretAccessKey);
			
			MongoClient client = new MongoClient(Utils.MONGODB_HOST, 27017);
			DB database = client.getDB(Utils.MONGODB_DB);
			DBCollection providers= database.getCollection(Utils.MONGODB_PROVIDERS_COLLECTION);
			
			providers.insert(new BasicDBObject("providerId", provider.getProviderId()).append("url", clcHost).append("port", clcPort)
					.append("awsAccessKeyId", awsAccessKeyId).append("secretAccessKey", secretAccessKey).append("name", name)
					.append("cloudType", "EUCALYPTUS"));
			
			ProviderRegistry.getInstance().getProviders().add(provider);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
