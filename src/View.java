
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View extends JFrame implements Observer {
	private static final long serialVersionUID = 4990734093778181198L;
	
	private Controller c;
	private XOButton[][] buttons = new XOButton[Model.DIM][Model.DIM]; 
	private JPanel jpanel; 
	
	private Turn t;
	
	public View(Model m) {
		super();
		this.setSize(300, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		t = Turn.X;
		
		c = new Controller(this, m); 
		m.addObserver(this);

		jpanel = new JPanel(new GridLayout(Model.DIM, Model.DIM));
		this.add(jpanel);
		
		for(int i = 0; i < Model.DIM; i++) {
			for(int j = 0; j < Model.DIM; j++) {
				XOButton button = new XOButton(); 
				buttons[i][j] = button;
				
				button.setActionCommand(i + ":" + j);
				button.addActionListener(c);
				
				jpanel.add(button);	
			}
		}

		this.setVisible(true);
		
	}
	
	public void resetView() {
		for(int i = 0; i < Model.DIM; i++) {
			for(int j = 0; j < Model.DIM; j++) {
				buttons[i][j].setIcon(null); 
			}
		}
	}
	
	public XOButton[][] getButtons() {
		return buttons; 
	}

	public Turn getTurn() {
		return t;
	}

	public void setTurn(Turn t) {
		this.t = t;
	}

	@Override
	public void update(Observable o, Object arg) {		
		if(arg instanceof int[]) {
			int[] pos = (int[]) arg;
			buttons[pos[0]][pos[1]].setTurn(t);
		}
		
		else if(arg instanceof String) {
			String game = (String) arg;
			if(game.equals("win")) {
				if(t == Turn.X) {
					JOptionPane.showMessageDialog(this, "Trump won, what have you done!");
				}
				else {
					JOptionPane.showMessageDialog(this, "Clinton won! I mean, it's better than Trump...");
				}
			}
			else if(game.equals("tie")) {
				JOptionPane.showMessageDialog(this, "I don't think a tie is possible when choosing the prez");
			}
			else if(game.equals("reset")) {
				int response = JOptionPane.showConfirmDialog(this, "Would you like try to save America again?");
				if(response == JOptionPane.YES_OPTION) {
					resetView(); 
				}
				else if(response == JOptionPane.NO_OPTION) {
					this.setVisible(false);
					this.dispose();
				}
			}
		}
	}
}
