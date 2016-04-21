package checkersmodel;

import java.awt.Dimension;
import java.util.Observable;

public class CheckersModel extends Observable {
	Board myBoard = new Board();
	int turn;
	int size;
	Dimension selected;
	
	public CheckersModel() {
		myBoard.init();
		size = 800;
		turn = 1;
		selected = null;
	}
	
	public Piece getPieceAt(int x, int y){
		return myBoard.getPiece(x, y);
	}
	
	public void setSize(int s) {
		size = s;
		setChanged();
		notifyObservers();
	}
	
	public int getSize() {
		return size;
	}
	
	public void nextTurn() {
		if(turn == 1) {
			turn = 2;
		}else {
			turn = 1;
		}
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void setSelected(Dimension d) {
		selected = d;
		setChanged();
		notifyObservers();
	}
	
	public Dimension getSelected() {
		return selected;
	}
	
	public void setPiece(int x, int y, Piece newP) {
		myBoard.setPiece(x, y, newP);
		setChanged();
		notifyObservers();
	}
}
