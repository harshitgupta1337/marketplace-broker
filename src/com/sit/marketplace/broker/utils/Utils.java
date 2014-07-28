package com.sit.marketplace.broker.utils;

public class Utils {

	public static String COOKIE_NAME = "sitCloudMarketPlaceBrokerUser";
	public static String MONGODB_HOST = "10.14.254.1";
	public static String MONGODB_DB = "cloudMarketplaceBroker";
	public static String MONGODB_USERS_COLLECTION = "users";
	public static String MONGODB_PROVIDERS_COLLECTION = "providers";
	public static enum CloudType {EUCALYPTUS, OPENSTACK};
	public static String TRUST_PARAM_NAME = "trust";
	public static String COST_PARAM_NAME = "costPerVmPerHour";
	public static String MIN_AVAILABILITY_PARAM_NAME = "minAvailability";
	public static String NO_OF_FREE_VMS_PARAM_NAME = "noOfFreeVms";
	public static String PROVIDER_ID_PARAM_NAME = "providerId";
	public static String NO_OF_VMS_PARAM_NAME = "noOfVms";
}
