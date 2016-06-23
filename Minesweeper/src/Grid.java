import java.util.*;

public class Grid {
	
	public double startTime;
	public double endTime;
	public double playTime;
	public int size;
	public int mineCount;
	public Cell[][] cellGrid;
	public boolean[][] minesPos;
	public boolean gameOver;
	public boolean gameWon;

	public Grid(int size, int mineCount) {
		this.size = size;
		this.mineCount = mineCount;
		this.cellGrid = new Cell[size][size];
		this.gameOver = false;
		this.gameWon = false;
		this.minesPos = new boolean[size][size];



		for(int x = 0; x < this.size; x++){
			for(int y = 0; y < this.size; y++){
				this.minesPos[x][y] = false;
			}
		}
	}

	public void setSize(int size){
		this.size = size;
	}

	public void setMines(int mines){
		this.mineCount = mines;
	}

	//Randomizes the position of the mines in the grid.
	public void initMines(){
		int i = 0; 
		Random rand = new Random();
		while( i < this.mineCount ){
			int randX = rand.nextInt(size);
			int randY = rand.nextInt(size);
			if(this.minesPos[randX][randY] == false){
				i++;
				this.minesPos[randX][randY] = true;
			}
		}
	}

	//Puts the mines in the correct cells and also sets the value of the cells.
	public void initGrid(){
		this.initMines();
		for(int x = 0; x < this.size; x++){
			for(int y = 0; y < this.size; y++){
				this.cellGrid[x][y] = new Cell(true, this.minesPos[x][y]);
			}
		}

		for(int x = 0; x < this.size; x++){
			for(int y = 0; y < this.size; y++){
				if (this.minesPos[x][y] == true){
					//try-catch structure provides a way of skipping the check for border cells.
					try {this.cellGrid[x-1][y-1].incrementValue();}catch (IndexOutOfBoundsException e) {}
					try {this.cellGrid[x][y-1].incrementValue();}catch (IndexOutOfBoundsException e) {}
					try {this.cellGrid[x+1][y-1].incrementValue();}catch (IndexOutOfBoundsException e) {}
					try {this.cellGrid[x-1][y].incrementValue();}catch (IndexOutOfBoundsException e) {}
					try {this.cellGrid[x+1][y].incrementValue();}catch (IndexOutOfBoundsException e) {}
					try {this.cellGrid[x-1][y+1].incrementValue();}catch (IndexOutOfBoundsException e) {}
					try {this.cellGrid[x][y+1].incrementValue();}catch (IndexOutOfBoundsException e) {}
					try {this.cellGrid[x+1][y+1].incrementValue();}catch (IndexOutOfBoundsException e) {}
				}
			}
		}
		this.startTime = System.currentTimeMillis()/1000;

	}
	
	//This recursive method allows for auto-revealing of all the null cells.
	public void revealNull(int x, int y){
		try {if(this.cellGrid[x-1][y-1].value == 0 && !this.cellGrid[x-1][y-1].played){
			this.cellGrid[x-1][y-1].played = true;
			revealNull(x-1, y-1);
		}
		else if(!this.cellGrid[x-1][y-1].isMined()) this.playCell(x-1, y-1);
		}catch (IndexOutOfBoundsException e) {}

		try {if(this.cellGrid[x][y-1].value == 0 && !this.cellGrid[x][y-1].played){
			this.cellGrid[x][y-1].played = true;
			revealNull(x,y-1);
		}
		else if(!this.cellGrid[x][y-1].isMined()) this.playCell(x, y-1);
		}catch (IndexOutOfBoundsException e) {}

		try {if(this.cellGrid[x+1][y-1].value == 0 && !this.cellGrid[x+1][y-1].played){
			this.cellGrid[x+1][y-1].played = true;
			revealNull(x+1,y-1);
		}
		else if(!this.cellGrid[x+1][y+1].isMined()) this.playCell(x+1, y-1);
		}catch (IndexOutOfBoundsException e) {}

		try {if(this.cellGrid[x-1][y].value == 0 && !this.cellGrid[x-1][y].played){
			this.cellGrid[x-1][y].played = true;
			revealNull(x-1,y);
		}
		else if(!this.cellGrid[x-1][y].isMined()) this.playCell(x-1, y);
		}catch (IndexOutOfBoundsException e) {}

		try {if(this.cellGrid[x+1][y].value == 0 &&!this.cellGrid[x+1][y].played){
			this.cellGrid[x+1][y].played = true;
			revealNull(x+1,y);
		}
		else if(!this.cellGrid[x+1][y].isMined()) this.playCell(x+1, y);
		}catch (IndexOutOfBoundsException e) {}

		try {if(this.cellGrid[x-1][y+1].value == 0 && !this.cellGrid[x-1][y+1].played){
			this.cellGrid[x-1][y+1].played = true;
			revealNull(x-1,y+1);
		}
		else if(!this.cellGrid[x-1][y+1].isMined()) this.playCell(x-1, y+1);
		}catch (IndexOutOfBoundsException e) {}

		try {if(this.cellGrid[x][y+1].value == 0 && !this.cellGrid[x][y+1].played){
			this.cellGrid[x][y+1].played = true;
			revealNull(x,y+1);
		}
		else if(!this.cellGrid[x][y+1].isMined()) this.playCell(x, y+1);
		}catch (IndexOutOfBoundsException e) {}

		try {if(this.cellGrid[x+1][y+1].value == 0 && !this.cellGrid[x+1][y+1].played){
			this.cellGrid[x+1][y+1].played = true;
			revealNull(x+1,y+1);
		}
		else if(!this.cellGrid[x+1][y+1].isMined()) this.playCell(x+1, y+1);
		}catch (IndexOutOfBoundsException e) {}
	}
	
	/* playing a cell will reveal it and may cause a game over case.
	 * This is why play time is calculated here*/
	public void playCell(int x, int y){
		if(!this.cellGrid[x][y].played){
			if(this.cellGrid[x][y].isMined()){
				this.cellGrid[x][y].reveal();
				this.cellGrid[x][y].played = true;
				this.gameOver = true;
				this.endTime = System.currentTimeMillis()/1000;
				this.playTime = this.endTime - this.startTime;
			}
			else{
				this.cellGrid[x][y].reveal();
				this.cellGrid[x][y].played = true;
				if(this.cellGrid[x][y].value == 0){
					revealNull(x, y);
				}
			}
		}

	}

	//Updates the game state to see if there is a win situation
	public void setGameState(){
		int hiddenCount = this.size*this.size;
		for(int x = 0; x<this.size; x++){
			for(int y = 0; y<this.size; y++){
				if(this.cellGrid[x][y].played) hiddenCount--;
			}	
		}
		if(hiddenCount == this.mineCount){
			this.gameWon = true;
			this.gameOver = true;
			this.endTime = System.currentTimeMillis()/1000;
			this.playTime = this.endTime - this.startTime;
		}
	}




}
