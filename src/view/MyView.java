package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
		
		Dimension s = model.getSelected();
		if(s != null) {
			g2d.setColor(Color.YELLOW);
			g2d.setStroke(new BasicStroke(5));
			g2d.drawRect((int)(s.getWidth() * squareSize), (int)(s.getHeight() * squareSize), (int)squareSize, (int)squareSize);
			
			highLightMoves(g2d,squareSize,model.getTurn());
			

		}
	}
	
	public void highLightMoves(Graphics2D g2d, double squareSize, int turn) {
		g2d.setColor(Color.GREEN);
		
		if(turn == 1) {
			Dimension upLeft = new Dimension(model.getSelected().width - 1, model.getSelected().height -1);
			Dimension upRight = new Dimension(model.getSelected().width + 1, model.getSelected().height -1);
			
			if(upLeft.width >= 0 && upLeft.height >=0) {
				Piece p = model.getPieceAt(upLeft.width, upLeft.height);
				if (p == null) {
					g2d.drawRect((int)(upLeft.getWidth() * squareSize), (int)(upLeft.getHeight() * squareSize), (int)squareSize, (int)squareSize);
				}
			}
			
			if(upRight.height >=0 && upRight.width < 8) {
				Piece p = model.getPieceAt(upRight.width, upRight.height);
				if (p == null) {
					g2d.drawRect((int)(upRight.getWidth() * squareSize), (int)(upRight.getHeight() * squareSize), (int)squareSize, (int)squareSize);
				}
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
						model.setSelected(new Dimension(x,y));
					}
				}
			}
		} else {
			Dimension upLeft = new Dimension(model.getSelected().width - 1, model.getSelected().height -1);
			Dimension upRight = new Dimension(model.getSelected().width + 1, model.getSelected().height -1);
			
			if(upLeft.width >= 0 && upLeft.height >=0) {
				Piece p = model.getPieceAt(upLeft.width, upLeft.height);
				if (p == null) {
					if(x == upLeft.width && y == upLeft.height){
						model.setPiece(x, y, model.getPieceAt(model.getSelected().width, model.getSelected().height));
						model.setPiece(model.getSelected().width, model.getSelected().height, null);
					}
				}
			}
			if(upRight.height >=0 && upRight.width < 8) {
				Piece p = model.getPieceAt(upRight.width, upRight.height);
				if (p == null) {
					if(x == upRight.width && y == upRight.height){
						model.setPiece(x, y, model.getPieceAt(model.getSelected().width, model.getSelected().height));
						model.setPiece(model.getSelected().width, model.getSelected().height, null);
					}
				}
			} 
			model.setSelected(null);
			
			
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
