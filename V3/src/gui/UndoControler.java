package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import receiver.Buffer;

import commands.Undo;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 3.0
 *
 */
public class UndoControler implements ActionListener{
	private Text frerot;
	private Buffer buffer;
	private Undo undo;
	
	/**
	 * Constructeur pour le controleur (actionListener) de la commande Undo.
	 * @param t le Text pour pouvoir modifier son contenu.
	 * @param u la commande Undo.
	 * @param b le Buffer de l'EditorEngine pour recuperer son contenu.
	 */
	public UndoControler(Text t, Undo u, Buffer b) {
		this.frerot = t;
		this.buffer = b ;
		this.undo = u ;
	}
	
	/**
	 * Determiner l'action lors d'un Undo.
	 */
	public void actionPerformed(ActionEvent e) {
		undo.execute();
		frerot.setText(buffer.toString());
	}
	
}
