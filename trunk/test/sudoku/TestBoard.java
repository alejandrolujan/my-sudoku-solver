package sudoku;

import junit.framework.TestCase;

public class TestBoard extends TestCase{

	
	public void testBoard(){
		String boardLine = "2......6.....75.3..48.9.1.....3.....3...1...9.....8.....1.2.57..8.73.....9......4";
		Board board = BoardFactory.createBoard(boardLine);
		board.solve();
		
		System.out.println(boardLine);
		System.out.println(board.toString());
		assertTrue("Board was not completely solved", board.isSolved());
	}
}
