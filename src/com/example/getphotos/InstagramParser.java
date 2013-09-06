package com.example.getphotos;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import android.content.Context;

public class InstagramParser implements ServiceParser {

	private final String URL = "https://api.instagram.com/v1/media/"
			+ "popular?client_id=511ba4a600ab4e139b649f4567760413";
	private String photoUrl;
	private String photoAuthor;
	private String photoTitle;
	private String type;
	private URL urlObj;
	private HttpURLConnection conn;
	private InputStream stream;
	private JsonParser parser;
	private JsonElement jsonElement;
	private JsonObject jsonObject;
	private JsonArray jsonData;
	private String jsonRes = null;
	private Photos photos;
	private static InstagramParser instagramObj = null;

	public static InstagramParser getInstance() {

		if (instagramObj == null) {
			instagramObj = new InstagramParser();
		}
		return instagramObj;
	}

	@Override
	public Photos getParsedItems() {

		return this.photos;
	}

	@Override
	public void parseJSONResponse() {

		try {

			getJSONresponse(this.URL);

			photos = Photos.getInstance();
			parser = new JsonParser();
			jsonElement = parser.parse(jsonRes);
			jsonObject = jsonElement.getAsJsonObject();
			jsonData = jsonObject.getAsJsonArray("data");

			for (int i = 0; i < jsonData.size(); i++) {

				jsonObject = jsonData.get(i).getAsJsonObject();
				type = jsonObject.get("type").toString();

				if (type.replaceAll("\"|\"", "").equals("image")) {

					photoUrl = jsonObject.get("images").getAsJsonObject()
							.get("thumbnail").getAsJsonObject().get("url")
							.toString().replaceAll("\"|\"", "");

					if (jsonObject.get("caption").isJsonObject()) {

						photoTitle = jsonObject.get("caption")
								.getAsJsonObject().get("text").toString()
								.replaceAll("\"|\"", "");

						if (photoTitle.length() >= 35) {

							photoTitle = photoTitle.subSequence(0, 34) + "...";
						}

					} else {

						photoTitle = "No Image Title";
					}

					photoAuthor = jsonObject.get("user").getAsJsonObject()
							.get("username").toString().replaceAll("\"|\"", "");

					photos.addPhoto(new Photo(photoUrl, photoAuthor, photoTitle));
				}

			}
			
			this.jsonRes = null;
			
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public String getJSONresponse(String url) throws IOException {

		urlObj = new URL(url);
		conn = (HttpURLConnection) urlObj.openConnection();

		if (conn == null) {

			return null;
		}

		conn.setReadTimeout(15000 /* milliseconds */);
		conn.setConnectTimeout(15000 /* milliseconds */);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.connect();
		stream = conn.getInputStream();

		if (stream == null) {

			return null;
		}

		this.jsonRes = IOUtils.toString(stream, "UTF-8");
		stream.close();

		return jsonRes;

	}

}
