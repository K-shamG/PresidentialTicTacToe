import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
	private Model m;
	private View v;
	
	public Controller(View v, Model m) {
		this.m = m;
		this.v = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		XOButton jb = (XOButton) e.getSource();
		String s = (String) jb.getActionCommand(); 
		String[] coord = s.split(":");
		
		int x = Integer.parseInt(coord[0]);
		int y = Integer.parseInt(coord[1]);
		
		Turn t = m.play(x, y);
		v.setTurn(t);
	}

}
