
public class StartPrezTicTacToe {
	public static void main(String[] args) {
		Model m = new Model();
		new View(m);
		new Controller(m);
	}
}
