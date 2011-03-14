package sudoku;

import java.util.ArrayList;
import java.util.List;

public class Box{

	private List<Integer> potentialValues;
	private Integer value;
	private Sector sector;
	private Column column;
	private Row row;
	
	public Box(Sector sector, Row row, Column column){
		value = null;
		
		potentialValues = new ArrayList<Integer>();
		
		for(int i=1; i<=9; i++)
			potentialValues.add(i);
			
		sector.addBox(this);
		row.addBox(this);
		column.addBox(this);
		
		this.sector = sector;
		this.row = row;
		this.column = column;
	}
	
	public Integer getValue(){
		return value;
	}
	
	public void discard(Integer discard){
		if(discard.equals(value))
			throw new IllegalStateException("Trying to discard actual value");
		
//		System.out.println("Discarding " + discard + " from " + row.getRowNumber() + "," + column.getColumnNumber() + " [" + sector.getSectorNumber() + "]");
		
		if(potentialValues.remove(discard)){
			if(potentialValues.size()==1){
				assignValue(potentialValues.get(0));
			}
		}
	}
	
	public void assignValue(Integer actual){
		if(!potentialValues.contains(actual))
		 	throw new IllegalStateException("Trying to assign impossible value");
		 	
		value = actual;
		potentialValues.clear();
		potentialValues.add(value);
		
		sector.discard(value, this);
		column.discard(value, this);
		row.discard(value, this);
	}

	public boolean isPotential(int i) {
		return potentialValues.contains(i);
	}
}