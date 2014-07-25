package com.sit.marketplace.test.sa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sit.marketplace.test.types.Provider;
import com.sit.marketplace.test.types.Solution;

public class SimulatedAnnealingOptimizer extends Optimizer {

	private static double MIN_TEMPERATURE = 0.0001;
	private static int NO_OF_ITERATIONS = 1000;
	private static double INITIAL_TEMPERATURE= 1000;
	
	private List<Solution> omega;
	private double temperature;
	
	@Override
	public Solution findOptimalSolution(List<Provider> providers, int noOfVms) {
		this.providers = providers;
		this.noOfVms = noOfVms;
		providerIdToProviderMap = new HashMap<String, Provider>();
		for(Provider provider : providers){
			providerIdToProviderMap.put(provider.getProviderId(), provider);
		}
		omega = new ArrayList<Solution>();
		temperature = INITIAL_TEMPERATURE;
		fillRandomSolution();
		
		while(temperature > MIN_TEMPERATURE){
			//System.out.println(omega.size());
			printOmega();
			iterate();
		}
		
		return pickFinalSolutionFromOmega();
	}
	
	private void printOmega(){
		for(Solution solution : omega){
			System.out.print("[Cost : " + solution.getAvgCost() + ", MinAvail : " + solution.getAvgMinAvailability() + ", Trust : " + solution.getAvgTrust() + "], ");
		}
		System.out.println();
		System.out.println();
	}
	
	private Solution pickFinalSolutionFromOmega(){
		return omega.get(0);
	}
	
	private void fillRandomSolution(){
		Map<String, Integer> allocationMap = new HashMap<String, Integer>();
		int noOfVmsLeft = noOfVms;
		for(Provider provider : providers){
			System.out.println("Provider Id" + provider.getProviderId());
			if(noOfVmsLeft == 0){
				allocationMap.put(provider.getProviderId(), 0);
				continue;
			}
			
			if(provider.getNoOfAvailableVms() <= noOfVmsLeft){
				allocationMap.put(provider.getProviderId(), provider.getNoOfAvailableVms());
				System.out.println(provider.getNoOfAvailableVms() + " VMs allocated");
				noOfVmsLeft -= provider.getNoOfAvailableVms();
			}
			else{
				allocationMap.put(provider.getProviderId(), noOfVmsLeft);
				noOfVmsLeft = 0;
			}
		}
		
		omega.add(new Solution(allocationMap, calculateAvgMinAvailability(allocationMap), calculateAvgCost(allocationMap), calculateAvgTrust(allocationMap)));
		
	}
	
	private double calculateAvgMinAvailability(Map<String, Integer> allocationMap){
		double numerator = 0.0;
		double denominator = 0.0;
		for(String providerId : allocationMap.keySet()){
			
			numerator += providerIdToProviderMap.get(providerId).getMinAvailability() * allocationMap.get(providerId);
			denominator += allocationMap.get(providerId);
			
			
		}
		return (numerator/denominator);
	}
	private double calculateAvgCost(Map<String, Integer> allocationMap){
		double numerator = 0.0;
		double denominator = 0.0;
		for(String providerId : allocationMap.keySet()){
			numerator += providerIdToProviderMap.get(providerId).getCostPerHour() * allocationMap.get(providerId);
			denominator += allocationMap.get(providerId);
		}
		return (numerator/denominator);
	}
	private double calculateAvgTrust(Map<String, Integer> allocationMap){
		double numerator = 0.0;
		double denominator = 0.0;
		for(String providerId : allocationMap.keySet()){
			numerator += providerIdToProviderMap.get(providerId).getTrust() * allocationMap.get(providerId);
			denominator += allocationMap.get(providerId);
		}
		return (numerator/denominator);
	}
	
	private void printDiffOfAllocationMaps(Map<String, Integer> initialMap, Map<String, Integer> finalMap){
		System.out.println("===========");
		for(String providerId : initialMap.keySet()){
			System.out.println(providerId + " : " + initialMap.get(providerId) + " ----> " + finalMap.get(providerId));
		}
		System.out.println("===========");
	}
	
	private Solution perturbSolution(Solution solution){
		Provider loser = null;
		while(true){
			loser = providers.get((int) (Math.random()*providers.size()));
			if(solution.getVmAllocationMap().get(loser.getProviderId()) > 0)
				break;
		}
		
		Provider gainer = null;
		int i=0;
		while(true){
			if(i == providers.size()){
				gainer = getGainerByBruteForce(loser, solution);
				if(gainer == null){
					gainer = loser;
					break;
				}else{
					break;
				}
			}
			i++;
			int randomIndex = (int) (Math.random()*providers.size());
			Provider currentProvider = providers.get(randomIndex);
			if(currentProvider.getProviderId() == loser.getProviderId())
				continue;
			if(currentProvider.getNoOfAvailableVms() == solution.getVmAllocationMap().get(currentProvider.getProviderId()))
				continue;
			
			gainer = currentProvider;
			break;
		}
		Map<String, Integer> allocationMap = new HashMap<String, Integer>(solution.getVmAllocationMap());
		allocationMap.put(loser.getProviderId(), allocationMap.get(loser.getProviderId())-1);
		allocationMap.put(gainer.getProviderId(), allocationMap.get(gainer.getProviderId())+1);
		
		//printDiffOfAllocationMaps(solution.getVmAllocationMap(), allocationMap);
		return new Solution(allocationMap, calculateAvgMinAvailability(allocationMap), calculateAvgCost(allocationMap), calculateAvgTrust(allocationMap));
		
	}

