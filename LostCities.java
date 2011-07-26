

import it.stessaro.lostcities.core.GamePref;
import it.stessaro.lostcities.core.Model;
import it.stessaro.lostcities.core.View;

import javax.swing.JFrame;


public class LostCities extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private static final int  WIDTH = GamePref.get_Width();
	private static final int HEIGHT = GamePref.get_Height();
	private Model game;
	private View view;
	public static void main(String [] args) {
		LostCities main = new LostCities();
		main.setVisible(true);
		
	}
	public LostCities(){
		this.setLocation(400, 10);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
			
		game = new Model();
		view = new View(game);
		this.setTitle("Lost Cities");
		//this.addComponentListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(view);
		//this.pack();
	    //this.setVisible(true);
	}

}
