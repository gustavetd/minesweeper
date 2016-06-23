import javax.swing.JPanel;

public class MainWindowPanel extends JPanel {
	
	public Grid grid;
	public CellButton[][] cellGrid;
	
	public MainWindowPanel(Grid grid){
		this.grid = grid;
		this.cellGrid = new CellButton[grid.size][grid.size];
		for(int x = 0; x<this.grid.size ; x++){
			for(int y = 0; y<this.grid.size; y++){
				this.cellGrid[x][y] = new CellButton(this.grid.cellGrid[x][y]);
				this.add(this.cellGrid[x][y]);
			}
		}
	}

}
