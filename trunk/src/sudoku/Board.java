package sudoku;

import java.util.ArrayList;
import java.util.List;

public class Board{

	private List<Row> rows;
	private List<Column> columns;
	private List<Sector> sectors;
	
	public Board(){
		rows = new ArrayList<Row>();
		columns = new ArrayList<Column>();
		sectors = new ArrayList<Sector>();
		
		for(int i=0; i<9; i++){
			rows.add(new Row(i));
			columns.add(new Column(i));
			sectors.add(new Sector(i));
		}
		
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				int sector = 3*(i/3) + (j/3);
				new Box(sectors.get(sector), rows.get(i), columns.get(j));
			}
		}
	}
	
	
	public void assignValue(int row, int column, int value){
		rows.get(row).assignValue(column, value);
		//System.out.println(this.toString());
	}
	
	public void solve(){
		boolean updated;
		
		do{
			updated = false;
			
			for(Row row : rows)
				updated |= row.findNakedTuples() || row.findHiddenTuples();
			
			for(Column column : columns)
				updated |= column.findNakedTuples() || column.findHiddenTuples();
			
			for(Sector sector : sectors)
				updated |= sector.findNakedTuples() || sector.findHiddenTuples();
			
		} while(updated);	
		
	}
	
	public boolean isSolved(){
		
		for(Row row : rows)
			if(!row.isSolved()) 
				return false;
		
		for(Column column : columns)
			if(!column.isSolved()) 
				return false;
		
		for(Sector sector : sectors)
			if(!sector.isSolved()) 
				return false;
		
		return true;
	}
	
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		for(Row row : rows){
			sb.append(row.toString());
			sb.append("\n");
		}
		
		return sb.toString();
	}
}