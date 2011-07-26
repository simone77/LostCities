package it.stessaro.lostcities.core;
import it.stessaro.lostcities.collection.Card;
import it.stessaro.lostcities.collection.Deck;
import it.stessaro.lostcities.collection.Discards;
import it.stessaro.lostcities.collection.Expedition;
import it.stessaro.lostcities.collection.Expeditions;
import it.stessaro.lostcities.collection.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;


public class Model extends Observable{
	
    private Deck mazzo;
	private Hand player_hand, pc_hand;
	private Expeditions playerExpeditions;
	private Expeditions pcExpeditions;
	private Discards discardPile;
	private boolean playerTurn = true;
	public boolean gameRunning = true;
	//

	public Model(){
		mazzo = new Deck();
		player_hand = mazzo.getPlayerHand();
		pc_hand = mazzo.getHandPC();
		//
		discardPile = new Discards();
		//
		playerExpeditions = new Expeditions();
		pcExpeditions = new Expeditions();
		//
	}
	
	public Discards getDiscardPile() {
		return discardPile;
	}

	public boolean isPlayerTurn() {
		return playerTurn;
	}

	public void doPCMove() {
		//simple pcMove
		if (gameRunning) {
			Hand valid = new Hand();
			
			for (Card card : pc_hand) {
				if (pcExpeditions.isCardPlayable(card)) {
					valid.add(card);
				}
			}
			for (Card card : valid) {
				int col = card.getInt();
				card.setWeight( pcExpeditions.getPoint(col) );
				card.addWeight( valid.getPoint(col) );
			}
			
			
			
			//sort the card for weight
			valid.sortbyWeight();
			if (valid.size() > 0) {
				
				Card pcCard = valid.peek();

				if (pcExpeditions.addCard(pcCard)) {//TODO expedition playcard
					pc_hand.remove(pcCard);
					this.setChanged();
					this.notifyObservers();
				}
			}else{
				Random rand = new Random();
				int i = rand.nextInt(pc_hand.size());
				Card pcCard = pc_hand.get(i);
				this.addCardToDiscard(pcCard);
				pc_hand.remove(pcCard);
				this.setChanged();
				this.notifyObservers();
			}
			this.pcDrawCard();
			valid.print();
		}
	}
	
	public Hand getPlayerHand(){
		return player_hand;
	}
	
	public Deck getDeck(){
		return mazzo;
	}
	
	public String getDeckSize(){
		return mazzo.sizeStr();
	}

	public void playerDrawCard(){
		mazzo.playerDrawCard();
		if (mazzo.size() == 0) {
			gameRunning = false;
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	public void pcDrawCard(){
		mazzo.pcDrawCard();
		if (mazzo.size() == 0) {
			gameRunning = false;
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	/**/
	public Expeditions getExpeditions(){
		return playerExpeditions;
	}
	public Expeditions getPcExpeditions(){
		return pcExpeditions;
	}
	//TODO change type?
	/*Add a card to player expedition*/
	public void addCardToExpedition(Card card){
		playerExpeditions.addCard(card);
	}
	/*Add a card to pc expedition*/
	public void addCardToPcExpedition(Card card){
		pcExpeditions.addCard(card);
	}
	/*Add a card to discard*/
	public void addCardToDiscard(Card card){
		discardPile.addCard(card);
	}

	public Card drawCard(String colore){
		return discardPile.drawCard(colore);
	}
	/**
	 * @return the pc_hand
	 */
	public Hand getPc_hand() {
		return pc_hand;
	}
}
