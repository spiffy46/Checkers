package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EventListener;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainFrame implements ComponentListener{
	JFrame frame;
	JLabel image;
	ImageIcon iIcon;
	
	public void createAndShowWindow() {
		frame = new JFrame("Checkers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(400,400));
		frame.setPreferredSize(new Dimension(800,800));
		
		//frame.add(new JLabel(new ImageIcon("lib/board.png")));
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("lib/board.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Image rImg = img.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
		
		image = new JLabel(new ImageIcon(rImg));
		frame.getContentPane().add(image, BorderLayout.CENTER);
		
		frame.addComponentListener(this);

				
		frame.pack();
		frame.setVisible(true);
	}

	public void componentResized(ComponentEvent e) {
		int s = 0;
		if(frame.getSize().height > frame.getSize().width){
			s = frame.getSize().height;
		}else{
			s = frame.getSize().width;
		}
		frame.setSize(new Dimension(s,s));
		
		//TODO Fix this so that the board resizes correctly
		Image rImg = im  g.getScaledInstance(s, s, Image.SCALE_SMOOTH);
		image = new JLabel(new ImageIcon(rImg));
		//frame.getContentPane().remove(0);
		frame.getContentPane().add(image, BorderLayout.CENTER);
	}

	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}
	public void componentHidden(ComponentEvent e) {}
}