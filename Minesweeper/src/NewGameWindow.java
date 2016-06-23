import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

//This is the new game popup allowing to set new grid size and mine count
public class NewGameWindow extends JFrame implements ActionListener {
	
	public NewGameWindowPanel panel;
	public Grid grid;
	
	public NewGameWindow(Grid grid){
		
		super();
		this.grid = grid;
		this.setTitle("New Game");
		
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		
		
		this.panel = new NewGameWindowPanel();
		this.setContentPane(panel);
		
		this.setLayout(new GridLayout(3, 2));
		
		this.panel.button.addActionListener(this);
		this.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		
	}

}
