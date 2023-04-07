package fallingSnake;

import java.awt.EventQueue;

import javax.swing.JFrame;

import fallingSnake.Main;
import fallingSnake.Grille;

public class Main extends JFrame{
	private static Main fenetre;
    // créer une fenetre du jeu
	public Main() {
		add(new Grille());
        setTitle("Falling Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(625, 650);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            fenetre = new Main();
            fenetre.setVisible(true);
        });
    }
	

}
