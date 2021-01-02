package assets;

import objs.properties.Spritesheet;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public enum Animation {
	
	//------------------------------------------------------------
	//Enumerators (Animation)
	//------------------------------------------------------------
	
	P_EVADE("ANIM_Evade", 0.5),
	P_SPECIAL("NA", 0.5),
	P_CHARGE1("ANIM_Player_Attack2_Charge", 0.5),
	P_CHARGE2("ANIM_Player_Attack2_Shoot", 1),
	P_CHARGING_STAGES("ANIM_Player_Attack2_Charge_Stages", 1);
	
	//------------------------------------------------------------
	//Members - Constructor
	//------------------------------------------------------------
	
	private String fileName;
	private double duration;
	private boolean active;
	private int mode;
	private Spritesheet spritesheet;
	
	private Animation(String fileName, double duration) {
		this.fileName = fileName;
		this.duration = duration;
		active = false;
		mode = 0;
		
		spritesheet = new Spritesheet(fileName);
	}
	
	//------------------------------------------------------------
	//Getters - Setters
	//------------------------------------------------------------
	
	public String getFileName() {
		return fileName;
	}
	
	public double getDuration() {
		return duration;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public int getFrameCount() {
		return spritesheet.getImgCount();
		
	}
	
}
