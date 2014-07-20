package org.mcsg.music.player;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

public class MusicPlayer {

	EmbeddedMediaPlayerComponent embedded;
	MediaPlayer player;
	private MusicPlaylist playlist;
	private boolean paused = false;


	public MusicPlayer(MusicPlaylist playlist){
		this.playlist = playlist;
		embedded = new EmbeddedMediaPlayerComponent();
		player = embedded.getMediaPlayer();
		JFrame frame = new JFrame();
		frame.setSize(0,0);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setContentPane(embedded);
	}

	public void play(){
		if(paused){
			player.play();
		} else {
			play(playlist.getNextSong());
		}
	}
	
	private void play(String file){
		player.playMedia(file);
	}

	public void pause(){
		player.pause();
		paused = true;
	}

	public void next(){
		play(playlist.getNextSong());
	}
	
	public void last(){
		play(playlist.getLastSong());
	}

	public void setTime(long time){
		player.setTime(time);
	}
	
	public MediaPlayer getPlayer(){
		return player;
	}
	
	
	


}