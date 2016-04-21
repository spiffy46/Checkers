package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import checkersmodel.CheckersModel;
import checkersmodel.Piece;
import gui.MainFrame;


public class MyView implements Observer, MouseListener{

	CheckersModel model;
	MainFrame frame;
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof CheckersModel){
			model = (CheckersModel) o;
		} else {}
		frame.refresh();
	}
	
	public void refreshView(Graphics2D g2d) {
		Piece p = null;
		double size = (double)model.getSize();
		double squareSize = size/8;
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if((x + y) % 2 == 0) {
					g2d.setColor(Color.WHITE);
				}else {
					g2d.setColor(Color.BLACK);
				}
				p = model.getPieceAt(x, y);
				g2d.fillRect((int)(x * squareSize), (int)(y * squareSize), (int)squareSize, (int)squareSize);
				
				if(p == null) {}
				else if(p.getOwner() == 1) {
					g2d.setColor(Color.GRAY);
					g2d.fillOval((int)(x * squareSize), (int)(y * squareSize), (int)squareSize, (int)squareSize);
				}else {
					g2d.setColor(Color.RED);
					g2d.fillOval((int)(x * squareSize), (int)(y * squareSize), (int)squareSize, (int)squareSize);
				}
			}

		}
		
		Point2D s = model.getSelected();
		if(s != null) {
			g2d.setColor(Color.YELLOW);
			g2d.setStroke(new BasicStroke(5));
			g2d.drawRect((int)(s.getX() * squareSize), (int)(s.getY() * squareSize), (int)squareSize, (int)squareSize);
			
			highLightMoves(g2d,squareSize,model.getTurn());
			

		}
	}
	
	public void highLightMoves(Graphics2D g2d, double squareSize, int turn) {
		g2d.setColor(Color.GREEN);
		
		ArrayList<ArrayList<Point2D>> moveSet = model.getMoves();
		
		for (ArrayList<Point2D> list: moveSet) {
			for (Point2D point: list) {
				g2d.drawRect((int)(point.getX() * squareSize), (int)(point.getY() * squareSize), (int)squareSize, (int)squareSize);
			}
		}
	}
	
	public void setModel(CheckersModel m) {
		model = m;
		model.addObserver(this);
	}
	
	public void setFrame(MainFrame f) {
		frame = f;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int xCoord = e.getPoint().x;
		int yCoord = e.getPoint().y;
		int squareSize = model.getSize()/8;
		int x = xCoord/squareSize;
		int y = yCoord/squareSize;
		
		if(model.getSelected() == null) {
			if((x+y) % 2 == 1){
				Piece p = model.getPieceAt(x, y);
				if(p != null){
					if(model.getTurn() == p.getOwner()){
						model.setSelected(new Point(x,y));	
						initMoveset(new Point(x,y));
					}
				}
			}
		} else {
			if (inMoveset(new Point(x,y))) {
				
				//TODO Add implementation for a move
				System.out.println("Found in moveset");
			} else {
				model.setSelected(null);
				model.resetAvailableMoves();
			}
			
 /*
			Point2D upLeft = new Point((int)model.getSelected().getX() - 1, (int)model.getSelected().getY() -1);
			Point2D upRight = new Point((int)model.getSelected().getX() + 1, (int)model.getSelected().getY() -1);
			
			if(upLeft.getX() >= 0 && upLeft.getY() >=0) {
				Piece p = model.getPieceAt((int)upLeft.getX(), (int)upLeft.getY());
				if (p == null) {
					if(x == (int)upLeft.getX() && y == (int)upLeft.getY()){
						model.setPiece(x, y, model.getPieceAt((int)model.getSelected().getX(), (int)model.getSelected().getY()));
						model.setPiece((int)model.getSelected().getX(), (int)model.getSelected().getY(), null);
					}
				}
			}
			if(upRight.getY() >=0 && upRight.getX() < 8) {
				Piece p = model.getPieceAt((int)upRight.getX(), (int)upRight.getY());
				if (p == null) {
					if(x == (int)upRight.getX() && y == (int)upRight.getY()){
						model.setPiece(x, y, model.getPieceAt((int)model.getSelected().getX(), (int)model.getSelected().getY()));
						model.setPiece((int)model.getSelected().getX(), (int)model.getSelected().getY(), null);
					}
				}
			} 
			model.setSelected(null);
			
			*/
		}
		
	}
	
	public boolean inMoveset(Point2D p) {
		return model.inMoveset(p);
	}
	
	public void initMoveset(Point2D p) {
		ArrayList<Point2D> initList = new ArrayList<Point2D>();
		if(model.getTurn() == 1) {
			if(p.getX() >= 1 && p.getY() >= 1) {
				Piece tmp = model.getPieceAt((int)p.getX()-1, (int)p.getY()-1);
				if(tmp == null){
					initList.add(new Point((int)p.getX()-1,(int)p.getY()-1));
					model.addMove(initList);
				}
			}
			initList.clear();
			if(p.getX() <= 6 && p.getY() >= 1) {
				Piece tmp = model.getPieceAt((int)p.getX()+1, (int)p.getY()-1);
				if(tmp == null){
					initList.add(new Point((int)p.getX()+1,(int)p.getY()-1));
					model.addMove(initList);
				}
			}
		} else {
			
		}
		findMoveset(p,new ArrayList<Point2D>());
	}
		
	public void findMoveset(Point2D p, ArrayList<Point2D> l) {
		
		if (model.getTurn() == 1) {
			//Logic for player 1
			boolean left = leftPossible(p);
			boolean right = rightPossible(p);
			if(!left && !right) {
				model.addMove(l);
			} else {
				//TODO Careful not sure if deep copy or shallow copy in the recursion
				if (left) {
					ArrayList<Point2D> leftMove = new ArrayList<Point2D>();
					for(Point2D point : l) {
						leftMove.add((Point2D) point.clone());
					}
					leftMove.add(p);
					findMoveset(new Point((int)p.getX()-2,(int)p.getY()-2),leftMove);
				}
				if (right) {
					ArrayList<Point2D> rightMove = new ArrayList<Point2D>();
					for(Point2D point : l) {
						rightMove.add((Point2D) point.clone());
					}
					rightMove.add(p);
					findMoveset(new Point((int)p.getX()+2,(int)p.getY()-2),rightMove);
				}
			}
		} else {
			//Logic for player 2
		}
	}
	
	public boolean leftPossible(Point2D p) {
		if (model.getTurn() == 1) {
			//Logic for player 1
			if (p.getX() <= 1 || p.getY() <=1) {
				return false;
			} else{
				Piece tmp = model.getPieceAt((int)p.getX()-1, (int)p.getY()-1);
				if (tmp == null) {
					return false;
				} else if (tmp.getOwner() == 1){
					return false;
				} else {
					tmp = model.getPieceAt((int)p.getX()-2, (int)p.getY()-2);
					if (tmp == null) {
						return true;
					} else {
						return false;
					}
				}
			}
				
		} else {
			//Logic for player 2
			return false;
		}
	}
	
	public boolean rightPossible(Point2D p) {
		if (model.getTurn() == 1) {
			//Logic for player 1
			if (p.getX() >= 6 || p.getY() <=1) {
				return false;
			} else{
				Piece tmp = model.getPieceAt((int)p.getX()+1, (int)p.getY()-1);
				if (tmp == null) {
					return false;
				} else if (tmp.getOwner() == 1){
					return false;
				} else {
					tmp = model.getPieceAt((int)p.getX()+2, (int)p.getY()-2);
					if (tmp == null) {
						return true;
					} else {
						return false;
					}
				}
			}
				
		} else {
			//Logic for player 2
			return false;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
