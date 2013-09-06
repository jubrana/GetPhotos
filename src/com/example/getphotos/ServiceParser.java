/**
 * 
 */
package com.example.getphotos;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * @author Juraj Brana
 * 
 * Interface kojeg moraju implementirati parseri svih servisa (Instagram, Flickr....).
 * 
 * Svaki se parser jednostavno (kroz metodu execute(ServiceParser... parsers) prosljeðuje ExtendedTask-u).
 * 
 * Objekte klase koje implementiraju ServiceParser Interface jednostavno proslijedimo ExtededTask objektu kojeg smo
 * inicijalizirali u onCreate() metodi naše aktivnosti, npr:
 * 
 * 		new ExtendedTask(MyActivity.this).execute(new InstagramParser(), new FlickrParser(), ... , AnyParser());
 * 
 * èime je postignuta modularnost, što znaèi da jedino što moramo uèiniti jest da izvedemo svoj parser implementirajuæi 
 * ServiceParser interface te objekt tog parsera proslijedimo ExtendedTasku.
 *         
 */
public interface ServiceParser  {

	
	/**
	 * Metoda vraæa parsane podatke fotografija spremljene u objekt Photos
	 * 
	 * @return Objekt Photos koji sadrži podatke fotografija 
	 */
	Photos getParsedItems();
	
	/**
	 * Metoda parsira JSON response sa zadanog servisa
	 *
	 */
	void parseJSONResponse();

	/**
	 * Metoda u kojoj se spajamo na servis i vuèemo JSON response
	 * 
	 * @param url URL API Endpoint-a zadanog servisa
	 * @return JSON response
	 * @throws IOException
	 */
	String getJSONresponse(String url) throws IOException;
	

}
