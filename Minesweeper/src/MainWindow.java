import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

//Main window, here is part of the controller and the view
public class MainWindow extends JFrame implements ActionListener {

	public Grid grid;
	public MainWindowPanel panel;
	public NewGameWindowPanel ngPanel;
	private NewGameWindow ngWindow;
	private boolean ngValidate;

	public MainWindow(Grid grid, NewGameWindowPanel ngPanel, NewGameWindow ngWindow){
		super();
		this.grid = grid;
		this.ngWindow = ngWindow;
		this.ngPanel = ngPanel;
		this.ngPanel.button.addActionListener(this);
		this.ngValidate = true;


		//window formating
		this.setTitle("Gustave\'s Minesweeper");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//adding the panel
		this.panel = new MainWindowPanel(grid);


		//setting visible 
		this.setVisible(false);
	}

	public void initWindow(){
		//adding the panel
		this.panel = new MainWindowPanel(grid);
		this.setContentPane(this.panel);

		//defining the layout of the main window according to grid size
		this.setLayout(new GridLayout(this.grid.size, this.grid.size));

		//listening to the cells
		for(int x = 0; x<this.grid.size ; x++){
			for(int y = 0; y<this.grid.size; y++){
				this.panel.cellGrid[x][y].addActionListener(this);
				this.panel.cellGrid[x][y].cell = this.grid.cellGrid[x][y];
			}
		}


		//this.setVisible(false);
	}



	public void actionPerformed(ActionEvent e) {


		//setting up a new game after the button is pressed
		if(e.getSource() == this.ngPanel.button){
			try{
				int size = Integer.parseInt(this.ngPanel.sizeField.getText());
				int mines = Integer.parseInt(this.ngPanel.minesField.getText());

				//I choose to max the grid size to 30
				if (size<30) this.grid = new Grid(size, 0); 
				else this.grid = new Grid(30, 0);
				System.out.println("size: " + this.grid.size);
				//Of course at least one cell should remain safe
				if (mines<(this.grid.size*this.grid.size)) this.grid.mineCount = mines; 
				else this.grid.mineCount = (this.grid.size*this.grid.size - 1);
				System.out.println("mine count: " + this.grid.mineCount);
				this.grid.initGrid();


				//this.grid.initGrid();
				this.initWindow();
				this.ngWindow.setVisible(false);
				int windowSize = this.grid.size*30; //each icon is 30px long
				this.setSize(windowSize, windowSize);
				this.setResizable(false);
				this.setVisible(true);
			}
			catch (NumberFormatException exc) {
				if(this.ngValidate){
					this.ngPanel.add(new JLabel("Please enter valid numbers"));
					this.ngPanel.revalidate();
					this.ngValidate = false;
				}
			}
		}

		
		if(!this.grid.gameOver){


			//flagging a cell
			for(int x = 0; x<this.grid.size ; x++){
				for(int y = 0; y<this.grid.size; y++){

					if(e.getSource() == this.panel.cellGrid[x][y]){
						if(e.getModifiers() == 18){

							if(!this.panel.cellGrid[x][y].cell.isFlagged()){
								this.panel.cellGrid[x][y].cell.setFlag();
								System.out.println("calling the flaggin");
								this.panel.cellGrid[x][y].setFlagged();
								//this.panel.cellGrid[x][y].set();
								//this.panel.cellGrid[x][y].setText("f");
							}
							else if(this.panel.cellGrid[x][y].cell.isFlagged()){
								this.panel.cellGrid[x][y].cell.unflag();
								//this.panel.cellGrid[x][y].setText("");
								this.panel.cellGrid[x][y].setHidden();

							}

						}
					}
				}
			}

			//revealing a cell
			for(int x = 0; x<this.grid.size ; x++){
				for(int y = 0; y<this.grid.size; y++){

					if(e.getSource() == this.panel.cellGrid[x][y]){
						if(e.getModifiers() == 16){
							this.grid.playCell(x, y);
							if(this.panel.cellGrid[x][y].cell.isMined()){
								//this.panel.cellGrid[x][y].setText("X");
								System.out.println("Calling the minin");
								this.panel.cellGrid[x][y].setMined();
							}
							else{
								this.panel.cellGrid[x][y].setValue(this.panel.cellGrid[x][y].cell.value);
							}
							this.grid.setGameState();
							System.out.println("game over " + this.grid.gameOver);
							System.out.println("game won " + this.grid.gameWon);

						}
					}
				}
			}

			//updating UI
			for(int x = 0; x<this.grid.size ; x++){
				for(int y = 0; y<this.grid.size; y++){
					if(this.panel.cellGrid[x][y].cell.played){

						if(this.panel.cellGrid[x][y].cell.isMined()){
							this.panel.cellGrid[x][y].setMined();;
						}
						else if(this.panel.cellGrid[x][y].cell.isFlagged()){
							this.panel.cellGrid[x][y].cell.setFlag();
						}
						else{
							this.panel.cellGrid[x][y].setValue(this.panel.cellGrid[x][y].cell.value);
						}
					}
				}
			}
		}
		
		//Finishing the Game
		if(this.grid.gameOver){
			GameOverWindow gow = new GameOverWindow(this.grid.gameWon, this.grid.playTime);
		}




	}

}
