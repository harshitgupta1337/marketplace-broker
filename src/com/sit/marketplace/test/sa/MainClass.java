package com.sit.marketplace.test.sa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.sit.marketplace.test.types.Provider;
import com.sit.marketplace.test.types.Solution;

public class MainClass {
	public static void main(String args[]) throws InterruptedException{
		Provider provider1 = new Provider("provider1", Math.random()*100, Math.random()*100, Math.random()*100, 100);
		Provider provider2 = new Provider("provider2", Math.random()*100, Math.random()*100, Math.random()*100, 100);
		Provider provider3 = new Provider("provider3", Math.random()*100, Math.random()*100, Math.random()*100, 100);
		Provider provider4 = new Provider("provider4", Math.random()*100, Math.random()*100, Math.random()*100, 100);
		Provider provider5 = new Provider("provider5", Math.random()*100, Math.random()*100, Math.random()*100, 100);
		Provider provider6 = new Provider("provider6", Math.random()*100, Math.random()*100, Math.random()*100, 100);
		
		List<Provider> providers = new ArrayList<Provider>();
		providers.add(provider1);
		providers.add(provider2);
		providers.add(provider3);
		providers.add(provider4);
		providers.add(provider5);
		providers.add(provider6);
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		AllocatorThread allocatorThread = new AllocatorThread(providers, 500);
		executorService.submit(allocatorThread);
		
	}

}
