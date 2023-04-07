package fallingSnake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Serpent {
	private int serpentsize =3;
	private Color color  ;
	private boolean alive ; 
	public ArrayList<Coordinate> corps;
	public Serpent() {
		this.alive =true;
		corps = new ArrayList();
		this.color= Color.white;
		for (int i = 0; i < serpentsize; i++) {
			this.corps.add(new Coordinate((serpentsize-1-i)*25,0));
		}
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public int getSerpentsize() {
		return serpentsize;
	}
	public Color getColor() {
		return color;
	}
	public ArrayList<Coordinate> getcorps() {
		return corps;
	}
	public void setSerpentsize(int serpentsize) {
		this.serpentsize = serpentsize;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void setBody(ArrayList<Coordinate> corps) {
		this.corps = corps;
	}
	
	public void dessinerserpent(Graphics2D g) {
		if(Obstacle.timer!=0) g.setColor(Color.black);
		else g.setColor(Color.white);
		for (int i = 0; i < this.getSerpentsize(); i++) {
        	g.fillOval(this.corps.get(i).getX() ,this.corps.get(i).getY() , 25, 25);
		}
	}
	// deplacer le snake case par case
	// la facon de bouger snake est de modifier les coordonnees du corps du snake a chaque fois
	public int bougerSerpent(Case tabCase[][]) {
		if(Obstacle.timer!=0) Obstacle.timer--;
		for (int i = 0; i < this.corps.size(); i++) {
			
			if(this.corps.get(i).getY()==(tabCase.length-1)*tabCase.length) {
				return 1;
				//debut bouger serpent
			}if(this.corps.get(i).getY()%2==0) {			
				if(this.corps.get(i).getX()!=(tabCase.length-1)*tabCase.length) {			
					this.corps.get(i).setX(this.corps.get(i).getX()+tabCase.length);			
				}else if(this.corps.get(i).getX()==(tabCase.length-1)*tabCase.length){

					this.corps.get(i).setY(this.corps.get(i).getY()+tabCase.length);
					
				}
			}else if(this.corps.get(i).getY()%2==1) {				
				if(this.corps.get(i).getX()!=0) {					
					this.corps.get(i).setX(this.corps.get(i).getX()-tabCase.length);
				}else {					
					this.corps.get(i).setY(this.corps.get(i).getY()+tabCase.length);
				}//fin bouger snake
			}
			int x = this.corps.get(i).getX();
			int y =this.corps.get(i).getY();
			Color c ;
			// collision avec les obstacles
			// on recupere la coouleure de chaque case 
			if(tabCase[x/tabCase.length][y/tabCase.length].getObstacle()!=null) {
				c = tabCase[x/tabCase.length][y/tabCase.length].getObstacle().getColor();
				if(c==Color.red) {
					this.serpentsize++;
					Coordinate c1 = this.corps.get(this.corps.size()-1);
					//on ajoute le morceau a la fin de snake par rapport a la position du dernier morceau du snake ( 4 cas)
					if(c1.getY()%2==0) {
						if(c1.getX()==0) {
							this.corps.add(new Coordinate( c1.getX(),c1.getY()-tabCase.length));
						}else if(c1.getX()<=tabCase.length *tabCase.length) {
							this.corps.add(new Coordinate( c1.getX()-tabCase.length,c1.getY()));
						}
					}else if(c1.getY()%2!=0) {
						if(c1.getX()>=0) {
							this.corps.add(new Coordinate( c1.getX()+tabCase.length,c1.getY()));
						}else if(c1.getX()==tabCase.length *tabCase.length) {
							this.corps.add(new Coordinate( c1.getX(),c1.getY()-tabCase.length));
						}
					}
					tabCase[x/tabCase.length][y/tabCase.length].setObstacle(null);
				}else if(c==Color.orange) {
					// descendre le snake d'une case y donc 25 p
    				this.corps.get(i).setY(this.corps.get(i).getY()+25);
				}else if(c==Color.yellow) {
					// reinisiatise les obstacles dans la grille aléatoirement 
					Random random = new Random();
			    	int nRandom =100 ; 
			    	int temps = 0;
					for (int a = 0; a <tabCase.length ; a++) {
						for (int b = 0; b < tabCase.length; b++) {
							temps = random.nextInt(nRandom);
							if(temps>3||b ==tabCase.length-1 ||(b==0 && a<3) ) {
								tabCase[a][b] =new Case(null);
							}else {
								Obstacle ob = new Obstacle();
								ob.setColor(ob.reconnaitreCouleur(temps));
								tabCase[a][b]= new Case(ob);
							}
						}
						
					}
					tabCase[x/tabCase.length][y/tabCase.length].setObstacle(null);
				}else if(c==Color.cyan) {
					Obstacle.timer =25 ;
					tabCase[x/tabCase.length][y/tabCase.length].setObstacle(null);
				}
			}
			
		}
		return 0;
		
	}
 
 
}
