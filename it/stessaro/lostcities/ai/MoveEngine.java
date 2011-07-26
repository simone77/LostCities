package it.stessaro.lostcities.ai;
import java.util.Observable;
import java.util.Observer;

import it.stessaro.lostcities.collection.*;
import it.stessaro.lostcities.core.Model;

public class MoveEngine implements Observer{
	
	private Model game;
	public MoveEngine() {

		game = new Model();
	}
	public void findValidMove(Hand hand ){
		//just for test random card
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
