package checkers;

import gui.MainFrame;

public class main {
	
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame window = new MainFrame();
				window.createAndShowWindow();
			}
 		});
	}
	
}