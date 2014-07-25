package com.sit.marketplace.broker.types;

public abstract class AbstractInstanceDescription {
	private String ipAddress;
	private String instanceId;
	public String getIpAddress(){
		return ipAddress;
	}
	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}
	public String getInstanceId(){
		return instanceId;
	}
	public void setInstanceId(String instanceId){
		this.instanceId = instanceId;
	}
	public AbstractInstanceDescription(String instanceId, String ipAddress){
		this.instanceId = instanceId;
		this.ipAddress = ipAddress;
	}

}
