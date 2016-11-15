import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestModel {
	private Model m;
	
	@Before
	public void setup() {
		m = new Model(); 
		
	}
	
	@Test
	public void testWinHorizontal() {
		for(int i = 0; i < Model.DIM; i++) {
			m.getGrid()[0][i] = 1;  
		}
		assertTrue(m.win());
		
		m.resetBoard();
		for(int i = 0; i < Model.DIM; i++) {
			m.getGrid()[1][i] = 1;  
		}
		assertTrue(m.win());
		
		m.resetBoard();
		for(int i = 0; i < Model.DIM; i++) {
			m.getGrid()[2][i] = 1;  
		}
		assertTrue(m.win());
		
	}
	
	@Test
	public void testWinVertical() {
		for(int i = 0; i < Model.DIM; i++) {
			m.getGrid()[i][0] = 1;  
		}
		assertTrue(m.win());
		
		m.resetBoard();
		for(int i = 0; i < Model.DIM; i++) {
			m.getGrid()[i][1] = 1;  
		}
		assertTrue(m.win());
		
		m.resetBoard();
		for(int i = 0; i < Model.DIM; i++) {
			m.getGrid()[i][2] = 1;  
		}
		assertTrue(m.win());
		
		m.resetBoard();
	}
	
	@Test
	public void testWinDiagonal() {
		m.getGrid()[0][0] = 1;
		m.getGrid()[1][1] = 1;
		m.getGrid()[2][2] = 1;
		assertTrue(m.win());
		
		m.resetBoard();
		
		m.getGrid()[0][2] = 1;
		m.getGrid()[1][1] = 1;
		m.getGrid()[2][0] = 1;
		assertTrue(m.win());
	}

}
