package it.stessaro.lostcities.collection;

public class ColorPile extends CardPile {

	//stack with same color (not deck or hand)
	private static final long serialVersionUID = 1L;
	private String color;
	private int colorInt;
	
	public ColorPile(String color) {
		super();
		this.color = color;
	}
	public ColorPile(int color) {
		super();
		this.colorInt = color;
	}
	
	public String getColor() {
		return color;
	}
	public int getPos() {
		return colorInt;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
