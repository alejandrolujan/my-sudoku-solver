package sudoku;

public class Column extends BoxContainer{

	private int columnNumber;
	
	public Column(int columnNumber){
		this.columnNumber = columnNumber;
	}
	
	public int getColumnNumber(){
		return columnNumber;
	}
}