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
public class RunGetHTTP implements Runnable {
	
	/***************************************************************************************
     * Exemple de méthode GET : récupération de données
     ***************************************************************************************/
    
    public void getData() throws JSONException, ClientProtocolException, IOException {
    	
    	System.out.println("Récupération de DOnnées pour le streamID : "+ TestHTTPLO.sGetDataLinkBase + TestHTTPLO.sStreamID);

    	//StringJSON est le résultat en String de la requête : elle contient les données
    	String stringJSON = Request.Get(TestHTTPLO.sGetDataLinkBase + TestHTTPLO.sStreamID) //concaténation du lien définis pour la requête+le streamID
				.addHeader("limit", TestHTTPLO.sNumberOfPageLimit)    						//Ajout du nombre de page désiré
				.addHeader("X-API-Key", TestHTTPLO.sAPIKey)     							//ajout de la clé d'API Live Objects
				.connectTimeout(1000)
		        .socketTimeout(1000)
		        .execute()             //Lancement de la requête
		        .returnContent().toString(); //Récupération et formatage (en String) des donées envoyées dans la variable stringJSON
		
		//conversion de stringJSON en JSONArray
		JSONArray dataArray = new JSONArray(stringJSON);
		
		//parcours du la tableau pour récupérer chaque lot de données
		int longueur = dataArray.length();
		
		// On met le curseur � la fin de la requ�te pr�c�dente
		TestHTTPLO.fenetreTestHTTPLO.textPane.setCaretPosition( TestHTTPLO.fenetreTestHTTPLO.textPane.getDocument().getLength());
		
		// Affichage de la requete
		TestHTTPLO.fenetreTestHTTPLO.textPane.append("\n");
		TestHTTPLO.fenetreTestHTTPLO.textPane.append("URL : " + TestHTTPLO.sGetDataLinkBase + "\n");
		TestHTTPLO.fenetreTestHTTPLO.textPane.append("Nb Pages : " + TestHTTPLO.sNumberOfPageLimit + "\n");
		TestHTTPLO.fenetreTestHTTPLO.textPane.append("API Key : " + TestHTTPLO.sAPIKey + "\n");
		TestHTTPLO.fenetreTestHTTPLO.textPane.append("Stream ID : " + TestHTTPLO.sStreamID + "\n");
		TestHTTPLO.fenetreTestHTTPLO.textPane.append("Nb Reponses : " + longueur + "\n");
		for(int i = 0; i < longueur; i++){
			
			JSONObject dataJson = (JSONObject)dataArray.get(i); //dataJSON représente une valeurs de données dans le tableau
			
			//Affichage de chaque élément du tableau JSON
			System.out.println("Données numéro "+i+"==> "+dataJson.toString());
			TestHTTPLO.fenetreTestHTTPLO.textPane.append("Données numéro "+i+"==> "+dataJson.toString()+"\n");
		}
	}
    
 
    //
    // Actuellement non ex�cut�e !!!
    //
    /***************************************************************************************
     * Exemple de méthode POST : envoie d'une commande
     * Elle prend en paramètre l'événement à lancer, ainsi que les paramètres de latitue
     * et de longitude propre au projet
     ***************************************************************************************/
    
    public void sendCommand(String event, int latitude, int longitude) throws ClientProtocolException, IOException{
			String stringJSON = Request.Post(TestHTTPLO.POST_COMMANDS_LINK) //ajout du lien pour l'envoie de commande définis dans le swagger
			        .useExpectContinue()
			        .addHeader("X-API-Key", TestHTTPLO.sAPIKey)         //ajout de la clé
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
    
	/*
	 * 
	 * On lance un thread pour faire le get
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			TestHTTPLO.fenetreTestHTTPLO.boutonGet.setEnabled(false);
			getData();
			TestHTTPLO.fenetreTestHTTPLO.boutonGet.setEnabled(true);
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			TestHTTPLO.fenetreTestHTTPLO.boutonGet.setEnabled(true);
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
