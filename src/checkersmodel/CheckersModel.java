package checkersmodel;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;

public class CheckersModel extends Observable {
	Board myBoard = new Board();
	ArrayList<ArrayList<Point2D>> possibleMoves;
	
	int turn;
	int size;
	Point2D selected;
	
	public CheckersModel() {
		myBoard.init();
		possibleMoves = new ArrayList<ArrayList<Point2D>>();
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
	
	public void setSelected(Point2D d) {
		selected = d;
		setChanged();
		notifyObservers();
	}
	
	public Point2D getSelected() {
		return selected;
	}
	
	public void setPiece(int x, int y, Piece newP) {
		myBoard.setPiece(x, y, newP);
		setChanged();
		notifyObservers();
	}
	
	public void resetAvailableMoves() {
		possibleMoves = new ArrayList<ArrayList<Point2D>>();
		setChanged();
		notifyObservers();
	}
	
	public void addMove(ArrayList<Point2D> p) {
		ArrayList<Point2D> tmp = new ArrayList<Point2D>();
		for (Point2D point : p) {
			tmp.add((Point2D) point.clone());
		}
		possibleMoves.add(tmp);
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<ArrayList<Point2D>> getMoves() {
		return possibleMoves;
	}
	
	public boolean inMoveset(Point2D p) {
		if (possibleMoves == null) {
			return false;
		}
		
		
		for (ArrayList<Point2D> chains : possibleMoves) {
			for (Point2D jump : chains) {
				if ((int)p.getX() == (int)jump.getX() && (int)p.getY() == (int)jump.getY()) {
					return true;
				}
			}
		}
		return false;
	}
}
