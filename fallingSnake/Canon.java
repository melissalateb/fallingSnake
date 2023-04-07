package fallingSnake;

import java.awt.Color;
import java.awt.Graphics2D;

public class Canon {
	private int x,y,Dx;
	private boolean alive ;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDx() {
		return Dx;
	}

	public void setDx(int dx) {
		Dx = dx;
	}
	
	public Canon(int x, int y) {
		super();
		this.alive=true;
		this.x = x;
		this.y = y;
		
	}
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void dessinerCanon(Graphics2D g) {
		g.setColor(Color.yellow);
		g.fillRect(this.x, this.y, 25, 25);
	}
	public void bougerCanon() {
		if(this.x!=0&& Dx == -1) {
			this.x = x-25;
		}else if (this.x!=24*25&& Dx == 1) {
			this.x = x+25;
		}
	}
}
