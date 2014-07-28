package com.sit.marketplace.broker.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sit.marketplace.broker.utils.Utils;

public class ParamsGsonAdapter extends TypeAdapter<Map<String, Double>> {
	
	  @Override
	  public Map<String, Double> read(final JsonReader in) throws IOException {
	    Map<String, Double> map = new HashMap<String, Double>();

	    in.beginObject();
	    while (in.hasNext()) {
	    	map.put(in.nextName(), in.nextDouble());
	    }
	    in.endObject();
	    return map;
	  }
	  @Override
	  public void write(final JsonWriter out, final Map<String, Double> UtilsMap) throws IOException {
	    out.beginObject();
	    
	    out.name(Utils.TRUST_PARAM_NAME).value(UtilsMap.get(Utils.TRUST_PARAM_NAME));
	    out.name(Utils.MIN_AVAILABILITY_PARAM_NAME).value(UtilsMap.get(Utils.MIN_AVAILABILITY_PARAM_NAME));
	    out.name(Utils.COST_PARAM_NAME).value(UtilsMap.get(Utils.COST_PARAM_NAME));
	    
	    out.endObject();
	  }
}
