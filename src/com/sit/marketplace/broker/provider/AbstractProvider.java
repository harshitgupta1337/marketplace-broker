package com.sit.marketplace.broker.provider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.sit.marketplace.broker.types.AbstractInstanceDescription;
import com.sit.marketplace.broker.utils.Utils;
import com.sit.marketplace.broker.utils.Utils.CloudType;

public abstract class AbstractProvider {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private final static String USER_AGENT = "Mozilla/5.0";

	protected String name;
	protected String providerId;
	protected String url;
	protected int port;
	protected CloudType cloudType;
	
	public AbstractProvider(String providerId, String name, String url, int port, CloudType cloudType){
		this.name = name;
		this.providerId = providerId;
		this.url = url;
		this.port = port;
		this.cloudType = cloudType;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public void setPort(int port){
		this.port = port;
	}
	public void setCloudType(CloudType cloudType){
		this.cloudType = cloudType;
	}
	public void setProviderId(String providerId){
		this.providerId = providerId;
	}
	
	public String getName(){
		return name;
	}
	public String getUrl(){
		return url;
	}
	public int getPort(){
		return port;
	}
	public CloudType getCloudType(){
		return cloudType;
	}
	public String getProviderId(){
		return providerId;
	}
	
	public boolean launchInstances(int numOfInstances, String username) throws Exception{
		List<String> instanceIds = new ArrayList<String>();
		String url = "http://" + this.url + ":" + port + "/CloudMarketplaceProvider/launchInstances";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("POST");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		con.setRequestProperty("num", Integer.toString(numOfInstances));
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			instanceIds.add(inputLine);
		}
		in.close();
		//print result
		updateDbForUser(username, instanceIds);
		System.out.println(response.toString());
		return true;
	}
	
	private boolean updateDbForUser(String username, List<String> instanceIds) throws Exception{
		try{
			MongoClient client = new MongoClient(Utils.MONGODB_HOST, 27017);
			DB database = client.getDB(Utils.MONGODB_DB);
			DBCollection users = database.getCollection(Utils.MONGODB_USERS_COLLECTION);
			for(String instanceId : instanceIds){
				users.update(new BasicDBObject("username", username), new BasicDBObject("$push", new BasicDBObject("instances", new BasicDBObject("instanceId", instanceId).append("providerId", providerId).append("provisionDate", dateFormat.format(new Date())))));
			}
			return true;
		}
		catch(Exception e){
			throw(e);
		}
	}
	
	public abstract List<AbstractInstanceDescription> describeInstances(List<String> instanceIds);
}
