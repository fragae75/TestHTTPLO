package com.test.HTTPLO;

public class TestHTTPLO {
	static String API_KEY = ""; 				// A r�cup�rer dans la classe CleAPI

	public static void main(String[] args) {
       API_KEY = CleLiveObjectsAPI.GetAPIKey();
       System.out.println("Cl� API : " + API_KEY);
       

		Services2 services2LO = new Services2();
		Thread threat = new Thread(services2LO);
		threat.start();
			
	}

}
