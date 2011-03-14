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
	
	public boolean findLoners(){
		boolean modified = false;
		
		for(int i=1; i<=9; i++){
			List<Box> potentials = new ArrayList<Box>();
			
			for(Box box : boxes){
				if(box.getValue()==null && box.isPotential(i)){
					potentials.add(box);
				}
			}
			
			if(potentials.size()==1){
				potentials.get(0).assignValue(i);
				modified = true;
			}
		}
		
		return modified;
	}
	
	// TODO
	public boolean findNakedTuples(){
		return false;
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