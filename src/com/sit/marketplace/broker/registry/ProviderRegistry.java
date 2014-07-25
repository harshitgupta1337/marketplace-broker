package com.sit.marketplace.broker.registry;

import java.util.ArrayList;
import java.util.List;

import com.sit.marketplace.broker.provider.AbstractProvider;

public class ProviderRegistry {
	
	private static ProviderRegistry instance;
	private List<AbstractProvider> providers;
	
	protected ProviderRegistry() {
		setProviders(new ArrayList<AbstractProvider>());
	}
	
	public static ProviderRegistry getInstance() {
		if(instance == null) {
			instance = new ProviderRegistry();
		}
		return instance;
	}

	/**
	 * @return the providers
	 */
	public List<AbstractProvider> getProviders() {
		return providers;
	}

	/**
	 * @param providers the providers to set
	 */
	public void setProviders(List<AbstractProvider> providers) {
		this.providers = providers;
	} 

}
