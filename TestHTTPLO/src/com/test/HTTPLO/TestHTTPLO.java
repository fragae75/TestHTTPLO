package com.test.HTTPLO;


public class TestHTTPLO {
	public final static String GET_PARAMETERS_LINK = "https://liveobjects.orange-business.com/api/v0/assets/DemoJavaMQTTLast/00-11-22-26-09-17/params";
	public final static String GET_DATA_LINK_BASE = "https://liveobjects.orange-business.com/api/v0/data/streams/";
	public final static String POST_COMMANDS_LINK = "https://liveobjects.orange-business.com/api/v0/assets/DemoJavaMQTTLast/00-11-22-26-09-17/commands";
//	private static final String STREAM_ID_TEMPERATURE = "TemperaturePerZone5";
	private static final String STREAM_ID = "355570061616473";
//	private static final String STREAM_ID = "test.franck.01";
//	private static final String STREAM_ID = "testfranck01";
	public final static String NUMBER_OF_PAGE_LIMIT = "5";
	public final static String EVENEMENT_SET_LOCALISATION = "SET LOCALISATION";
	

	static String sAPIKey = ""; 				// A récupérer dans la classe CleAPI
	static String sStreamID = STREAM_ID; 		// testfranck01, test.franck.01
	static String sGetDataLinkBase = GET_DATA_LINK_BASE;
	static String sNumberOfPageLimit = NUMBER_OF_PAGE_LIMIT;

	static Fenetre fenetreTestHTTPLO;
	
	public static void main(String[] args) {
       sAPIKey = CleLiveObjectsAPI.GetAPIKey();
       System.out.println("Clé API : " + sAPIKey);
       
       fenetreTestHTTPLO = new Fenetre();
/*
       Services2 services2LO = new Services2();
       Thread threat = new Thread(services2LO);
       threat.start();
*/		
	}

}
