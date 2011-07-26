package it.stessaro.lostcities.collection;

import it.stessaro.lostcities.core.GamePref;

import java.util.ArrayList;
import java.util.List;

public class Discards  extends ArrayList<Discard>{
	
	private static final long serialVersionUID = 1L;
	/**/
	private static final int RED = GamePref.get_RedCard();
	private static final int WHITE = GamePref.get_WhiteCard();
	private static final int BLUE = GamePref.get_BlueCard();
	private static final int GREEN = GamePref.get_GreenCard();
	private static final int YELLOW = GamePref.get_YellowCard();
	/**/
	private List<Card> topCards;
	public Discards() {
		this.add(0, new Discard("r"));
		this.add(1, new Discard("w"));
		this.add(2, new Discard("g"));
		this.add(3, new Discard("b"));
		this.add(4, new Discard("y"));
	}
	
	public boolean addCard(Card card){
		int pos = this.getPosition(card);
			this.get(pos).addCard(card);
			return true;
	}
	
	private int getPosition(Card card){
		String colore = card.getColorStr();
		if (colore.equals("r")) {
			return 0;
		}else if (colore.equals("w")) {
			return 1;
		}else if (colore.equals("g")) {
			return 2;
		}else if (colore.equals("b")) {
			return 3;
		}else{
			return 4;
		}
	}
	
	public List<Card> getTopCards(){
		for (Discard discard : this) {
			topCards.add(discard.lastCard());
		}
		return topCards;
	}
	public Card drawCard(String colore){
		if (colore.equals("r")) {
			return this.get(0).pop();
		}else if (colore.equals("w")) {
			return this.get(1).pop();
		}else if (colore.equals("g")) {
			return this.get(2).pop();
		}else if (colore.equals("b")) {
			return this.get(3).pop();
		}else{
			return this.get(4).pop();
		}	
	}
	
}
