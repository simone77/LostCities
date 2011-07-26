package it.stessaro.lostcities.collection;


public class Expedition extends ColorPile{
	
	private static final long serialVersionUID = 1L;
	
	public Expedition(String color) {
		super(color);
	}
	public Expedition(int color) {
		super(color);
	}
	
	public int exp_value(){
		int value;
		if (this.size() == 0) {
			return 0;
		}else{
			value = -20;
			int moltiply = 1;
			for (Card card : this) {
				if (card.getNumber() == 1 ) {
					moltiply++;
				}else {
					value = value + card.getNumber();
				}
				
			}
			return value * moltiply;
		}
	}
	
	public String exp_valueStr(){
		return Integer.toString(this.exp_value());
	}
}
