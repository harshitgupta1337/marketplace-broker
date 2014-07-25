package com.sit.marketplace.test.sa;

import java.util.List;
import java.util.Map;

import com.sit.marketplace.test.types.Provider;
import com.sit.marketplace.test.types.Solution;

public abstract class Optimizer {

	protected List<Provider> providers;
	protected Map<String, Provider> providerIdToProviderMap;
	protected int noOfVms;
	
	public abstract Solution findOptimalSolution(List<Provider> providers, int noOfVms);

	/**
	 * @return the providers
	 */
	public List<Provider> getProviders() {
		return providers;
	}

	/**
	 * @param providers the providers to set
	 */
	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	/**
	 * @return the noOfVms
	 */
	public int getNoOfVms() {
		return noOfVms;
	}

	/**
	 * @param noOfVms the noOfVms to set
	 */
	public void setNoOfVms(int noOfVms) {
		this.noOfVms = noOfVms;
	}
	
	public void setProviderIdToProviderMap(Map<String, Provider> providerIdToProviderMap){
		this.providerIdToProviderMap = providerIdToProviderMap;
	}
	public Map<String, Provider> getProviderIdToProviderMap(){
		return providerIdToProviderMap;
	}
}
