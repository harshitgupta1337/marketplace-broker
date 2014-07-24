package com.sit.marketplace.test.sa;

import java.util.ArrayList;
import java.util.List;

import com.sit.marketplace.test.types.Provider;
import com.sit.marketplace.test.types.Solution;

public class SimulatedAnnealingOptimizer extends Optimizer {

	private static double MIN_TEMPERATURE = 0.01;
	private static int NO_OF_ITERATIONS = 1000;
	private static double INITIAL_TEMPERATURE= 1000;
	private static double PERTURBATION_PROBABILITY= 0.3;
	
	private List<Solution> omega;
	private double temperature;
	
	@Override
	public Solution findOptimalSolution(List<Provider> providers, int noOfVms) {
		this.providers = providers;
		this.noOfVms = noOfVms;
		omega = new ArrayList<Solution>();
		temperature = INITIAL_TEMPERATURE;
		fillRandomSolution();
		
		while(temperature > MIN_TEMPERATURE){
			iterate();
		}
		
		return pickSolutionFromOmega();
	}
	
	private Solution pickSolutionFromOmega(){
		return omega.get(0);
	}
	
	private void fillRandomSolution(){
	}
	
	private Solution perturbSolution(Solution solution){
		return solution;
		
	}

	public float probability(float deltaEnergy){
		return (float) Math.min(1, Math.exp(-1*deltaEnergy/temperature));
	}
	
	public void iterate(){
		for(int i=0;i< NO_OF_ITERATIONS;i++){
			Solution uniSelected = omega.get((int)(Math.random()*omega.size()));
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
				omega_.add(omega.get(i));
			}
		}
		return omega_;
	}
	
	private void updateTemperature(){
		temperature = (float) (temperature * 0.90);
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