	private Provider getGainerByBruteForce(Provider loser, Solution solution){
		for(Provider provider : providers){
			if(provider.getProviderId() == loser.getProviderId())
				continue;
			if(provider.getNoOfAvailableVms() == solution.getVmAllocationMap().get(provider.getProviderId()))
				continue;
			return provider;
		}
		return null;
	}
	public float probability(float deltaEnergy){
		return (float) Math.min(1, Math.exp(-1*deltaEnergy/temperature));
	}
	
	public void iterate(){
		for(int i=0;i< NO_OF_ITERATIONS;i++){
			Solution uniSelected = uniselect();
			Solution x_ = perturbSolution(uniSelected);
			
			List<Solution> omega_ = getPerturbedNonDomSet(x_);
			if(Math.random()<probability(deltaEnergy(omega_, x_))){
				omega = omega_;
			} 
		}
		updateTemperature();
	}
	
	public float deltaEnergy(List<Solution> omega, Solution x_){
		int a=0,b=0;
		for(int i=0;i<omega.size();i++){
			if(omega.get(i).dominates(x_))
				a++;
			if(x_.dominates(omega.get(i)))
				b++;
		}
		return ((float)(a-b))/(omega.size());
	}
	
	public List<Solution> getPerturbedNonDomSet(Solution perturbed){
		List<Solution> omega_ = new ArrayList<Solution>();
		omega_.add(perturbed);

		for(int i=0;i<omega.size();i++){
			if((!omega.get(i).dominates(perturbed))&&(!perturbed.dominates(omega.get(i)))){
				//if(omega.get(i).equals(perturbed))
					//System.out.println("EQUAL SOLUTIONS FOUND !!!!!");
				omega_.add(omega.get(i));
			}
		}
		return omega_;
	}
	
	private void updateTemperature(){
		temperature = (float) (temperature * 0.999);
	}
	
	private Solution uniselect(){		
		Solution uniselectedSolution = omega.get(0);
		double random = Math.random();
		if(random < 0.33){
			double mincost = omega.get(0).getAvgCost(), maxcost = omega.get(0).getAvgCost();
			for(Solution soln : omega){
				if(soln.getAvgCost() > maxcost)
					maxcost = soln.getAvgCost();
				if(soln.getAvgCost() < mincost)
					mincost = soln.getAvgCost();
			}
			
			double randomcost = Math.random()*(maxcost - mincost) + mincost;
			
			for(Solution soln : omega){
				if(Math.abs(soln.getAvgCost() - randomcost) < Math.abs(uniselectedSolution.getAvgCost() - randomcost))
					uniselectedSolution = soln;
			}
			
		}else if(random <0.66){
			double minAvailability = omega.get(0).getAvgMinAvailability(), maxAvailability = omega.get(0).getAvgMinAvailability();
			for(Solution soln : omega){
				if(soln.getAvgMinAvailability() > maxAvailability)
					maxAvailability = soln.getAvgMinAvailability();
				if(soln.getAvgMinAvailability() < minAvailability)
					minAvailability = soln.getAvgMinAvailability();
			}
			
			double randomAvailability = Math.random()*(maxAvailability - minAvailability) + minAvailability;
						
			for(Solution soln : omega){
				if(Math.abs(soln.getAvgMinAvailability() - randomAvailability) < Math.abs(uniselectedSolution.getAvgMinAvailability() - randomAvailability))
					uniselectedSolution = soln;
			}
		}else{
			double mincost = omega.get(0).getAvgCost(), maxcost = omega.get(0).getAvgCost();
			for(Solution soln : omega){
				if(soln.getAvgCost() > maxcost)
					maxcost = soln.getAvgCost();
				if(soln.getAvgCost() < mincost)
					mincost = soln.getAvgCost();
			}
			
			double randomcost = Math.random()*(maxcost - mincost) + mincost;
						
			for(Solution soln : omega){
				if(Math.abs(soln.getAvgCost() - randomcost) < Math.abs(uniselectedSolution.getAvgCost() - randomcost))
					uniselectedSolution = soln;
			}
		}
		return uniselectedSolution;
	}
	
	/**
	 * @return the omega (list of mutually non-dominating solutions)
	 */
	public List<Solution> getOmega() {
		return omega;
	}

	/**
	 * @param omega the omega (list of mutually non-dominating solutions) to set
	 */
	public void setOmega(List<Solution> omega) {
		this.omega = omega;
	}

}
