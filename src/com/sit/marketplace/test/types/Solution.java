package com.sit.marketplace.test.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solution {

	private Map<String, Integer> vmAllocationMap;

	private double avgMinAvailability;
	private double avgCost;
	private double avgTrust;

	public Solution(Map<String, Integer> vmAllocationMap, double avgMinAvailability, double avgCost, double avgTrust){
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


	public double getAvgMinAvailability() {
		return avgMinAvailability;
	}


	public void setAvgMinAvailability(double avgMinAvailability) {
		this.avgMinAvailability = avgMinAvailability;
	}


	public double getAvgCost() {
		return avgCost;
	}


	public void setAvgCost(double avgCost) {
		this.avgCost = avgCost;
	}


	public double getAvgTrust() {
		return avgTrust;
	}


	public void setAvgTrust(double avgTrust) {
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