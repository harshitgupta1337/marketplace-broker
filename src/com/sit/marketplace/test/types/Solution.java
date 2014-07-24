package com.sit.marketplace.test.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

	private Map<String, Integer> vmAllocationMap;

	/**
	 * @return the vmAllocationMap
	 */
	public Map<String, Integer> getVmAllocationMap() {
		return vmAllocationMap;
	}

	/**
	 * @param vmAllocationMap the vmAllocationMap to set
	 */
	public void setVmAllocationMap(Map<String, Integer> vmAllocationMap) {
		this.vmAllocationMap = vmAllocationMap;
	}
	
	public List<String> getProviderIds(){
		Set<String> providerIdsSet = vmAllocationMap.keySet();
		List<String> providerIdsList = new ArrayList<String>();
		for(String providerId : providerIdsSet){
			providerIdsList.add(providerId);
		}
		return providerIdsList;
	}
}