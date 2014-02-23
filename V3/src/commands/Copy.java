package commands;

import receiver.EditorEngine;

public class Copy implements Command {
	private EditorEngine engine;
	
	public Copy(EditorEngine e) {
		this.engine = e;
	}
	
	public void execute() {
		engine.copy();
	}
}
