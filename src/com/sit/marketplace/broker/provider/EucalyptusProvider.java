package com.sit.marketplace.broker.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sit.marketplace.broker.types.AbstractInstanceDescription;
import com.sit.marketplace.broker.types.EucalyptusInstanceDescription;
import com.sit.marketplace.broker.utils.Utils.CloudType;
import com.xerox.amazonws.ec2.Jec2;
import com.xerox.amazonws.ec2.ReservationDescription;
import com.xerox.amazonws.ec2.ReservationDescription.Instance;

public class EucalyptusProvider extends AbstractProvider {

	private String awsAccessKeyId;
	private String secretAccessKey;
	
	public void setAwsAccessKeyId(String awsAccessKeyId){
		this.awsAccessKeyId = awsAccessKeyId;
	}
	public String getAwsAccessKeyId(){
		return awsAccessKeyId;
	}
	public void setSecretAccessKey(String secretAccessKey){
		this.secretAccessKey = secretAccessKey;
	}
	public String getSecretAccessKey(){
		return secretAccessKey;
	}
	
	public EucalyptusProvider(String providerId, String name, String url, int port, String awsAccessKeyId, String secretAccessKey) {
		super(providerId, name, url, port, CloudType.EUCALYPTUS);
		this.awsAccessKeyId = awsAccessKeyId;
		this.secretAccessKey = secretAccessKey;
	}

	@Override
	public List<AbstractInstanceDescription> describeInstances(List<String> instanceIds) {
		Jec2 connection = new Jec2(getAwsAccessKeyId(), 
				getSecretAccessKey(), false, 
				getUrl(), getPort());
		connection.setResourcePrefix("/services/Eucalyptus"); 
		connection.setSignatureVersion(1);
		
		Map<String, EucalyptusInstanceDescription> instanceMap = new HashMap<String, EucalyptusInstanceDescription>();
		List<ReservationDescription> resDescs = null;
		try{
			resDescs = connection.describeInstances(instanceIds);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
		for(ReservationDescription resDesc : resDescs){
			for(Instance instance : resDesc.getInstances()){
				instanceMap.put(instance.getInstanceId(), new EucalyptusInstanceDescription(
						instance.getInstanceId(), instance.getIpAddress(), instance.getKeyName(), resDesc.getGroups().get(0)));
			}
		}
		List<AbstractInstanceDescription> instanceDescriptions = new ArrayList<AbstractInstanceDescription>();
		for(String instanceId : instanceIds){
			instanceDescriptions.add(instanceMap.get(instanceId));
		}
		return instanceDescriptions;
	}
}
