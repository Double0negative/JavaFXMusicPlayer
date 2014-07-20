package org.mcsg.music.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.ResourceBundle;

import org.mcsg.music.player.MusicPlayer;
import org.mcsg.music.player.MusicPlaylist;
import org.mcsg.music.util.ItunesUtil;
import org.mcsg.music.util.ThreadUtil;
import org.mcsg.music.visualize.Visualizer;

import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MusicController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView cover;

	@FXML
	private Label song;

	@FXML
	private Button StopButton;

	@FXML
	private Slider seeker;

	@FXML
	private Label artist;

	@FXML
	private Button closeButton;

	@FXML
	private Button forwardButton;

	@FXML
	private Button BackButton;

	@FXML
	private Label nowPlaying;

	@FXML
	private Button VisualizeButton;

	private MusicPlayer player;
	private MusicPlaylist playlist;
	private long time = 0;
	private boolean paused;

	@FXML
	void clickBack(ActionEvent event) {
		player.last();
	}

	@FXML
	void clickStop(ActionEvent event) {
		paused = !paused;
		((Button) event.getSource()).setText(paused ? "   Play   " : "  Pause  ");
		if (paused) {
			player.pause();
		} else
			player.play();
	}

	@FXML
	void clickForward(ActionEvent event) {
		player.next();
	}

	Visualizer visual;
	@FXML
	void clickVisualize(ActionEvent event) {
		if(visual == null){
			visual = new Visualizer();
			visual.setVisible(true);
			visual.startDraw();
			visual.addWindowListener(new WindowListener() {
				public void windowOpened(WindowEvent e) {}
				public void windowIconified(WindowEvent e) {}
				public void windowDeiconified(WindowEvent e) {}
				public void windowDeactivated(WindowEvent e) {}
				public void windowClosing(WindowEvent e) {}
				public void windowClosed(WindowEvent e) {
					visual.stop();
					visual = null;
				}
				public void windowActivated(WindowEvent e) {}
			});
		} else {
			visual.stop();
			visual.setVisible(false);
			visual.dispose();
			visual = null;
		}
	}

	@FXML
	void clickClose(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void initialize() {
		seeker.valueProperty().addListener((ObservableValue<? extends Number> observable,
				Number oldValue, Number newValue) -> {
					if(time != newValue.longValue())
						player.setTime(newValue.longValue());
				});
		playlist = new MusicPlaylist("/mnt/4A0AE9C2333C6717/Music");
		player = new MusicPlayer(playlist);

		player.getPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
			public void timeChanged(MediaPlayer arg0,long arg1) {
				time = arg0.getTime();
				Platform.runLater(() -> {
					seeker.setMax(arg0.getLength());
					seeker.setValue(arg0.getTime());
					if(time % 5000 == 0 || time < 700)
						cover.setImage(SwingFXUtils.toFXImage(player.getPlayer().getSnapshot(), null));
				});
			}
			public void videoOutput(MediaPlayer arg0, int arg1) {}
			public void titleChanged(MediaPlayer arg0,int arg1) {}
			public void subItemPlayed(MediaPlayer arg0,int arg1) {}
			public void subItemFinished(MediaPlayer arg0,int arg1) {}
			public void stopped(MediaPlayer arg0) {}
			public void snapshotTaken(MediaPlayer arg0,String arg1) {}
			public void seekableChanged(MediaPlayer arg0,int arg1) {}
			public void positionChanged(MediaPlayer arg0,float arg1) {}
			public void paused(MediaPlayer arg0) {}
			public void pausableChanged(MediaPlayer arg0,int arg1) {}
			public void opening(MediaPlayer arg0) {}
			public void newMedia(MediaPlayer arg0) {}
			public void mediaSubItemAdded(MediaPlayer arg0,libvlc_media_t arg1) {}
			public void mediaStateChanged(MediaPlayer arg0,int arg1) {}
			public void mediaMetaChanged(MediaPlayer arg0,int arg1) {}
			public void mediaFreed(MediaPlayer arg0) {}
			public void mediaDurationChanged(MediaPlayer arg0, long arg1) {}
			public void mediaChanged(MediaPlayer arg0,libvlc_media_t arg1, String arg2) {
				Platform.runLater(() -> {
					artist.setText(playlist.getArtist());
					song.setText(playlist.getSongName());
					seeker.setMin(0);
				});
			}
			public void lengthChanged(MediaPlayer arg0,long arg1) {}
			public void forward(MediaPlayer arg0) {}
			public void finished(MediaPlayer arg0) {
				player.next();
				seeker.setValue(0);
			}
			public void error(MediaPlayer arg0) {}
			public void endOfSubItems(MediaPlayer arg0) {}
			public void buffering(MediaPlayer arg0, float arg1) {}
			public void backward(MediaPlayer arg0) {}
		});


		player.play();

	}
}
