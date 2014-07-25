package com.sit.marketplace.test.sa;

import java.util.List;

import com.sit.marketplace.test.types.Provider;
import com.sit.marketplace.test.types.Solution;

public class AllocatorThread implements Runnable {

	Optimizer optimizer;
	List<Provider> providers;
	int noOfVms;
	
	public AllocatorThread(List<Provider> providers, int noOfVms){
		this.optimizer = new SimulatedAnnealingOptimizer();
		this.providers = providers;
		this.noOfVms = noOfVms;
	}
	
	@Override
	public void run() {
		Solution solution = optimizer.findOptimalSolution(providers, noOfVms);
		allocateVms(solution);
		System.out.println("Found the solution");
	}
	
	private void allocateVms(Solution solution){
		
	}

}
