import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

//This is onr cell
public class CellButton extends JButton implements MouseListener {

	public Cell cell;
	private Image img;
	


	//Collection of methods to repaint the button
	public CellButton(Cell cell){
		super("");
		try {
			this.img = ImageIO.read(new File("hiddenCell.png"));
			this.setIcon(new ImageIcon(this.img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	public void setHidden(){
		try {
			this.img = ImageIO.read(new File("hiddenCell.png"));
			this.setIcon(new ImageIcon(this.img));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMined(){
		try {
			System.out.println("minin");
			this.img = ImageIO.read(new File("minedCellS.png"));
			this.setIcon(new ImageIcon(this.img));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setFlagged(){
		try {
			System.out.println("flaggin");
			this.img = ImageIO.read(new File("flaggedCell.png"));
			this.setIcon(new ImageIcon(this.img));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	
	public void setValue(int value){
		try {
			//System.out.println("revealedCell" + value + "S.png");
			this.img = ImageIO.read(new File("revealedCell" + value + "S.png"));
			this.setIcon(new ImageIcon(this.img));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void mouseClicked(MouseEvent e) {


	}


	public void mouseEntered(MouseEvent e) {


	}


	public void mouseExited(MouseEvent e) {


	}


	public void mousePressed(MouseEvent e) {
		
		

	}


	public void mouseReleased(MouseEvent e) {

		

		
		

	}

}
