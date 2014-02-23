package gui;

import javax.swing.*;
import receiver.*;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 3.0
 *
 */
public class Graphics{

	/**
	 * Classe principale a lancer pour lancer l'IHM.
	 * @param args aucun.
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				EditorEngine engine = new EditorEngine();
				Window fenetre = new Window(engine);
				fenetre.setVisible(true);
				
			}
		});
	}
}
