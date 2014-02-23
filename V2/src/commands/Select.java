package commands;

import receiver.EditorEngine;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 2.0
 *
 */
public class Select implements Command {
	private EditorEngine engine;
	private int start;
	private int lenght;
	
	/**
	 * Constructeur.
	 * @param e l'EditorEngine sur lequel on travaille.
	 */
	public Select(EditorEngine e, int s, int l) {
		this.engine = e;
		this.start = s;
		this.lenght = l;
	}
	
	/**
	 * Setter pour le debut et la taille de la selection.
	 * @param s debut de la selection.
	 * @param l taille de la selection.
	 */
	public void setSelect(int s, int l) {
		this.start = s;
		this.lenght = l;
	}
	
	/**
	 * Execute la commande correspondante dans EditorEngine.
	 */
	public void execute() {
		engine.select(start, lenght);
	}
}