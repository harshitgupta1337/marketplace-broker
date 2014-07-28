package com.sit.marketplace.broker.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sit.marketplace.broker.types.InstanceReservation;

public class InstanceReservationsGsonAdapter extends TypeAdapter<List<InstanceReservation>> {
	
	@Override
	public List<InstanceReservation> read(final JsonReader in) throws IOException {
		List<InstanceReservation> instanceReservations = new ArrayList<InstanceReservation>();
		in.beginArray();
		while(in.hasNext()){
			in.beginObject();
			while(in.hasNext()){
				int numOfVms = 0;
				String providerId = "";
				double trust = 0.0, minAvailability = 0.0, cost = 0.0;
				if(in.nextName().equals(Utils.COST_PARAM_NAME))
					cost = in.nextDouble();
				if(in.nextName().equals(Utils.TRUST_PARAM_NAME))
					trust = in.nextDouble();
				if(in.nextName().equals(Utils.MIN_AVAILABILITY_PARAM_NAME))
					minAvailability = in.nextDouble();
				if(in.nextName().equals(Utils.NO_OF_VMS_PARAM_NAME))
					numOfVms = in.nextInt();
				if(in.nextName().equals(Utils.PROVIDER_ID_PARAM_NAME))
					providerId = in.nextString();
				instanceReservations.add(new InstanceReservation(numOfVms, cost, trust, minAvailability, providerId));
			}
			in.endObject();
		}
	    in.endArray();
	    return instanceReservations;
	}
	@Override
	public void write(final JsonWriter out, final List<InstanceReservation> instanceReservations) throws IOException {
	    out.beginArray();
	    for(InstanceReservation instanceReservation : instanceReservations){
	    	out.beginObject();
			out.name(Utils.PROVIDER_ID_PARAM_NAME).value(instanceReservation.getProviderId());
			out.name(Utils.NO_OF_VMS_PARAM_NAME).value(instanceReservation.getNumOfVms());
			out.name(Utils.TRUST_PARAM_NAME).value(instanceReservation.getTrust());
		    out.name(Utils.MIN_AVAILABILITY_PARAM_NAME).value(instanceReservation.getMinAvailability());
		    out.name(Utils.COST_PARAM_NAME).value(instanceReservation.getCostPerVmPerHour());
		    out.endObject();
	    }
	    out.endArray();
	}
}
