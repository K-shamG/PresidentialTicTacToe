
import java.util.AbstractMap;
import java.util.Observable;


public class Model extends Observable {
	private int[][] grid; 
	
	public static final int DIM = 3; 
	
	private int numTurns; 
	private int blankBoxes;
	
	private Turn turn; 
	
	public Model() {
		numTurns = 0; 
		blankBoxes = 9; 
		turn = Turn.X;
		
		grid = new int[DIM][DIM];
		for(int i = 0; i < DIM; i++) {
			for(int j = 0; j < DIM; j++) {
				grid[i][j] = -1;
			}
		}
		setChanged(); 
		notifyObservers();
	}
	
	public void play(int x, int y) {
		int[] pos = new int[2];
		
		if(blankBoxes == 0) {
			return;
		}
		
		if(numTurns < 9) {
			if(grid[x][y] == -1) {
				if(turn == Turn.O){
					grid[x][y] = 0;
				}
				else if(turn == Turn.X){
					grid[x][y] = 1;
				}
				numTurns++;
				blankBoxes--;
				
				pos[0] = x;
				pos[1] = y;
				
				AbstractMap.SimpleEntry<int[], Turn> map = new AbstractMap.SimpleEntry<int[], Turn> (pos, this.turn);
				
				setChanged(); 
				this.notifyObservers(map);
				clearChanged(); 
			}
		}
		
		
	if(numTurns >= 5) {
		if(win()) {
			setChanged(); 
			notifyObservers(this.turn);
			clearChanged(); 
			resetBoard();
		}
		else if(tie()) {
			setChanged(); 
			notifyObservers("tie");
			clearChanged(); 
			resetBoard();
		}
	}
	
	switchTurns();
}
	
	private void switchTurns() {
		if (turn == Turn.X) turn = Turn.O;
		else if(turn == Turn.O) turn = Turn.X;
	}
	
	public boolean win() {
		int result = -1;
		
		for (int i = 0; i < 3; i++) {
			if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][0] != -1) {
				result = 0;
			}
			else if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] && grid[0][i] != -1) {
				result = 0;
			}
		}

		if ((grid[0][0] == grid[1][1]) && (grid[1][1] == grid[2][2]) && (grid[0][0] != -1)) {
			result = 0;
		}

		else if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] != -1) {
			result = 0;
		}

		return result == 0; 
	}
	
	private boolean tie() {
		if(blankBoxes == 0 && !win()) {
			return true; 
		}
		return false;
	}

	public void resetBoard() {
		for(int i = 0; i < Model.DIM; i++) {
			for(int j = 0; j < Model.DIM; j++) {
				grid[i][j] = -1; 
			}
		}
		
		numTurns = 0; 
		blankBoxes = 9; 
		
		setChanged();
		notifyObservers("reset");
	}
	
	public int[][] getGrid() {
		return grid; 
	}

}
