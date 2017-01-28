package com.test.HTTPLO;

import java.io.IOException;

import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * This example demonstrates basics of request execution with the HttpClient fluent API.
 */
public class Services2 implements Runnable {
	
	public final static String GET_PARAMETERS_LINK = "https://liveobjects.orange-business.com/api/v0/assets/DemoJavaMQTTLast/00-11-22-26-09-17/params";
//	public final static String GET_DATA_LINK_BASE = "https://liveobjects.orange-business.com/api/v0/data/streams/";
	public final static String GET_DATA_LINK_BASE = "https://liveobjects.orange-business.com/api/v0/data/streams/";
	
	public final static String POST_COMMANDS_LINK = "https://liveobjects.orange-business.com/api/v0/assets/DemoJavaMQTTLast/00-11-22-26-09-17/commands";
	
//	private static final String STREAM_ID_TEMPERATURE = "TemperaturePerZone5";
//	private static final String STREAM_ID = "355570061616473";
//	private static final String STREAM_ID = "test.franck.01";
	private static final String STREAM_ID = "testfranck01";
	public final static String NUMBER_OF_PAGE_LIMIT = "5";
	
	public final static String EVENEMENT_SET_LOCALISATION = "SET LOCALISATION";
	
	
	
	
	/***************************************************************************************
     * Exemple de méthode GET : récupération de données
     ***************************************************************************************/
    
    public void getData() throws JSONException, ClientProtocolException, IOException{
	System.out.println("Récupération de DOnnées pour le streamID : "+GET_DATA_LINK_BASE+STREAM_ID);
    	//StringJSON est le résultat en String de la requête : elle contient les données
    	String stringJSON = Request.Get(GET_DATA_LINK_BASE+STREAM_ID)  //concaténation du lien définis pour la requête+le streamID
				.addHeader("limit", NUMBER_OF_PAGE_LIMIT)    //Ajout du nombre de page désiré
				.addHeader("X-API-Key", TestHTTPLO.API_KEY)     //ajout de la clé d'API Live Objects
		        
				.connectTimeout(1000)
		        .socketTimeout(1000)
		        .execute()             //Lancement de la requête
		        .returnContent().toString(); //Récupération et formatage (en String) des donées envoyées dans la variable stringJSON
    	
		
		//conversion de stringJSON en JSONArray
		JSONArray dataArray = new JSONArray(stringJSON);
		
		//parcours du la tableau pour récupérer chaque lot de données
		int longueur = dataArray.length();
		for(int i = 0; i < longueur; i++){
			
			JSONObject dataJson = (JSONObject)dataArray.get(i); //dataJSON représente une valeurs de données dans le tableau
			
			//Affichage de chaque élément du tableau JSON
			System.out.println("Données numéro "+i+"==> "+dataJson.toString());
			
		}
	}
    
    
    /***************************************************************************************
     * Exemple de méthode POST : envoie d'une commande
     * Elle prend en paramètre l'événement à lancer, ainsi que les paramètres de latitue
     * et de longitude propre au projet
     ***************************************************************************************/
    
    public void sendCommand(String event, int latitude, int longitude) throws ClientProtocolException, IOException{
			String stringJSON = Request.Post(POST_COMMANDS_LINK) //ajout du lien pour l'envoie de commande définis dans le swagger
			        .useExpectContinue()
			        .addHeader("X-API-Key", TestHTTPLO.API_KEY)         //ajout de la clé
			        .version(HttpVersion.HTTP_1_1)
			        
			        .bodyString("{"                            //ajout du corps de la requête au format JSON (en tenant compte des
			        		  +"\"event\": \""+event+"\","     //Contrainte de JAVA
			        		  +"\"data\": {"
			        		    +"\"latitude\":"+latitude+","
			        		    +"\"longitude\":"+longitude+""
			        		  +"}"
			        		+"}", ContentType.APPLICATION_JSON)
			        
			        .execute()                                 //Exécution de la requête
			        .returnContent().toString();               //Récupération et formatage de la réponse à l'envoie de commande
			
			System.out.println("La réponse à l'envoie de commande est : "+stringJSON);
	}
    

	@Override
	public void run() {
		try {
			getData();
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
/*		try {
			sendCommand(EVENEMENT_SET_LOCALISATION, 22, 44);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/	
	}

}
