package it.stessaro.lostcities.collection;

import it.stessaro.lostcities.core.GamePref;

import java.util.ArrayList;
import java.util.List;


public class Expeditions extends ArrayList<Expedition>{

	/**/
	private static final int RED = GamePref.get_RedCard();
	private static final int WHITE = GamePref.get_WhiteCard();
	private static final int BLUE = GamePref.get_BlueCard();
	private static final int GREEN = GamePref.get_GreenCard();
	private static final int YELLOW = GamePref.get_YellowCard();
	/**/
	private static final long serialVersionUID = 1L;
	private List<Card> playedCards;
	private List<Card> topCards;

	public Expeditions(){
		playedCards = new ArrayList<Card>();
		topCards = new ArrayList<Card>();
		
		this.add(RED, new Expedition("r"));
		this.add(WHITE, new Expedition("w"));
		this.add(GREEN, new Expedition("g"));
		this.add(BLUE, new Expedition("b"));
		this.add(YELLOW, new Expedition("y"));
	}

	public boolean addCard(Card card){
		int pos = this.getPosition(card);
		if (card.getNumber() >= this.get(pos).lastNumber()) {
			this.get(pos).addCard(card);
			playedCards.add(card);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isCardPlayable(Card card){
		int pos = this.getPosition(card);
		if (card.getNumber() >= this.get(pos).lastNumber()) {
			return true;
		}else {
			return false;
		}
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
		for (Expedition expedition : this) {
			topCards.add(expedition.lastCard());
		}
		return topCards;
	}
	
	public String getMax(){
		String max = new String();
		int m = 0;
		for (Expedition expedition : this) {
			if (expedition.exp_value() > m) {
				max = expedition.getColor();
				m = expedition.exp_value();
			}
		}
		return max;
	}
	
	public int getPoint(int pos){
		int lista[] = new int[5];
		for (Expedition expedition : this) {
			lista[expedition.getPos()] = expedition.exp_value();
		}
		return lista[pos];
	}
	
	public int getScore(){
		int total = 0;
		for (Expedition expedition : this) {
			total = total + expedition.exp_value();
		}
		return total;
	}
}