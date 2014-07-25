package com.sit.marketplace.test.types;


public class Provider {
	
	private String providerId;
	private double minAvailability;
	private double trust;
	private double costPerHour;
	private int noOfAvailableVms;
	
	public Provider(String providerId, double minAvailability, double trust, double costPerHour, int noOfAvailableVms){
		this.providerId = providerId;
		this.minAvailability = minAvailability;
		this.trust = trust;
		this.costPerHour = costPerHour;
		this.noOfAvailableVms = noOfAvailableVms;
	}
	
	/**
	 * @return the minAvailability
	 */
	public double getMinAvailability() {
		return minAvailability;
	}
	/**
	 * @param minAvailability the minAvailability to set
	 */
	public void setMinAvailability(double minAvailability) {
		this.minAvailability = minAvailability;
	}
	/**
	 * @return the trust
	 */
	public double getTrust() {
		return trust;
	}
	/**
	 * @param trust the trust to set
	 */
	public void setTrust(double trust) {
		this.trust = trust;
	}
	/**
	 * @return the costPerHour
	 */
	public double getCostPerHour() {
		return costPerHour;
	}
	/**
	 * @param costPerHour the costPerHour to set
	 */
	public void setCostPerHour(double costPerHour) {
		this.costPerHour = costPerHour;
	}
	
	/**
	 * @return the providerId
	 */
	public String getProviderId() {
		return providerId;
	}
	/**
	 * @param providerId the providerId to set
	 */
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	/**
	 * @return the noOfAvailableVms
	 */
	public int getNoOfAvailableVms() {
		return noOfAvailableVms;
	}
	/**
	 * @param noOfAvailableVms the noOfAvailableVms to set
	 */
	public void setNoOfAvailableVms(int noOfAvailableVms) {
		this.noOfAvailableVms = noOfAvailableVms;
	}
}
