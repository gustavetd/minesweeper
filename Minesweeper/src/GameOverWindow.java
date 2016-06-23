import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverWindow extends JFrame {
	private JPanel pan;
	public boolean gameWon;
	
	public GameOverWindow(boolean gameWon, double playTime){
		super("Game Over");
		this.gameWon = gameWon;
		this.setSize(300, 100);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.pan = new JPanel();
		this.setContentPane(this.pan);
		this.pan.add(new JLabel("Game over"));
		JLabel label = null;
		
		if (gameWon){
			label = new JLabel("You Won!");
		}
		else label = new JLabel("You lost...");
		
		this.pan.add(label);
		if (gameWon) this.pan.add(new JLabel("You solved this puzzle in " + (int)playTime + " seconds"));
		this.setLayout(new GridLayout(3, 1));
		this.setVisible(true);
	}
}
