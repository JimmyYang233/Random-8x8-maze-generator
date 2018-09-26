
public class Main {

	public static void main(String[] args) {
		Maze aMaze = new Maze();
//		aMaze.generateRow();
//		aMaze.drawWallWithID();
//		aMaze.drawWall();
//		aMaze.generateRow();

		for(int i = 0;i<8;i++){
			aMaze.generateRow();
			aMaze.drawWallWithID();
			aMaze.drawWall();
		}
		aMaze.finalizeMaze();
		aMaze.drawWall();

	}

}
