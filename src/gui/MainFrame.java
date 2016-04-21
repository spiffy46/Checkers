package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EventListener;

import javax.imageio.ImageIO;
import javax.swing.*;

import checkersmodel.CheckersModel;
import view.MyView;

public class MainFrame implements ComponentListener, MouseListener{
	JFrame frame;
	MyCanvas canvas;
	MyView view;
	CheckersModel model;
	int xyDiff;
	
	
	public void createAndShowWindow(MyView v, CheckersModel m) {
		view = v;
		model = m;
		
		frame = new JFrame("Checkers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(400,400));
		//frame.setPreferredSize(new Dimension(800,800));
				
		canvas = new MyCanvas(v);
		canvas.setPreferredSize(new Dimension(800,800));
		canvas.setBackground(Color.gray);
		canvas.addMouseListener(this);

		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		
		frame.addComponentListener(this);

				
		frame.pack();
		frame.setVisible(true);
		
		xyDiff = frame.getHeight() - frame.getWidth();
	}

	public void componentResized(ComponentEvent e) {
		int s = frame.getSize().width;

		frame.setSize(s,s+xyDiff);
		model.setSize(s);
	}

	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}
	public void componentHidden(ComponentEvent e) {}
	
	public void setView(MyView v) {
		view = v;
	}
	
	public void setModel(CheckersModel m) {
		model = m;
	}

	public void refresh() {
		canvas.repaint();
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		view.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		view.mousePressed(e);
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