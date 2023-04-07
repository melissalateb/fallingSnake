package fallingSnake;

import java.awt.Color;

public class Obstacle {
	static int timer ; 
	private Color color ;
	public Obstacle() {
		this.timer = 0;
	}
	public Obstacle(Color color) {
		super();
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	} // sert a reconnaitre le type de l'obstacle 
	public Color reconnaitreCouleur(int x) {
		Color c = Color.black;
		if(x == 0) {
			c= Color.orange;
		}else if(x == 1) {
			c= Color.red ;
		}else if(x == 2) {
			c= Color.CYAN;
		}else if(x == 3) {
			c =Color.yellow;
		}
		return c ;
		
	}
	
}
