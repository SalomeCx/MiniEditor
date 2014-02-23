package commands;

import receiver.EditorEngine;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 1.0
 *
 */
public class Delete implements Command {
	private EditorEngine engine;
	
	/**
	 * Constructeur.
	 * @param e l'EditorEngine sur lequel on travaille.
	 */
	public Delete(EditorEngine e) {
		this.engine = e;
	}
	
	/**
	 * Execute la commande correspondante dans EditorEngine.
	 */
	public void execute() {
		engine.delete();
	}
}