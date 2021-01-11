package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.Gui;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Config {

	private static String source = "config.txt";
	private static String key = " = ";
	private static String line;
	private static int i = 0;

	private static BufferedReader r;
	private static ConfigVariables[] config = ConfigVariables.values();

	public static boolean AUDIO;
	public static float VOLUME;
	public static int CANVAS_WIDTH;
	public static int CANVAS_HEIGHT;
	public static double SCALE;
	public static double REFRESH_RATE;
	public static int TILESIZE;
	public static int P_EVADE_DELAY;
	public static int P_CHARGE_DELAY;
	
	public void init() {
		try {
			r = new BufferedReader(new FileReader("src\\config\\" + source));

			while ((line = r.readLine()) != null) {
				String command = line.substring(0, config[i].toString().length());

				if(command.equals(config[i].toString())) {
					setupCommand(config[i]);

				}
				i++;
			}
			r.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	private void setupCommand(ConfigVariables configVariables) {
		String operation = configVariables.toString() + key;
		operation = line.substring(operation.length());

		switch(configVariables) {
		case AUDIO:
			AUDIO = Boolean.parseBoolean(operation);
			break;
			
		case VOLUME:
			VOLUME = Float.parseFloat(operation);
			break;

		case CANVAS_HEIGHT:
			CANVAS_HEIGHT = Integer.parseInt(operation);
			break;

		case CANVAS_WIDTH:
			CANVAS_WIDTH = Integer.parseInt(operation);
			break;

		case REFRESH_RATE:
			REFRESH_RATE = 1/(Double.parseDouble(operation));
			break;

		case TILESIZE:
			TILESIZE = Integer.parseInt(operation);
			break;

		case P_EVADE_DELAY:
			P_EVADE_DELAY = Integer.parseInt(operation);
			break;
			
		case P_CHARGE_DELAY:
			P_CHARGE_DELAY = Integer.parseInt(operation);
			break;

		}
	}
	
	public void refresh(Gui gui) {
		CANVAS_WIDTH = gui.getCanvas().getWidth();
		CANVAS_HEIGHT = gui.getCanvas().getHeight();
	}
}