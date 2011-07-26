package it.stessaro.lostcities.core;
import it.stessaro.lostcities.collection.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class View extends JPanel implements MouseListener, MouseMotionListener, Observer{
	
	private static final long serialVersionUID = 1L;
	private static final int  CARD_W = GamePref.get_Card_Width();
	private static final int CARD_H = GamePref.get_Card_Height();
	private static final int START_X  = GamePref.get_Start_X();
	private static final int SPACER = GamePref.get_Spacer();
	/**/
	private static final int RED = GamePref.get_RedCard();
	private static final int WHITE = GamePref.get_WhiteCard();
	private static final int BLUE = GamePref.get_BlueCard();
	private static final int GREEN = GamePref.get_GreenCard();
	private static final int YELLOW = GamePref.get_YellowCard();
	/**/
	private Model game;
	private boolean dragging;
	private boolean isPlayerTurn;
	boolean[] cardDragged;
	private Hand player_hand;
	private Expeditions playerExpeditions, pcExpeditions;
	private Discards cardPile;
	
	int offsetX, offsetY;
	int currentCardPos;
	//card position
	int num_x[] = {50, 70, 90, 110, 130, 150, 170, 190};
	int num_y[] = {600, 600, 600, 600, 600, 600, 600, 600};
	//end

	public View(Model game){
		this.game = game;
		this.player_hand = game.getPlayerHand();
		this.playerExpeditions = game.getExpeditions();
		this.pcExpeditions = game.getPcExpeditions();
		this.cardPile = game.getDiscardPile();
		this.isPlayerTurn = true;
		this.setBounds(0, 0, 600, 700);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.game.addObserver(this);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int count = 0;
		this.paintPcPiles(g);
		this.paintCenter(g);
		this.paintPlayerPiles(g);

		for (Card card : player_hand) {
			g.setColor(card.getColor());
			g.fillRect(num_x[count], num_y[count], CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(num_x[count], num_y[count], CARD_W, CARD_H);
			g.drawString(card.getNumberString() , num_x[count]+3, num_y[count]+13);
			count++;
		}
	}

	public void paintPlayerPiles(Graphics g){
		int y = 340;
		int h_arr[] = {0, 0, 0, 0, 0};
		//red
		g.setColor(Color.black);
		g.drawString(playerExpeditions.get(RED).exp_valueStr() ,START_X + 16, y - 5);
		g.setColor(Color.red);
		g.drawRect(START_X, y, CARD_W, CARD_H);
		for (Card card : playerExpeditions.get(RED)) {
			g.setColor(Color.red);
			g.fillRect(START_X, y + h_arr[0], CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X, y + h_arr[0], CARD_W, CARD_H);
			g.drawString(card.getNumberString() , START_X + 3, y + h_arr[0] + 13);
			h_arr[0] = h_arr[0] + 15;
		}
		//white
		g.setColor(Color.black);
		g.drawString(playerExpeditions.get(WHITE).exp_valueStr() ,START_X + CARD_W + SPACER + 16, y - 5);
		g.setColor(Color.white);
		g.drawRect(START_X + CARD_W + SPACER, y, CARD_W, CARD_H);
		for (Card card : playerExpeditions.get(WHITE)) {
			g.setColor(Color.white);
			g.fillRect(START_X + CARD_W + SPACER, y + h_arr[1], CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X + CARD_W + SPACER, y + h_arr[1], CARD_W, CARD_H);
			g.drawString(card.getNumberString(), START_X + CARD_W + SPACER + 3, y + h_arr[1] + 13);
			h_arr[1] = h_arr[1] + 15;
		}
		//green
		g.setColor(Color.black);
		g.drawString(playerExpeditions.get(GREEN).exp_valueStr(), START_X + (CARD_W + SPACER)*2 + 16, y - 5);
		g.setColor(Color.green);
		g.drawRect(START_X + (CARD_W+ SPACER)*2, y, CARD_W, CARD_H);
		for (Card card : playerExpeditions.get(GREEN)) {
			g.setColor(Color.green);
			g.fillRect(START_X + (CARD_W + SPACER)*2, y + h_arr[2], CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X + (CARD_W + SPACER)*2, y + h_arr[2], CARD_W, CARD_H);
			g.drawString(card.getNumberString(), START_X + (CARD_W + SPACER)*2 + 3, y + h_arr[2] + 13);
			h_arr[2] = h_arr[2] + 15;
		}

		//blu
		g.setColor(Color.black);
		g.drawString(playerExpeditions.get(BLUE).exp_valueStr(), START_X + (CARD_W + SPACER)*3 + 16, y - 5);
		g.setColor(Color.cyan);
		g.drawRect(START_X + (CARD_W + SPACER)*3, y, CARD_W, CARD_H);
		for (Card card : playerExpeditions.get(BLUE)) {
			g.setColor(Color.cyan);
			g.fillRect(START_X + (CARD_W + SPACER)*3, y + h_arr[3], CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X + (CARD_W + SPACER)*3, y + h_arr[3], CARD_W, CARD_H);
			g.drawString(card.getNumberString(), START_X + (CARD_W + SPACER)*3 + 3, y + h_arr[3] + 13);
			h_arr[3] = h_arr[3] + 15;
		}

		//yellow
		g.setColor(Color.black);
		g.drawString(playerExpeditions.get(YELLOW).exp_valueStr(), START_X + (CARD_W + SPACER)*4 + 16, y - 5);
		g.setColor(Color.yellow);
		g.drawRect(START_X + (CARD_W + SPACER)*4, y, CARD_W, CARD_H);
		for (Card card : playerExpeditions.get(YELLOW)) {
			g.setColor(Color.yellow);
			g.fillRect(START_X + (CARD_W + SPACER)*4, y + h_arr[4], CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X + (CARD_W + SPACER)*4, y + h_arr[4], CARD_W, CARD_H);
			g.drawString(card.getNumberString() , START_X + (CARD_W+ SPACER)*4 + 3, y + h_arr[4] + 13);
			h_arr[4] = h_arr[4] + 15;
		}
	}
	
	public void paintCenter(Graphics g){
		int h = 250;
		//paint background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 240, 600, CARD_H + 20);
		//red
		g.setColor(Color.red);
		g.drawRect(START_X, h, CARD_W, CARD_H);
		for (Card card : cardPile.get(RED)) {
			g.setColor(Color.red);
			g.fillRect(START_X, h, CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X, h, CARD_W, CARD_H);
			g.drawString(card.getNumberString(), START_X + 3, h + 13);
		}
		//white
		g.setColor(Color.white);
		g.drawRect(START_X + CARD_W + SPACER, h, CARD_W, CARD_H);
		for (Card card : cardPile.get(WHITE)) {
			g.setColor(Color.white);
			g.fillRect(START_X + CARD_W + SPACER, h, CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X + CARD_W + SPACER, h, CARD_W, CARD_H);
			g.drawString(card.getNumberString(), START_X + CARD_W + SPACER + 3, h + 13);
		}
		//green
		g.setColor(Color.green);
		g.drawRect(START_X + (CARD_W + SPACER)*2, h, CARD_W, CARD_H);
		for (Card card : cardPile.get(GREEN)) {
			g.setColor(Color.green);
			g.fillRect(START_X + (CARD_W + SPACER)*2, h, CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X + (CARD_W + SPACER)*2, h, CARD_W, CARD_H);
			g.drawString(card.getNumberString(), START_X + (CARD_W + SPACER)*2 + 3, h + 13);
		}
		//blue
		g.setColor(Color.cyan);
		g.drawRect(START_X + (CARD_W + SPACER)*3, h, CARD_W, CARD_H);
		for (Card card : cardPile.get(BLUE)) {
			g.setColor(Color.cyan);
			g.fillRect(START_X + (CARD_W + SPACER)*3, h, CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X + (CARD_W + SPACER)*3, h, CARD_W, CARD_H);
			g.drawString(card.getNumberString(), START_X + (CARD_W + SPACER)*3 + 3, h + 13);
		}
		//yellow
		g.setColor(Color.yellow);
		g.drawRect(START_X + (CARD_W + SPACER)*4, h, CARD_W, CARD_H);
		for (Card card : cardPile.get(YELLOW)) {
			g.setColor(Color.yellow);
			g.fillRect(START_X + (CARD_W + SPACER)*4, h, CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X + (CARD_W + SPACER)*4, h, CARD_W, CARD_H);
			g.drawString(card.getNumberString(), START_X + (CARD_W + SPACER)*4 + 3, h + 13);
		}
		//
		g.setColor(Color.ORANGE);
		g.fillRect(START_X + (CARD_W + SPACER)*5, h, CARD_W, CARD_H);
		g.drawString(this.game.getDeckSize() ,START_X + (CARD_W + SPACER)*5 + CARD_W + 10, h +10);
	}
	
	public void paintPcPiles(Graphics g){
		int y = 10;
		int h_arr[] = {0, 0, 0, 0, 0};
		g.setColor(Color.red);
		g.drawRect(START_X, y, CARD_W, CARD_H);
		for (Card card : pcExpeditions.get(RED)) {
			g.setColor(Color.red);
			g.fillRect(START_X, y + h_arr[0], CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X, y + h_arr[0], CARD_W, CARD_H);
			g.drawString(card.getNumberString() , START_X + 3, y + h_arr[0] + 13);
			h_arr[0] = h_arr[0] + 15;
		}
		//white
		g.setColor(Color.white);
		g.drawRect(START_X + CARD_W + SPACER, y, CARD_W, CARD_H);
		for (Card card : pcExpeditions.get(WHITE)) {
			g.setColor(Color.white);
			g.fillRect(START_X + CARD_W + SPACER, y + h_arr[1], CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X + CARD_W + SPACER, y + h_arr[1], CARD_W, CARD_H);
			g.drawString(card.getNumberString(), START_X + CARD_W + SPACER + 3, y + h_arr[1] + 13);
			h_arr[1] = h_arr[1] + 15;
		}
		//green
		g.setColor(Color.green);
		g.drawRect(START_X + (CARD_W+ SPACER)*2, y, CARD_W, CARD_H);
		for (Card card : pcExpeditions.get(GREEN)) {
			g.setColor(Color.green);
			g.fillRect(START_X + (CARD_W + SPACER)*2, y + h_arr[2], CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X + (CARD_W + SPACER)*2, y + h_arr[2], CARD_W, CARD_H);
			g.drawString(card.getNumberString(), START_X + (CARD_W + SPACER)*2 + 3, y + h_arr[2] + 13);
			h_arr[2] = h_arr[2] + 15;
		}

		//blu
		g.setColor(Color.cyan);
		g.drawRect(START_X + (CARD_W + SPACER)*3, y, CARD_W, CARD_H);
		for (Card card : pcExpeditions.get(BLUE)) {
			g.setColor(Color.cyan);
			g.fillRect(START_X + (CARD_W + SPACER)*3, y + h_arr[3], CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X + (CARD_W + SPACER)*3, y + h_arr[3], CARD_W, CARD_H);
			g.drawString(card.getNumberString(), START_X + (CARD_W + SPACER)*3 + 3, y + h_arr[3] + 13);
			h_arr[3] = h_arr[3] + 15;
		}

		//yellow
		g.setColor(Color.yellow);
		g.drawRect(START_X + (CARD_W + SPACER)*4, y, CARD_W, CARD_H);
		for (Card card : pcExpeditions.get(YELLOW)) {
			g.setColor(Color.yellow);
			g.fillRect(START_X + (CARD_W + SPACER)*4, y + h_arr[4], CARD_W, CARD_H);
			g.setColor(Color.black);
			g.drawRect(START_X + (CARD_W + SPACER)*4, y + h_arr[4], CARD_W, CARD_H);
			g.drawString(card.getNumberString() , START_X + (CARD_W+ SPACER)*4 + 3, y + h_arr[4] + 13);
			h_arr[4] = h_arr[4] + 15;
		}
	}

	public void update(Observable arg0, Object arg1) {
		Log.print("update");
		if (game.gameRunning) {	
			this.isPlayerTurn = true;
			this.repaint();
	
		}else{
			dragging = false;
			this.isPlayerTurn = false;
			String message = "PC SCORE: " + pcExpeditions.getScore() + "\n" +
			"PLAYER SCORE: " + playerExpeditions.getScore();
		    JOptionPane pane = new JOptionPane(message);
		    JDialog dialog = pane.createDialog(SwingUtilities.getWindowAncestor(this), "Result");
		    dialog.show();//TODO
			this.repaint();
		}
	}

	public void mouseClicked(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();
		int h = 250;
		boolean played = false;
		if (evt.getClickCount() == 2) {
			Log.print( "double click!");
			//
			if(this.player_hand.size() == 7 && this.isPlayerTurn){
				try {
					if (x > START_X && x < START_X + CARD_W && y > h && y < h + CARD_H ) {
						Log.print("pesca carta rossa");
						Card redcard = game.drawCard("r");
						player_hand.add(redcard);
						played = true;
					}else if (x > START_X + CARD_W + SPACER && x < START_X + CARD_W * 2 + SPACER && y > h && y < h + CARD_H ) {
						Log.print("pesca carta bianca");
						Card whitecard = game.drawCard("w");
						player_hand.add(whitecard);
						played = true;
					}else if (x > START_X + (CARD_W + SPACER)*2 && x < START_X + (CARD_W + SPACER)*2 + CARD_W && y > h && y < h + CARD_H ) {
						System.out.println("pesca carta verde");
						Card greencard = game.drawCard("g");
						player_hand.add(greencard);
						played = true;
					}else if (x > START_X + (CARD_W + SPACER)*3 && x < START_X + (CARD_W + SPACER)*3 + CARD_W && y > h && y < h + CARD_H ) {
						Log.print("pesca carta blue");
						Card bluecard = game.drawCard("b");
						player_hand.add(bluecard);
						played = true;
					}else if (x > START_X + (CARD_W + SPACER)*4 && x < START_X + (CARD_W + SPACER)*4 + CARD_W && y > h && y < h + CARD_H ) {
						Log.print("pesca carta gialla");
						Card yellowcard = game.drawCard("y");
						player_hand.add(yellowcard);
						played = true;
					}else if (x > START_X + (CARD_W + SPACER)*5 && x < START_X + (CARD_W + SPACER)*5 + CARD_W && y > h && y < h + CARD_H ) {
						Log.print("pesca carta dal mazzo");
						this.game.playerDrawCard();
						played = true;
					}
					
					if (played) {
						this.isPlayerTurn = false;
						//this.mazzo.playerHandSort();
						game.doPCMove();
						System.out.println("valore: " + isPlayerTurn);
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	public void mousePressed(MouseEvent evt) {
		if (dragging || player_hand.size() < 8 || !isPlayerTurn) {
			return;
		}
		int x = evt.getX();
		int y = evt.getY();
		int gap = 20;

		cardDragged = new boolean[8];
		for (int i = 0; i < 8; i++) {
			cardDragged[i] = false;
		}

		if (x > num_x[0] && x < num_x[0] + gap && y > num_y[0] && y < num_y[0] + CARD_H) {
			dragging = true;
			cardDragged[0] = true;
			currentCardPos = 0;
			offsetX = x - num_x[0];
			offsetY = y - num_y[0];

		}else if(x > num_x[1] && x < num_x[1] + gap && y > num_y[1] && y < num_y[1] + CARD_H) {
			dragging = true;
			cardDragged[1] = true;
			currentCardPos = 1;
			offsetX = x - num_x[1]; 
			offsetY = y - num_y[1];

		}else if(x > num_x[2] && x < num_x[2] + gap && y > num_y[2] && y < num_y[2] + CARD_H) {
			dragging = true;
			cardDragged[2] = true;
			currentCardPos = 2;
			offsetX = x - num_x[2];  
			offsetY = y - num_y[2];

		}else if(x > num_x[3] && x < num_x[3] + gap && y > num_y[3] && y < num_y[3] + CARD_H) {
			dragging = true;
			cardDragged[3] = true;
			currentCardPos = 3;
			offsetX = x - num_x[3];  
			offsetY = y - num_y[3];

		}else if(x > num_x[4] && x < num_x[4] + gap && y > num_y[4] && y < num_y[4] + CARD_H) {
			dragging = true;
			cardDragged[4] = true;
			currentCardPos = 4;
			offsetX = x - num_x[4];  
			offsetY = y - num_y[4];

		}else if(x > num_x[5] && x < num_x[5] + gap && y > num_y[5] && y < num_y[5] + CARD_H) {
			dragging = true;
			cardDragged[5] = true;
			currentCardPos = 5;
			offsetX = x - num_x[5]; 
			offsetY = y - num_y[5];

		}else if(x > num_x[6] && x < num_x[6] + gap && y > num_y[6] && y < num_y[6] + CARD_H) {
			dragging = true;
			cardDragged[6] = true;
			currentCardPos = 6;
			offsetX = x - num_x[6];  
			offsetY = y - num_y[6];

		}else if(x > num_x[7] && x < num_x[7] + CARD_W && y > num_y[7] && y < num_y[7] + CARD_H) {
			dragging = true;
			cardDragged[7] = true;
			currentCardPos = 7;
			offsetX = x - num_x[7]; 
			offsetY = y - num_y[7];
		}
	}

	public void mouseReleased(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();
		Card currentCard;
		if (dragging) {
			try {
				currentCard = player_hand.get(currentCardPos);

				if (x > START_X && x < START_X + CARD_W && y > 340 && y < 400 + playerExpeditions.get(RED).size() * 15) {
					if (currentCard.getColorStr().equals("r") && currentCard.getNumber() >= playerExpeditions.get(RED).lastNumber()) {
						Log.print(currentCard + " dentro la spedizione rossa");
						game.addCardToExpedition(currentCard);
						player_hand.remove(currentCardPos);	
					}
				}else if (x > START_X + CARD_W + SPACER && x < START_X + CARD_W * 2 + SPACER && y > 340 && y < 400 + playerExpeditions.get(WHITE).size() * 15){
					if (currentCard.getColorStr().equals("w") && currentCard.getNumber() >= playerExpeditions.get(WHITE).lastNumber()) {
						Log.print(currentCard + " dentro la spedizione bianca");
						game.addCardToExpedition(currentCard);
						player_hand.remove(currentCardPos);	
					}
				}else if (x > START_X + (CARD_W + SPACER)*2 && x < START_X + (CARD_W + SPACER)*2 + CARD_W && y > 340 && y < 400 + playerExpeditions.get(2).size() * 15){
					if (currentCard.getColorStr().equals("g") && currentCard.getNumber() >= playerExpeditions.get(2).lastNumber()) {
						Log.print(currentCard + " dentro la spedizione verde");
						game.addCardToExpedition(currentCard);
						player_hand.remove(currentCardPos);	
					}
				}else if (x > START_X + (CARD_W + SPACER)*3  && x < START_X + (CARD_W + SPACER)*3 + CARD_W && y > 340 && y < 400 + playerExpeditions.get(3).size() * 15){
					if (currentCard.getColorStr().equals("b") && currentCard.getNumber() >= playerExpeditions.get(3).lastNumber()) {
						Log.print(currentCard + " dentro la spedizione Blu");
						game.addCardToExpedition(currentCard);
						player_hand.remove(currentCardPos);	
					}
				}else if (x > START_X + (CARD_W + SPACER)*4 && x < START_X + (CARD_W + SPACER)*4 + CARD_W && y > 340 && y < 400 + playerExpeditions.get(4).size() * 15){
					if (currentCard.getColorStr().equals("y") && currentCard.getNumber() >= playerExpeditions.get(4).lastNumber()) {
						Log.print(currentCard + " dentro la spedizione Gialla");
						game.addCardToExpedition(currentCard);
						player_hand.remove(currentCardPos);	
					}
				/**/
				}else if (x > START_X && x < START_X + CARD_W && y > 250 && y < 310) {
					if (currentCard.getColorStr().equals("r")) {
						Log.print(currentCard + " dentro il pozzo rosso");
						game.addCardToDiscard(currentCard);
						player_hand.remove(currentCardPos);	
					}

				}else if (x > START_X + CARD_W + SPACER && x < START_X + CARD_W * 2 + SPACER && y > 250 && y < 310){
					if (currentCard.getColorStr().equals("w")) {
						Log.print(currentCard + " dentro il pozzo bianco");
						game.addCardToDiscard(currentCard);
						player_hand.remove(currentCardPos);	
					}
				}else if (x > START_X + (CARD_W + SPACER)*2 && x < START_X + (CARD_W + SPACER)*2 + CARD_W && y > 250 && y < 310){
					if (currentCard.getColorStr().equals("g")) {
						Log.print(currentCard + " dentro il pozzo verde");
						game.addCardToDiscard(currentCard);
						player_hand.remove(currentCardPos);	
					}
				}else if (x > START_X + (CARD_W + SPACER)*3 && x < START_X + (CARD_W + SPACER)*3 + CARD_W && y > 250 && y < 310){
					if (currentCard.getColorStr().equals("b")) {
						Log.print(currentCard + " dentro il pozzo blu");
						game.addCardToDiscard(currentCard);
						player_hand.remove(currentCardPos);	
					}
				}else if (x > START_X + (CARD_W + SPACER)*4 && x < START_X + (CARD_W + SPACER)*4 + CARD_W && y > 250 && y < 310){
					if (currentCard.getColorStr().equals("y")) {
						Log.print(currentCard + " dentro il pozzo giallo");
						game.addCardToDiscard(currentCard);
						player_hand.remove(currentCardPos);	
					}
				}
				/**/
			} catch (Exception e) {
				Log.print(e.toString());
			}
		}

		dragging = false;
		//reset values
		int reset_x[] = {50, 70, 90, 110, 130, 150, 170, 190};
		int reset_y[] = {600, 600, 600, 600, 600, 600, 600, 600};
		System.arraycopy(reset_x, 0, num_x, 0, 8);
		System.arraycopy(reset_y, 0, num_y, 0, 8);
		//end reset
		this.repaint();

	}

	public void mouseDragged(MouseEvent evt) {
		if (dragging == false)  
			return;
		int x = evt.getX();
		int y = evt.getY();
		num_x[currentCardPos] = x - offsetX;
		num_y[currentCardPos] = y - offsetY;
		this.repaint();

	}

	/*Unused methods*/
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}

}