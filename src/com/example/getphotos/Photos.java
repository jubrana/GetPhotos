package com.example.getphotos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Photos {

	
	private static Photos instance = null;
	
	public static Photos getInstance() {
		
		if(instance == null) {
			
			instance = new Photos();
		}
		
		return instance;
	}
	
	private Photos() {
		
	}
	
	private List<Photo> photos = new ArrayList<Photo>();
	
	
	public List<Photo> getPhotos() {
		
		return this.photos;
	}
	
	public void addPhoto(Photo photo) {
		
		this.photos.add(photo);
	}
}
