package checkers;

import checkersmodel.CheckersModel;
import gui.MainFrame;
import view.MyView;

public class main {
	
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame window = new MainFrame();
				MyView view = new MyView();
				CheckersModel model = new CheckersModel();
				view.setModel(model);
				view.setFrame(window);
				window.createAndShowWindow(view,model);
			}
 		});
	}
	
}