package fallingSnake;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Grille extends JPanel implements ActionListener,KeyListener{
	private Dimension dim;
	private final int tailleCase = 25 ;
	private final int nbCase =25 ;
	Serpent serpent ;
	Canon canon ; 
	BouletCanon boulets ; 
	private Timer tempsBouger;
	public Case [][] tabCase;
	public Grille () {
		dim =new Dimension (625,625);
		setBackground(Color.pink);
		serpent = new Serpent();
		canon = new Canon(12*tailleCase,(tailleCase-1)*tailleCase);
		boulets = new BouletCanon();
		tempsBouger = new Timer(100, this);
		tempsBouger.start();
		addKeyListener(this);
		 setFocusable(true);
	     setFocusTraversalKeysEnabled(false);
	}
	// Getter et Setter
	public Case[][] getTabCase() {
		return tabCase;
	}
	public void setTabCase(Case[][] tabCase) {
		this.tabCase = tabCase;
	}
	
	@Override
    public void addNotify() {
        super.addNotify();
        initialisationGrille();
    }
//on initialise la grille avec un nombre random pour creer des obstacles aléatoirement
	public void initialisationGrille() {
		tabCase = new Case[nbCase][nbCase];
		Random random = new Random();
    	int nRandom =100 ; 
    	int temps = 0;
		for (int y = 0; y <nbCase ; y++) {
			for (int x = 0; x < nbCase; x++) {
				temps = random.nextInt(nRandom);
				if(temps>3||y ==nbCase-1 ||(y==0 && x<4) ) {
					tabCase[x][y] =new Case(null);
				}else {
					Obstacle ob = new Obstacle();
					ob.setColor(ob.reconnaitreCouleur(temps));
					tabCase[x][y]= new Case(ob);
				}
			}
			
		}
	}
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dessiner(g);
    }
	 private void dessiner(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setColor(Color.pink);
	        g2d.fillRect(0, 0, dim.width, dim.height);
	        dessinerGrille(g2d);
	        
	        canon.dessinerCanon(g2d);
	        boulets.dessinerBoulets(g2d);
	        boulets.bougerBoulets(this.tabCase ,serpent );
	        
	        serpent.dessinerserpent(g2d);
	        if(serpent.bougerSerpent(this.tabCase)==1) {
	        	tempsBouger.stop();
	        	System.err.println(" \n Vous avez perdu :(");
	        }
	        if(!serpent.isAlive()) {
	        	tempsBouger.stop();
	        	System.err.println(" \n Felicitation  vous avez gagnée la partie !!!");
	        }
	        Toolkit.getDefaultToolkit().sync();
	        g2d.dispose();
	}
	public void  dessinerGrille(Graphics2D g){
		 for (int y = 0; y < nbCase; y++) {
			 for (int x = 0; x < tabCase.length; x++) {
					if(tabCase[x][y].getObstacle()!=null) {
						// dessiner la grille qui contient  les obstacles avec les courbures
						g.setColor(tabCase[x][y].getObstacle().getColor());
						g.fillRoundRect(x*tabCase.length+5, y*tabCase.length+5, 15, 15, 15, 15);
					}
				}
		} 
	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		canon.bougerCanon();
		 repaint();
	}
	@Override
	//on utilise l'interface key listener pour bouger le canon 
	public void keyPressed(KeyEvent e) {		
		int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                canon.setDx(-1) ;
            } else if (key == KeyEvent.VK_RIGHT) {
            	canon.setDx(1) ;
            }else if(key ==KeyEvent.VK_SPACE) {
            	boulets.tirerBoulet(canon.getX(), canon.getY());
            
        	}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		canon.setDx(0);
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	

}
