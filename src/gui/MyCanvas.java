package gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;

import view.MyView;

public class MyCanvas extends Canvas{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MyView view;
	
	public MyCanvas(MyView v) {
		view = v;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2;
		g2 = (Graphics2D) g;
		view.refreshView(g2);		
	}
}
