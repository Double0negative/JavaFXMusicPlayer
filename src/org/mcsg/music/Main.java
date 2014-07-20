package org.mcsg.music;
	
import org.mcsg.music.layout.LayoutManager;
import org.mcsg.music.style.StyleManager;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Stage stage = new Stage(StageStyle.DECORATED);
		try {
			LayoutManager mng = new LayoutManager("music");
			stage.setTitle("Ten.java Music!");
			Parent parent = mng.getParent();
			parent.getStylesheets().add(StyleManager.getCss("music"));
			Scene scene = new Scene(parent,400,160);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
