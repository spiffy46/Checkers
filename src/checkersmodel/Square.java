package checkersmodel;

public class Square {

	Piece p;
	
	public Square() {
		p = null;
	}
	
	public Square(int owner) {
		p = new Piece(owner);
	}
	
	public Piece getPiece() {
		return p;
	}
	
	public void setPiece(Piece newP) {
		p = newP;
	}
	
}
