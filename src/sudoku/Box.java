package sudoku;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;

public class Box{

	private Set<Integer> potentialValues;
	private Integer value;
	private Sector sector;
	private Column column;
	private Row row;
	
	public Box(Sector sector, Row row, Column column){
		value = null;
		
		potentialValues = Collections.newSetFromMap(new ConcurrentHashMap<Integer, Boolean>());
		
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
	
	public boolean discard(Integer discard){
		
		if(discard.equals(value))
			throw new IllegalStateException("Trying to discard actual value");
		
//		System.out.println("Discarding " + discard + " from " + row.getRowNumber() + "," + column.getColumnNumber() + " [" + sector.getSectorNumber() + "]");
		
		boolean updated = false;
		
		if(potentialValues.remove(discard)){
			updated = true;
			if(potentialValues.size()==1){
				assignValue((potentialValues.toArray(new Integer[0]))[0]);
			}
		}
		
		return updated;
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

	public Set<Integer> getPotentialValues() {
		return potentialValues;
	}

	public boolean discardAllBut(Set<Integer> values) {
		boolean updated = false;
		
		for(Integer potential : potentialValues){
			if(!values.contains(potential)){
				updated |= discard(potential);
			}
		}
		
		return updated;
	}
}