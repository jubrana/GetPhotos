package com.example.getphotos;

public class Photo {

	private String url;
	private String author;
	private String title;
	
	public Photo(String url, String author, String title) {
		
		this.author = author;
		this.title = title;
		this.url = url;
	}

	public void setUrl(String url) {

		this.url = url;
	}

	public void setAuthor(String author) {

		this.author = author;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getUrl() {

		return this.url;
	}

	public String getAuthor() {

		return this.author;
	}

	public String getTitle() {

		return this.title;
	}
	
	public String toString() {
		
		return title + "\n" + author + "\n" + url + "\n\n";
	}
}
