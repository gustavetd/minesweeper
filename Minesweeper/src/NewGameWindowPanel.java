import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//new game popup components
public class NewGameWindowPanel extends JPanel{
	
	private JPanel panel;
	public int minesCount;
	public int size;
	public NewGameButton button;
	public JTextField minesField;
	public JTextField sizeField;
	
	public NewGameWindowPanel(){
		this.panel = new JPanel();
		JLabel minesLabel = new JLabel("Mines number");
		JLabel sizeLabel = new JLabel("Size of the grid");
		this.minesField = new JTextField(4);
		this.sizeField = new JTextField(4);
		this.button = new NewGameButton("New Game");
		
		this.add(sizeLabel);
		this.add(sizeField);
		this.add(minesLabel);
		this.add(minesField);
		
		this.add(button);
		
		
		
	}



}
