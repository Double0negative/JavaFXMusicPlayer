package org.mcsg.music.style;

public class StyleManager {
	public static void load(){
	
	}
	
	
	public static String getCss(String css){
		return StyleManager.class.getResource("css/"+css+".css").toExternalForm();
	}
	
}
