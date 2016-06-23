import javax.swing.*;
public class Main {

	public static void main(String[] args) {
		
		//Setting default window size and new game popup
		Grid grid = new Grid(1, 1);
		grid.initGrid();
		
		
		//main menu window with rules and creating a new game
		MainMenu menu = new MainMenu(grid);
	
			
		
	}

}
