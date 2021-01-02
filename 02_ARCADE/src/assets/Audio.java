package assets;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import config.Config;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Audio {

	private float volume;
	private File file;
	private Clip clip;

	public Audio(String startFile) {
		this.volume = Config.VOLUME;
		select(startFile);
		
	} 

	//------------------------------------------------------------
	//Media Control
	//------------------------------------------------------------
	
	public void select(String fileName) {
		file = new File("src\\assets\\sound\\" + fileName);
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(sound);
			setVolume(volume);

		} catch (Exception e) {
			System.out.println("Error while trying to load audio file: " + e);
		}
	}
	
	public void play(){
		setVolume(volume);
		clip.setFramePosition(0);
		clip.start();
	}

	public void loop(){
		setVolume(volume);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop(){
		clip.stop();
	}
	
	//------------------------------------------------------------
	//Getters -Setters
	//------------------------------------------------------------
	
	public long getTrackTime() {
		return clip.getMicrosecondLength();
	}
	
	public void setVolume(float volume) {
	    if (volume < 0f || volume > 1f)
	        throw new IllegalArgumentException("Volume not valid: " + volume);
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	    gainControl.setValue(20f * (float) Math.log10(volume));
	}
	
	public Clip getClip() {
		return clip;
	}

	//------------------------------------------------------------
	//Debug
	//------------------------------------------------------------
	
	/*
	public static void main(String[] args) {

		Audio audio = new Audio("GAME-Soundtrack");
		try {
			audio.play();
			Thread.sleep(audio.getClip().getMicrosecondLength()/1000);
		} catch (Exception e) {	}

	}
	*/
	
}

