package com.sit.marketplace.broker.types;

public class EucalyptusInstanceDescription extends AbstractInstanceDescription{
	public EucalyptusInstanceDescription(String instanceId, String ipAddress, String privateKey, String securityGroup) {
		super(instanceId, ipAddress);
		this.privateKey = privateKey;
		this.securityGroup = securityGroup;
	}
	private String privateKey;
	private String securityGroup;
	
	public String getPrivateKey(){
		return privateKey;
	}
	public String getSecurityGroup(){
		return securityGroup;
	}
	
	public void setPrivateKey(String privateKey){
		this.privateKey = privateKey;
	}
	public void setSecurityGroup(String securityGroup){
		this.securityGroup = securityGroup;
	}

}
