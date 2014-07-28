package com.sit.marketplace.broker.vmallocation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sit.marketplace.broker.provider.AbstractProvider;
import com.sit.marketplace.broker.registry.ProviderRegistry;
import com.sit.marketplace.broker.types.InstanceReservation;
import com.sit.marketplace.broker.utils.ParamsGsonAdapter;
import com.sit.marketplace.broker.utils.Utils;
import com.sit.marketplace.test.sa.Optimizer;
import com.sit.marketplace.test.sa.SimulatedAnnealingOptimizer;
import com.sit.marketplace.test.types.Provider;
import com.sit.marketplace.test.types.Solution;

public class FindVmAllocationSolution extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private final static String USER_AGENT = "Mozilla/5.0";

	public List<InstanceReservation> findOptimalSolution(HttpServletRequest request){
		List<Provider> providers = new ArrayList<Provider>();
		for(AbstractProvider abstractProvider : ProviderRegistry.getInstance().getProviders()){
			int noOfFreeVms = getNoOfFreeVms(abstractProvider);
			if(noOfFreeVms == -1){
				System.out.println("Provider "+abstractProvider.getProviderId()+" is not working fine. Please check.");
				continue;
			}
				
			Map<String, Double> paramsMap = getParamsMap(abstractProvider);
			Provider provider = new Provider(abstractProvider.getProviderId(), paramsMap.get(Utils.MIN_AVAILABILITY_PARAM_NAME), 
					paramsMap.get(Utils.TRUST_PARAM_NAME), paramsMap.get(Utils.COST_PARAM_NAME), noOfFreeVms);
			providers.add(provider);
		}
		List<Provider> providersForSA = performPreliminaryCheck(providers);
		Optimizer optimizer = new SimulatedAnnealingOptimizer();
		Solution solution = optimizer.findOptimalSolution(providersForSA, Integer.parseInt(request.getParameter("noOfVms")));
		List<InstanceReservation> instanceReservations = new ArrayList<InstanceReservation>();
		for(Provider provider : providers){
			int numOfVms = solution.getVmAllocationMap().get(provider.getProviderId());
			instanceReservations.add(new InstanceReservation(numOfVms, provider.getCostPerHour(), provider.getTrust(), provider.getMinAvailability(), provider.getProviderId()));
		}
		return instanceReservations;
	}
	private int getNoOfFreeVms(AbstractProvider provider){
		try{
			String url1 = "http://" + provider.getUrl() + ":8081/CloudMarketplaceProvider/getNoOfFreeVms";
			URL obj = new URL(url1);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 		con.setRequestMethod("GET");
	 		con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' getNoOfFreeVms request to URL : " + provider.getUrl());
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return Integer.parseInt(response.toString());
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
	private List<Provider> performPreliminaryCheck(List<Provider> providers){
		return providers;
	}
	private Map<String, Double> getParamsMap(AbstractProvider provider){
		try{
			String url1 = "http://" + provider.getUrl() + ":8081/CloudMarketplaceProvider/Servlet";
			URL obj = new URL(url1);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 		con.setRequestMethod("GET");
	 		con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + provider.getUrl());
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
			final GsonBuilder gsonBuilder = new GsonBuilder();
		    gsonBuilder.registerTypeAdapter(Map.class, new ParamsGsonAdapter());
		    final Gson gson = gsonBuilder.create();
			Map<String, Double> paramsMap = gson.fromJson(response.toString(), Map.class);
			return paramsMap;
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
