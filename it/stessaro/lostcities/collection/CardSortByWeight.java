package it.stessaro.lostcities.collection;

import java.util.Comparator;

public class CardSortByWeight implements Comparator<Card> {

	@Override
	public int compare(Card card1, Card card2) {
		int w1 = card1.getWeight();
		int w2 = card2.getWeight();
		if (w1>w2) {
			return 1;
		}else if (w1<w2) {
			return -1;
		}else{
			return 0;
		}
	}
}
