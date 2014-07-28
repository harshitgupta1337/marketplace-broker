package com.sit.marketplace.broker.vmallocation;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sit.marketplace.broker.utils.ParamsGsonAdapter;

public class LaunchInstances extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Map.class, new ParamsGsonAdapter());
		final Gson gson = gsonBuilder.create();
		String allocationMapJson = request.getParameter("allocationMap");
		Map<String, Integer> allocationMap = gson.fromJson(allocationMapJson, Map.class);
		//for(String providerId : )
	}
	
	

}
