package sudoku;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.TestCase;

public class TestBoard extends TestCase {

	public void testBoard() throws IOException {

		// Open the file that is the first
		// command line parameter
		FileInputStream fstream = new FileInputStream("test/subig20.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		
		int attempts = 0;
		int solved = 0;
		int maxattempts = 100000;
		long startTime = System.currentTimeMillis();
		
		// Read File Line By Line
		while ((strLine = br.readLine()) != null && maxattempts-- > 0) {
			Board board = BoardFactory.createBoard(strLine);
			board.solve();

//			System.out.println(strLine);
//			System.out.println(board.toString());
			
			//assertTrue("Board was not completely solved", board.isSolved());
			attempts++;
			if(board.isSolved())
				solved++;
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Solved " + solved + "/" + attempts + " in " + (endTime-startTime)/1000 + " secs");

	}
}
