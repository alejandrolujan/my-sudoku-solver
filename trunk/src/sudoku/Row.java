package sudoku;

public class Row extends BoxContainer{
	
	private int rowNumber;
	
	public Row(int rowNumber) {
		super();
		this.rowNumber = rowNumber;
	}

	public void assignValue(int column, int value){
		boxes.get(column).assignValue(value);
	}
	
	public int getRowNumber() {
		return rowNumber;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<boxes.size(); i++){
			sb.append( boxes.get(i).getValue()==null ? "." : boxes.get(i).getValue() );
			if((i+1)%3==0)
				sb.append(" ");
		}
		
		return sb.toString();
	}
}