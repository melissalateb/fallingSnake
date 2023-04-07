package fallingSnake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BouletCanon {
	private ArrayList<Coordinate> boulets;

	public BouletCanon() {
		this.boulets = new ArrayList();
	}
	public void tirerBoulet(int x ,int y) {
		this.boulets.add(new Coordinate(x,y));
	}
	public void dessinerBoulets(Graphics2D g) {
		g.setColor(Color.BLACK);
		for (int i = 0; i < this.boulets.size(); i++) {
        	g.fillOval(this.boulets.get(i).getX()+8 ,this.boulets.get(i).getY() ,10 , 15);
		}
	}
	// 1e: compare les positions de snake avec le projectile 
	public boolean trouverSerpent(Serpent serpent,int x ,int y) {
		boolean resultat =false;
		for (int i = 0; i <serpent.corps.size() ; i++) {
			if(serpent.corps.get(i).getX()==x &&serpent.corps.get(i).getY()==y) {
				resultat= true;
				break;
			}
		}
		return resultat;
	}
	// 2e: bouge le boulet et agit en fonction de ce qu'il trouve dans la case
	public void  bougerBoulets(Case tabCase[][],Serpent serpent) {
		for (int i = 0; i < this.boulets.size(); i++) {
			if(this.boulets.get(i).getY()!=0) {
				this.boulets.get(i).setY(this.boulets.get(i).getY()-tabCase.length);
				// on test s'il ya collision avec un obstacle 
				if(tabCase[this.boulets.get(i).getX()/tabCase.length][this.boulets.get(i).getY()/tabCase.length].getObstacle()!=null) {
					tabCase[this.boulets.get(i).getX()/tabCase.length][this.boulets.get(i).getY()/tabCase.length].setObstacle(null);
					this.boulets.remove(i);
					// test de collision avec le serpent
				}else if(trouverSerpent(serpent,this.boulets.get(i).getX(),this.boulets.get(i).getY())&&Obstacle.timer==0){
					serpent.setSerpentsize(serpent.getSerpentsize()-1);
					serpent.corps.remove(serpent.corps.size()-1);
					this.boulets.remove(i);
					if(serpent.getSerpentsize()==0) {
						serpent.setAlive(false);
					}
				}
			}else { // le boulet est arrivee jusqu'a la fin de la grille y= 0
				this.boulets.remove(i);
			}
		}
		
	}
}