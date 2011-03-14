package sudoku;

public class BoardFactory {

	public static Board createBoard(String line){
		Board board = new Board();
		
		for(int i=0; i<line.length(); i++){
			int row = i / 9;
			int column = i % 9;
			String value = line.substring(i, i+1);
			
			if(!value.equals("."))
				board.assignValue(row, column, Integer.parseInt(value));
		}
		
		return board;
	}
}
