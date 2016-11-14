
public class StartPrezTicTacToe {
	public static void main(String[] args) {
		Model m = new Model();
		View v = new View(m);
		new Controller(v, m);
	}
}
