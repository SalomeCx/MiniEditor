package commands;

import receiver.EditorEngine;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 2.0
 *
 */
public class StartRecord implements Command {
	private EditorEngine engine;
	
	/**
	 * Constructeur.
	 * @param e l'EditorEngine sur lequel on travaille.
	 */
	public StartRecord(EditorEngine e) {
		this.engine = e;
	}
	
	/**
	 * Execute la commande correspondante dans EditorEngine.
	 */
	public void execute() {
		engine.startRecord();
	}
}
