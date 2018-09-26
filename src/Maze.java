import java.util.ArrayList;

public class Maze {

	public Cell[][] maze;
	public ArrayList<Cell> sets = new ArrayList<Cell>();
	
	public int currentRow;
	
	public Maze(){
		maze = new Cell[8][8];
		for(int i = 0;i<8;i++){
			for(int j = 0; j<8;j++){
				maze[i][j] = new Cell();
			}
		}
		currentRow = 0;
	}
	
	public void generateRow(){
		givesID();
		givesRightWall();
		givesButtomWall();
		if(currentRow!=7){
			setNewRow();
			currentRow++;
		}
		
	}
	
	private void setNewRow() {
		for(int i = 0;i<8;i++){
			if(maze[currentRow][i].hasBottomWall){
				maze[currentRow+1][i].id = 0;
			}
			else{
				maze[currentRow+1][i].id = maze[currentRow][i].id;
			}
			
		}
	}

	private void givesID() {
		int previousNumber= currentRow*8;
		for(int i = 0;i<8;i++){
			if(maze[currentRow][i].id == 0){
				maze[currentRow][i].id = ++previousNumber;
			}
		}
	}

	public void givesRightWall(){
		
		for(int i = 0;i<7;i++){
			double random = Math.random();
			if(maze[currentRow][i].id == maze[currentRow][i+1].id){
				maze[currentRow][i].hasRightWall = true;
			}
			else if(random>=0.5){
				maze[currentRow][i].hasRightWall = true;
			}
			else{
				int idToChange = maze[currentRow][i+1].id;
				for(int j = 0;j<8;j++) {
					if(maze[currentRow][j].id ==idToChange) {
						maze[currentRow][j].id = maze[currentRow][i].id;
					}
				}
				
			}
		}
	}
	
	public void finalizeMaze() {
		for(int i = 0;i<7;i++){
			if(maze[currentRow][i].id != maze[currentRow][i+1].id&&(maze[currentRow][i].hasRightWall)){
				maze[currentRow][i].hasRightWall = false;
				int idToChange = maze[currentRow][i+1].id;
				for(int j = 0;j<8;j++) {
					if(maze[currentRow][j].id ==idToChange) {
						maze[currentRow][j].id = maze[currentRow][i].id;
					}
				}
			}
		}
	}
	
	private void givesButtomWall() {
		if(currentRow==7){
			for(int i = 0;i<8;i++){
				maze[currentRow][i].hasBottomWall = true;
			}
		}
		else{
			for(int i = 0;i<8;i++){
				double random = Math.random();
				if(random>=0.5){
					maze[currentRow][i].hasBottomWall = true;
				}
				sets.add(maze[currentRow][i]);
				if((i==7)||(maze[currentRow][i].id!=maze[currentRow][i+1].id)){
					if(!hasDownPassage()){
						getaDownPassage();
						
					}
					sets.clear();
				}
			}
		}
	}

	public void drawWall(){
		System.out.println(" _ _ _ _ _ _ _ _ ");
		for(int i = 0;i<8;i++){
			for(int j = 0;j<8;j++){
				if(j==0){
					if(maze[i][j].hasRightWall&&maze[i][j].hasBottomWall){
						System.out.print("|_|");
					}
					else if(maze[i][j].hasRightWall){
						System.out.print("| |");
					}
					else if(maze[i][j].hasBottomWall){
						System.out.print("|_ ");
					}
					else{
						System.out.print("|  ");
					}
				}
				else if(j==7){
					if(maze[i][j].hasBottomWall){
						System.out.println("_|");
					}
					else{
						System.out.println(" |");
					}
				}
				else{
					if(maze[i][j].hasRightWall&&maze[i][j].hasBottomWall){
						System.out.print("_|");
					}
					else if (maze[i][j].hasRightWall){
						System.out.print(" |");
					}
					else if(maze[i][j].hasBottomWall){
						System.out.print("_ ");
					}
					else{
						System.out.print("  ");
					}
				}
			}
		}
	}
	
	public void drawWallWithID(){
		System.out.println(" _ _ _ _ _ _ _ _ ");
		for(int i = 0;i<8;i++){
			for(int j = 0;j<8;j++){
				if(j==0){
					if(maze[i][j].hasRightWall&&maze[i][j].hasBottomWall){
						System.out.print("|" + maze[i][j].id + "|");
					}
					else if(maze[i][j].hasRightWall){
						System.out.print("|" + maze[i][j].id + "|");
					}
					else{
						System.out.print("|" + maze[i][j].id + " ");
					}
				}
				else if(j==7){
					System.out.println("" + maze[i][j].id + "|");
				}
				else{
					if(maze[i][j].hasRightWall&&maze[i][j].hasBottomWall){
						System.out.print("" + maze[i][j].id + "|");
					}
					else if (maze[i][j].hasRightWall){
						System.out.print("" + maze[i][j].id + "|");
					}
					else{
						System.out.print("" + maze[i][j].id + " ");
					}
				}
			}
		}
	}
	
	
	public boolean hasDownPassage(){
		if(sets.size()==0){
			return true;
		}
		for( Cell cell : sets){
			if(!cell.hasBottomWall){
				return true;
			}
		}
		return false;
	}
	
	public void getaDownPassage(){
		int size = sets.size();
		int random = (int)(Math.random()*size);
		sets.get(random).hasBottomWall = false;
	}
}
