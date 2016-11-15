import javax.swing.ImageIcon;
import javax.swing.JButton;

public class XOButton extends JButton {
	private static final long serialVersionUID = 5032600251894967508L;
	private ImageIcon X, O; 
	
	public XOButton() {
		X = new ImageIcon(this.getClass().getResource("TRUMP.jpg"));
		O = new ImageIcon(this.getClass().getResource("CLINTON.jpg"));
	}
	
	public void setTurn(Turn t) {
		if(t == Turn.X) {
			this.setIcon(X);
		}
		else {
			this.setIcon(O);
		}
	}
	
}
