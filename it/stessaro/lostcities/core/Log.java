package it.stessaro.lostcities.core;

public class Log{
	
	private static boolean DEBUG = GamePref.get_Debug();
	
	public static void print(String line) {
		if (DEBUG) {
			System.out.println(line);
		}
	}
	public static void print(boolean line) {
		if (DEBUG) {
			System.out.println(line);
		}
	}

}
