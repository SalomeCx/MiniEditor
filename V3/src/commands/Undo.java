package commands;

import receiver.EditorEngine;

public class Undo implements Command {
	private EditorEngine engine;
	
	public Undo(EditorEngine e) {
		this.engine = e;
	}
	
	public void execute() {
		engine.undo();
	}
}

