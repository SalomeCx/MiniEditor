package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import receiver.Buffer;

import commands.Redo;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 3.0
 *
 */
public class RedoControler implements ActionListener{
	private Text frerot;
	private Buffer buffer;
	private Redo redo;
	
	/**
	 * Constructeur pour le controleur (actionListener) de la commande Redo.
	 * @param t le Text pour pouvoir modifier son contenu.
	 * @param r la commande Redo.
	 * @param b le Buffer de l'EditorEngine pour recuperer son contenu.
	 */
	public RedoControler(Text t, Redo r, Buffer b) {
		this.frerot = t;
		this.buffer = b ;
		this.redo = r ;
	}
	
	/**
	 * Determiner l'action lors d'un Redo.
	 */
	public void actionPerformed(ActionEvent e) {
		redo.execute();
		frerot.setText(buffer.toString());
	}
	
}
