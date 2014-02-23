package commands;

import receiver.EditorEngine;

public class Redo implements Command {
	private EditorEngine engine;
	
	public Redo(EditorEngine e) {
		this.engine = e;
	}
	
	public void execute() {
		engine.redo();
	}
}
