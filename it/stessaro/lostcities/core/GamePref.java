package it.stessaro.lostcities.core;
import java.util.prefs.Preferences;

public class GamePref {
	private static final int  CARD_W = 40;
	private static final int CARD_H = 60;
	private static final int START_X = 30;
	private static final int SPACER = START_X/4;
	private static boolean DEBUG = true;
	/*Frame size*/
	private static final int WIDTH = 350;
	private static final int HEIGHT = 700;
	/*Colors*/
	private static final int RED = 0;
	private static final int WHITE = 1;
	private static final int GREEN = 2;
	private static final int BLUE = 3;
	private static final int YELLOW = 4;
	
	
	private static Preferences p = Preferences.userRoot().node("it").node("stessaro").node("lostCities");
	
	public static int get_Card_Width(){
		return p.getInt("card_width", CARD_W);
	}
	public static int get_Card_Height(){
		return p.getInt("card_width", CARD_H);
	}
	public static int get_Start_X(){
		return p.getInt("startXposition", START_X);
	}
	public static int get_Spacer(){
		return p.getInt("spacer", SPACER);
	}
	public static boolean get_Debug(){
		return p.getBoolean("debug", DEBUG);
	}
	//
	public static int get_RedCard(){
		return p.getInt("red", RED);
	}
	public static int get_WhiteCard(){
		return p.getInt("white", WHITE);
	}
	public static int get_GreenCard(){
		return p.getInt("green", GREEN);
	}
	public static int get_BlueCard(){
		return p.getInt("blue", BLUE);
	}
	public static int get_YellowCard(){
		return p.getInt("yellow", YELLOW);
	}
	public static int get_Width(){
		return p.getInt("width", WIDTH);
	}
	public static int get_Height(){
		return p.getInt("height", HEIGHT);
	}
	
}
