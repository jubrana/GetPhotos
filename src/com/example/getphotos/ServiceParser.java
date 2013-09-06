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
 * Svaki se parser jednostavno (kroz metodu execute(ServiceParser... parsers) proslje�uje ExtendedTask-u).
 * 
 * Objekte klase koje implementiraju ServiceParser Interface jednostavno proslijedimo ExtededTask objektu kojeg smo
 * inicijalizirali u onCreate() metodi na�e aktivnosti, npr:
 * 
 * 		new ExtendedTask(MyActivity.this).execute(new InstagramParser(), new FlickrParser(), ... , AnyParser());
 * 
 * �ime je postignuta modularnost, �to zna�i da jedino �to moramo u�initi jest da izvedemo svoj parser implementiraju�i 
 * ServiceParser interface te objekt tog parsera proslijedimo ExtendedTasku.
 *         
 */
public interface ServiceParser  {

	
	/**
	 * Metoda vra�a parsane podatke fotografija spremljene u objekt Photos
	 * 
	 * @return Objekt Photos koji sadr�i podatke fotografija 
	 */
	Photos getParsedItems();
	
	/**
	 * Metoda parsira JSON response sa zadanog servisa
	 *
	 */
	void parseJSONResponse();

	/**
	 * Metoda u kojoj se spajamo na servis i vu�emo JSON response
	 * 
	 * @param url URL API Endpoint-a zadanog servisa
	 * @return JSON response
	 * @throws IOException
	 */
	String getJSONresponse(String url) throws IOException;
	

}
