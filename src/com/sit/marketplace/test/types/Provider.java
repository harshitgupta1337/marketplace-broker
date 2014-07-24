package com.sit.marketplace.test.types;

public class Provider {
	
	private String providerId;
	private float minAvailability;
	private float trust;
	private float costPerHour;
	/**
	 * @return the minAvailability
	 */
	public float getMinAvailability() {
		return minAvailability;
	}
	/**
	 * @param minAvailability the minAvailability to set
	 */
	public void setMinAvailability(float minAvailability) {
		this.minAvailability = minAvailability;
	}
	/**
	 * @return the trust
	 */
	public float getTrust() {
		return trust;
	}
	/**
	 * @param trust the trust to set
	 */
	public void setTrust(float trust) {
		this.trust = trust;
	}
	/**
	 * @return the costPerHour
	 */
	public float getCostPerHour() {
		return costPerHour;
	}
	/**
	 * @param costPerHour the costPerHour to set
	 */
	public void setCostPerHour(float costPerHour) {
		this.costPerHour = costPerHour;
	}
	
	public Provider(float minAvailability, float trust, float costPerHour){
		this.minAvailability = minAvailability;
		this.trust = trust;
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
}
