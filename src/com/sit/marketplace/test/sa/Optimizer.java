package com.sit.marketplace.test.sa;

import java.util.List;

import com.sit.marketplace.test.types.Provider;
import com.sit.marketplace.test.types.Solution;

public interface Optimizer {

	public Solution findOptimalSolution(List<Provider> providers, int noOfVms);
}
