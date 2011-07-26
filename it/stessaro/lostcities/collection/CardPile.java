package it.stessaro.lostcities.collection;

import java.util.Collections;
import java.util.Stack;

public abstract class CardPile extends Stack<Card> {

	private static final long serialVersionUID = 1L;
	
	public void addCard(Card card){
		
		this.push(card);
	}
	
	public int numberAtindex(int index){
		Card card_at_index =  this.get(index);
		return card_at_index.getNumber();
	}
	
	public String colorAtindex(int index){
		Card card_at_index =  this.get(index);
		return card_at_index.getColorStr();
	}
	
	public int lastNumber(){
		if (this.empty()) {
			return 0;
		}else{
			Card last_card =  this.peek();
			return last_card.getNumber();
		}
	}
	public String lastNumberStr(){
		if (this.empty()) {
			return "";
		}else{
			Card last_card =  this.peek();
			return last_card.getNumberString();
		}
	}
	
	public String lastColor(){
		Card last_card =  this.peek();
		return last_card.getColorStr();
	}
	
	public Card lastCard(){
		return this.peek();
	}
	
	public void shuffle(){
		Collections.shuffle(this);
	}
	
	public String sizeStr(){
		return Integer.toString(this.size());
	}
	
	public void print(){
		for (Card alpha : this) {
			alpha.print();	
		}
		System.out.println(" ");
	}

}
