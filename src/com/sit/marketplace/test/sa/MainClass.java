package com.sit.marketplace.test.sa;

import java.util.ArrayList;
import java.util.List;

import com.sit.marketplace.test.types.Provider;
import com.sit.marketplace.test.types.Solution;

public class MainClass {
	public static void main(String args[]){
		Provider provider1 = new Provider("provider1", 99, 100, 0.2, 100);
		Provider provider2 = new Provider("provider2", 59, 20, 12, 100);
		Provider provider3 = new Provider("provider3", 59, 20, 12, 100);
		Provider provider4 = new Provider("provider4", 59, 20, 12, 100);
		Provider provider5 = new Provider("provider5", 59, 20, 12, 100);
		Provider provider6 = new Provider("provider6", 59, 20, 12, 100);
		
		List<Provider> providers = new ArrayList<Provider>();
		providers.add(provider2);
		providers.add(provider1);
		providers.add(provider3);
		providers.add(provider4);
		providers.add(provider5);
		providers.add(provider6);
		
		
		Optimizer optimizer = new SimulatedAnnealingOptimizer();
		Solution solution = optimizer.findOptimalSolution(providers, 20);
	}

}
