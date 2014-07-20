package org.mcsg.music.player;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class MusicPlaylist {

	private ArrayList<File> files = new ArrayList<>();
	private String dir;
	private Random rand = new Random();
	private long seed = rand.nextInt();
	private int index = 0;

	private File file;

	public MusicPlaylist(String dir){

		File fdir = new File(dir);
		File[] afiles = fdir.listFiles();

		for(File file : afiles){
			String name = file.getName();
			if(name.endsWith(".mp3") || name.endsWith(".flac") || name.endsWith(".mp4")){
				files.add(file);
			}
		}

	}

	public String getLastSong(){
		--index;
		rand.setSeed(seed + index);
		file = files.get(rand.nextInt(files.size()));
		return file.getAbsolutePath();
	}

	public String getNextSong(){
		++index;
		rand.setSeed(seed + index);
		file = files.get(rand.nextInt(files.size()));
		return file.getAbsolutePath();
	}

	private String getFileName(){
		int index = file.getName().indexOf(".");
		return file.getName().substring(0, index);
	}

	public String getSongName(){
		String split [] =getFileName().split("-");
		if(split.length > 1){
			return split[split.length - 1].trim().replace("_", " ");
		} else {
			return "";
		}
	}

	public String getArtist(){
		String split [] =getFileName().split("-");
		return split[(split.length == 1) ? 0 : (split.length == 3) ? 1 : 0].trim().replace("_", " ");
	}

}
