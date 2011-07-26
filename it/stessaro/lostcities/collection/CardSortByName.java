package it.stessaro.lostcities.collection;

import java.util.Comparator;

public class CardSortByName implements Comparator<Card>{

    public int compare(Card card1, Card card2) {
        return card1.getColorStr().compareTo(card2.getColorStr());
    }
}
