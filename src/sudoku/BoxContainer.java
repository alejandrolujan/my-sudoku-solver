package sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BoxContainer{
	
	protected List<Box> boxes;

	public BoxContainer(){
		boxes = new ArrayList<Box>();
	}
	
	public void addBox(Box box){
		boxes.add(box);
	}
	
	public void discard(Integer discard, Box initiator){
		for(Box box : boxes)
			if(box!=initiator)
				box.discard(discard);
	}
	
	public boolean findNakedTuples(){
		boolean updated = false;
		
		for(Box box : boxes){
			if(box.getValue()==null){
				Set<Box> nakedTuple = findNakedTuple(box.getPotentialValues());
				
				if(nakedTuple!=null){
					// Found a naked N-tuple, remove values from other boxes in container
					for(Box nonTupleBox : boxes){
						if(nonTupleBox.getValue()==null && !nakedTuple.contains(nonTupleBox)){
							for(Integer value : box.getPotentialValues()){
								updated |= nonTupleBox.discard(value);
							}
						}
					}
				}
			}
		}
		
		return updated;
	}
	
	
	private Set<Box> findNakedTuple(Set<Integer> values){
		Set<Box> tuple = new HashSet<Box>();
		for(Box box : boxes){
			Set<Integer> potentials = new HashSet<Integer>();
			potentials.addAll(box.getPotentialValues());
			if(potentials.equals(values))
				tuple.add(box);
		}
		
		return (tuple.size() == values.size()) ? tuple : null;
	}
	
	
	public boolean findHiddenTuples(){
		boolean updated = false;
		Set<Integer> values = new HashSet<Integer>();
		
		for(int i=1; i<=9; i++){
			for(int j=i+1; j<=9; j++){
				values.clear();
				values.add(i);
				values.add(j);
				
				Set<Box> hiddenTwin = findHiddenTuple(values);
				
				if(hiddenTwin!=null){
					updated |= processHiddenTuple(hiddenTwin, values);
				}
			}
		}
		
		for(int i=1; i<=9; i++)
			for(int j=i+1; j<=9; j++)
				for(int k=j+1; k<=9; k++){
					values.clear();
					values.add(i);
					values.add(j);
					values.add(k);
					
					Set<Box> hiddenTriplet = findHiddenTuple(values);
					
					if(hiddenTriplet!=null)
						updated |= processHiddenTuple(hiddenTriplet, values);
				}
		
		return updated;
	}
	
	private Set<Box> findHiddenTuple(Set<Integer> values){
		Set<Box> tuple = new HashSet<Box>();
		Set<Integer> containedValues = new HashSet<Integer>();
		
		for(Box box : boxes)
			if(box.getValue()==null)			// only process unsolved boxes
				for(Integer value : values)
					if(box.isPotential(value)){	// If box contains at least one of values
						tuple.add(box);			// it belongs to the tuple
						containedValues.add(value);
					}
		
		return (tuple.size() == values.size() && values.size() == containedValues.size()) ? tuple : null;
	}
	
	private boolean processHiddenTuple(Set<Box> tuple, Set<Integer> values){
		boolean updated = false;
		
		for(Box box : tuple)
			updated |= box.discardAllBut(values);
		
		return updated;
	}
	
	public boolean isSolved(){
		Set<Integer> valueSet = new HashSet<Integer>();
		
		for(Box box : boxes){
			Integer value = box.getValue();
			
			if(value==null || !valueSet.add(value))
				return false;
		}
		
		return valueSet.size()==9;
	}
	
}