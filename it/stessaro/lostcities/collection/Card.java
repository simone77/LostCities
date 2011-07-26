package it.stessaro.lostcities.collection;
import it.stessaro.lostcities.core.GamePref;

import java.awt.Color;

public class Card implements Comparable<Card>{

	/**/
	private static final int RED = GamePref.get_RedCard();
	private static final int WHITE = GamePref.get_WhiteCard();
	private static final int BLUE = GamePref.get_BlueCard();
	private static final int GREEN = GamePref.get_GreenCard();
	private static final int YELLOW = GamePref.get_YellowCard();
	/**/
	private int number;
	private String colorStr;
	private int weight;
	
	public int getWeight() {
		return weight;
	}

	public void addWeight(int w){
		this.weight = this.number + w;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Card(int number, String color){
		this.number = number;
		this.colorStr = color;
		this.weight = -number;
	}
	
	public String getColorStr(){
		return this.colorStr;		
	}
	
	public Color getColor(){
		if ( this.checkColor("r") ) {
			return Color.red;
		}else if ( this.checkColor("y") ) {
			return Color.yellow;
		}else if ( this.checkColor("g") ) {
			return Color.green;
		}else if ( this.checkColor("w") ) {
			return Color.white;
		}else if ( this.checkColor("b") ) {
			return Color.cyan;
		}else{
			return Color.black;
		}
	}
	public int getInt(){
		if ( this.checkColor("r") ) {
			return RED;
		}else if ( this.checkColor("W") ) {
			return WHITE;
		}else if ( this.checkColor("g") ) {
			return GREEN;
		}else if ( this.checkColor("b") ) {
			return BLUE;
		}else{
			return YELLOW;
		}
	}
	
	public String getNumberString(){
		if (number == 1) {
			return "*";
		}else{
			return Integer.toString(number);
		}
	}
	
	public String toString(){
		return this.getNumberString().concat(colorStr);
	}
	
	public int getNumber(){
		return number;
	}
	
	public boolean checkColor(String col){
		if (col.equals(this.colorStr)) {
			return true;
		}
		return false;
	}
	
	public void print(){
		System.out.print(number+colorStr+"("+weight+")"+" ");
	}

	@Override
	public int compareTo(Card comp_card) {
		// TODO fix this sorting
		return this.number - comp_card.number;
		//return this.colorStr.compareTo(comp_card.colorStr);
	}
}