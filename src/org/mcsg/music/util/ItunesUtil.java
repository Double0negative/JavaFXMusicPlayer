package org.mcsg.music.util;

import java.io.File;
import java.util.List;

import javafx.scene.image.Image;

import com.google.gson.Gson;

public class ItunesUtil {

	private static final String ITUNES_URL = "http://itunes.apple.com/search?term={0}entity=song";
	private static  Image DEFAULT_IMAGE;
	
	static {
			DEFAULT_IMAGE = new Image(new File("/home/drew/Downloads/default.png").toURI().toString());
	}
	
	
	public static Image getImageFromItunes(String search) throws Exception{
		String url = ITUNES_URL.replace("{0}", search).replace(" ", "%20");
		String json = WebClient.request(url);
		Itunes itun = new Gson().fromJson(json, Itunes.class);
		if(itun.results.size() != 0){
			return new Image(itun.results.get(0).artworkUrl100);
		} else {
			return DEFAULT_IMAGE;
		}
	}


	class Itunes {
		List<ItunesResult> results;

		class ItunesResult {
			String artworkUrl100;
		}
	}
}
