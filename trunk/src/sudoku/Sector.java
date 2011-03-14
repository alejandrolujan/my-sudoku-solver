package sudoku;

public class Sector extends BoxContainer{

	private int sectorNumber;
	
	public Sector(int sectorNumber) {
		this.sectorNumber = sectorNumber;
	}

	public int getSectorNumber() {
		return sectorNumber;
	}
	
}