
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.AbstractMap;
import java.util.ArrayList;
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
	
	public View(Model m) {
		super();
		this.setSize(300, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		c = new Controller(m); 
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

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof AbstractMap.SimpleEntry<?,?>) {
			@SuppressWarnings("unchecked")
			AbstractMap.SimpleEntry<int[], Turn> map = (AbstractMap.SimpleEntry<int[], Turn>) arg;
			buttons[map.getKey()[0]][map.getKey()[1]].setTurn(map.getValue());
		}
		else if (arg instanceof Turn) {
			Turn turn = (Turn) arg;
			if (turn == Turn.X) {
				JOptionPane.showMessageDialog(this, "Trump won, what have you done!");
			} else {
				JOptionPane.showMessageDialog(this, "Clinton won! I mean, it's better than Trump...");
			}
		} else if (arg instanceof String) {
			String game = (String) arg;
			if (game.equals("tie")) {
				JOptionPane.showMessageDialog(this, "I don't think a tie is possible when choosing the prez");
			} else if (game.equals("reset")) {
				int response = JOptionPane.showConfirmDialog(this, "Would you like try to save America again?");
				if (response == JOptionPane.YES_OPTION) {
					resetView();
				} else if (response == JOptionPane.NO_OPTION) {
					this.setVisible(false);
					this.dispose();
				}
			}
		}
	}
}
