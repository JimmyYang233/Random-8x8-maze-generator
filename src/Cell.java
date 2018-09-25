
public class Cell {

	public int id;
	public boolean hasRightWall;
	public boolean hasBottomWall;
	
	public Cell(int aId){
		id = aId;
		hasRightWall = false;
		hasBottomWall = false;
	}
	
	public Cell(){
		id = 0;
		hasRightWall = false;
		hasBottomWall = false;
	}
}
