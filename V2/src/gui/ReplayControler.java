package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import receiver.Buffer;

import commands.ReplayRecord;
import commands.Select;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 2.0
 *
 */
public class ReplayControler implements ActionListener{
	private Text frerot;
	private Buffer buffer;
	private Select select;
	private ReplayRecord replayRecord;
	
	/**
	 * Constructeur pour le controleur (actionListener) de la commande Replay.
	 * @param s la commande Select du meme EditorEngine
	 * @param t le Text pour pouvoir modifier son contenu.
	 * @param r la commande ReplayRecord.
	 * @param b le Buffer de l'EditorEngine pour recuperer son contenu.
	 */
	public ReplayControler(Select s, Text t, ReplayRecord r, Buffer b) {
		this.frerot = t;
		this.buffer = b ;
		this.select = s ;
		this.replayRecord = r ;
	}
	
	/**
	 * Determiner l'action lors d'un Replay.
	 */
	public void actionPerformed(ActionEvent e) {
		// Mettre a jour la selection
		int x = frerot.getSelectionStart();
		int y = frerot.getSelectionEnd();
		select.setSelect(x, (y - x));
		select.execute();

		replayRecord.execute();
		frerot.setText(buffer.toString());
	}
	
}
