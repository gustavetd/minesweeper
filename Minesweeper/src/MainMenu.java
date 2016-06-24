import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Main menu with the rules and a new game button
public class MainMenu extends JFrame implements ActionListener {
	
	private Grid grid;
	private MainMenuNewGame button;
	private JPanel pan;
	
	public MainMenu(Grid grid){
		this.grid = grid;
		this.setTitle("Main Menu");
		this.setSize(200, 150);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pan = new JPanel();
		this.setContentPane(pan);
		MainMenuNewGame button = new MainMenuNewGame();
		button.addActionListener(this);
		pan.add(button);
		NewGameWindow newGame = new NewGameWindow(grid);
		MainWindow mainWindow = new MainWindow(grid, newGame.panel, newGame);
		mainWindow.initWindow();
		
		this.pan.add(new JLabel("Rules:"));
		this.pan.add(new JLabel("Classic minesweeper rules."));
		this.pan.add(new JLabel("Click to reveal a cell,"));
		this.pan.add(new JLabel("CTRL-click to flag it."));
		
		this.setLayout(new GridLayout(5, 1));
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		NewGameWindow newGame = new NewGameWindow(this.grid);
		MainWindow mainWindow = new MainWindow(grid, newGame.panel, newGame);
		
		
	}


}
