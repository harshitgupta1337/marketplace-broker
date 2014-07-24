package com.sit.marketplace.test.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solution {

	private Map<String, Integer> vmAllocationMap;

	private float avgMinAvailability;
	private float avgCost;
	private float avgTrust;

	public Solution(Map<String, Integer> vmAllocationMap, float avgMinAvailability, float avgCost, float avgTrust){
		this.vmAllocationMap = vmAllocationMap;
		this.avgCost = avgCost;
		this.avgMinAvailability = avgMinAvailability;
		this.avgTrust = avgTrust;
	}
	
	/**
	 * @return the vmAllocationMap
	 */
	public Map<String, Integer> getVmAllocationMap() {
		return vmAllocationMap;
	}
	
	public List<String> getProviderIds(){
		List<String> providerIdsList = new ArrayList<String>();
		for(String providerId : vmAllocationMap.keySet()){
			providerIdsList.add(providerId);
		}
		return providerIdsList;
	}


	public float getAvgMinAvailability() {
		return avgMinAvailability;
	}


	public void setAvgMinAvailability(float avgMinAvailability) {
		this.avgMinAvailability = avgMinAvailability;
	}


	public float getAvgCost() {
		return avgCost;
	}


	public void setAvgCost(float avgCost) {
		this.avgCost = avgCost;
	}


	public float getAvgTrust() {
		return avgTrust;
	}


	public void setAvgTrust(float avgTrust) {
		this.avgTrust = avgTrust;
	}
	
	/**
	 * Checks the solutions for dominance relation.
	 * @param soln the solution to compare this solution with
	 * @return true if the caller solution dominates the argument solution
	 */
	public boolean dominates(Solution soln){
		if(this.avgCost <= soln.avgCost && this.avgMinAvailability >= soln.avgMinAvailability && this.avgTrust >= soln.avgTrust){
			if(this.avgCost < soln.avgCost || this.avgMinAvailability > soln.avgMinAvailability || this.avgTrust > soln.avgTrust){
				return true;
			}
		}
		return false;
	}
}