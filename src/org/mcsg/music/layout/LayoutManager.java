package org.mcsg.music.layout;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;


public class LayoutManager {

	public static Parent getLayout(String fxml){
		try {
			return (Parent)FXMLLoader.load(LayoutManager.class.getResource("fxml/"+fxml+".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	private FXMLLoader loader;
	private String fxml;
	private Parent parent;
	
	public LayoutManager(String fxml){
		this.fxml = fxml;
		loader = new FXMLLoader();
		try {
			parent = loader.load(LayoutManager.class.getResource("fxml/"+fxml+".fxml").openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FXMLLoader getLoader(){
		return loader;
	}
	
	public <T> T getController(){
		return loader.getController();
	}
	
	public Parent getParent(){
		return parent;
	}
}
