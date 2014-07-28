package com.sit.marketplace.broker.types;

public class InstanceReservation {
	private int numOfVms;
	private double costPerVmPerHour;
	private double trust;
	private double minAvailability;
	private String providerId;
	
	public InstanceReservation(int numOfVms, double costPerVmPerHour, double trust, double minAvailability, String providerId){
		this.numOfVms = numOfVms;
		this.costPerVmPerHour = costPerVmPerHour;
		this.trust = trust;
		this.minAvailability = minAvailability;
		this.providerId = providerId;
	}
	
	public int getNumOfVms() {
		return numOfVms;
	}
	public void setNumOfVms(int numOfVms) {
		this.numOfVms = numOfVms;
	}
	public double getCostPerVmPerHour() {
		return costPerVmPerHour;
	}
	public void setCostPerVmPerHour(double costPerVmPerHour) {
		this.costPerVmPerHour = costPerVmPerHour;
	}

	public double getTrust() {
		return trust;
	}

	public void setTrust(double trust) {
		this.trust = trust;
	}

	public double getMinAvailability() {
		return minAvailability;
	}

	public void setMinAvailability(double minAvailability) {
		this.minAvailability = minAvailability;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

}
