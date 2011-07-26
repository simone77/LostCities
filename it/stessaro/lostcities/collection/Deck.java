package it.stessaro.lostcities.collection;


//Deck
public class Deck extends CardPile{

	private Hand p1_hand, pc_hand;
	private static final long serialVersionUID = 1L;
	public Deck(){
		String[] arr_deck = {"w","w","w","w","w","w","w","w","w","w","w","w",
				"r","r","r","r","r","r","r","r","r","r","r","r",
				"g","g","g","g","g","g","g","g","g","g","g","g",
				"y","y","y","y","y","y","y","y","y","y","y","y",
				"b","b","b","b","b","b","b","b","b","b","b","b"};
		
		int[] arr_numbers  ={1,1,1,2,3,4,5,6,7,8,9,10,1,1,1,2,3,4,5,6,7,8,9,10,
				1,1,1,2,3,4,5,6,7,8,9,10,1,1,1,2,3,4,5,6,7,8,9,10,1,1,1,2,3,4,5,6,7,8,9,10};
		//prepare the deck
		for (int i = 0; i < arr_deck.length; i++) {			
			this.add(new Card(arr_numbers[i],arr_deck[i] ) );
		}
		//shuffle the deck
		this.shuffle();
		
		p1_hand = new Hand();
		pc_hand = new Hand();
		
		//Give cards
		for (int i = 0; i < 8; i++) {
			p1_hand.add(this.pop());
			pc_hand.add(this.pop());
		}

		//sort hands
		p1_hand.sort();
		pc_hand.sort();
	}
	
	public Hand getPlayerHand(){
		return p1_hand;
	}
	
	public Hand getHandPC(){
		return pc_hand;
	}
	
	public boolean playerDrawCard(){
		if (this.size() == 0) {
			return false;
		}else {
			Card draw_card = this.pop();
			p1_hand.add(draw_card);
			return true;
		}

	}
	
	public boolean pcDrawCard(){
		if (this.size() == 0) {
			return false;
		}else {
			Card draw_card = this.pop();
			pc_hand.add(draw_card);
			return true;
		}
	}
	
	public void playerHandSort(){
		p1_hand.sort();
	}
}