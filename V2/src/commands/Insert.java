package commands;

import receiver.EditorEngine;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 2.0
 *
 */
public class Insert implements Command {
	private EditorEngine engine;
	private String text;
	
	/**
	 * Constructeur.
	 * @param e l'EditorEngine sur lequel on travaille.
	 */
	public Insert(EditorEngine e, String t) {
		this.engine = e;
		this.text = t;
	}
	
	/**
	 * Setter pour le texte a inserer.
	 * @param s le texte a inserer.
	 */
	public void setInsert(String s) {
		this.text = s;
	}
	
	/**
	 * Execute la commande correspondante dans EditorEngine.
	 */
	public void execute() {
		engine.insert(text);
	}
}
