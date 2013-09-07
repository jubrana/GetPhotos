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
	
	private Photos photos;

	@Override
	public void parseJSONResponse() {

		String photoUrl;
		String photoAuthor;
		String photoTitle;
		String type;
		JsonParser parser;
		JsonElement jsonElement;
		JsonObject jsonObject;
		JsonArray jsonData;
		
		try {

			photos = Photos.getInstance();
			parser = new JsonParser();
			jsonElement = parser.parse(getJSONresponse(this.URL));
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
			
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public String getJSONresponse(String url) throws IOException {

		URL urlObj = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
		InputStream stream;
		String jsonRes;

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

		jsonRes = IOUtils.toString(stream, "UTF-8");
		stream.close();

		return jsonRes;

	}

}
