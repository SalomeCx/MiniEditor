package GUI;

import javax.swing.*;

public class Graphics{

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Window fenetre = new Window();
				fenetre.setVisible(true);
			}
		});
	}
}
