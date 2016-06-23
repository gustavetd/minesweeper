/*This class represents a cell in the game grid. 
  It does not store its position, but its value and the fact that it is played or not */

public class Cell {

	private boolean hidden;
	private boolean mined; 
	private boolean flagged;
	public int value;
	public boolean played;


	public Cell(boolean hidden, boolean mined) {
		this.hidden = hidden;
		this.played = false;
		this.mined = mined;
		this.value = 0; //defaults to 0, we need to construct all the cells before assigning all the values.
		if(mined) this.value = -1;
		this.flagged = false;
	}
	
	public boolean isMined(){
		return this.mined;
	}
	
	public void setFlag(){
		this.flagged = true;
	}
	
	public void unflag(){
		this.flagged = false;
	}
	public boolean isFlagged(){
		return this.flagged;
	}
	
	public boolean isHidden(){
		return this.hidden;
	}

	/* Incrementing the values of each cells will be easier than setting all of those separately.
	   This means that by the time we have to set the values we only have to increment cells next to a mine, instead of counting mines for each cell. */
	public void incrementValue(){
		this.value++;
	}

	

	public void reveal(){
		this.hidden = false;
	}
	
	






}
