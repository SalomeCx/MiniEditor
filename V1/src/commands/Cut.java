package commands;

import receiver.EditorEngine;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 1.0
 *
 */
public class Cut implements Command {
	private EditorEngine engine;
	
	/**
	 * Constructeur.
	 * @param e l'EditorEngine sur lequel on travaille.
	 */
	public Cut(EditorEngine e) {
		this.engine = e;
	}
	
	/**
	 * Execute la commande correspondante dans EditorEngine.
	 */
	public void execute() {
		engine.cut();
	}
}