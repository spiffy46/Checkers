package checkersmodel;

public class Board {

	Square[][] board = new Square[8][8];
	
	public void init() {
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < board[0].length; y++){
				if(y <= 2){
					if((x + y) % 2 == 0){
						board[x][y] = new Square(2);
					}
				}else if(y >= 5){
					if((x + y) % 2 == 0){
						board[x][y] = new Square(1);
					}
				}else{
					board[x][y] = new Square();
				}
			}
		}
	}
	
	public Piece getPiece(int x, int y) {
		return board[x][y].getPiece();
	}
	
	public void setPiece(int x, int y, Piece newP) {
		board[x][y].setPiece(newP);
	}
}
