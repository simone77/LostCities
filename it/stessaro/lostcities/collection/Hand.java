package it.stessaro.lostcities.collection;


import java.util.Collections;

public class Hand extends CardPile {

	
	private static final long serialVersionUID = 1L;
	public Hand(){
		super();
	}
	public void sort(){
		Collections.sort(this, new CardSortByName());
	}
	public void sortbyWeight(){
		Collections.sort(this, new CardSortByWeight());
	}
	
	public int getPoint(int pos){
		int lista[] = {0, 0, 0, 0, 0};
		for (Card card : this) {
			lista[card.getInt()] = lista[card.getInt()] + card.getWeight();
		}
		return lista[pos];
	}
}
