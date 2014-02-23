package commands;

import receiver.EditorEngine;

public class Delete implements Command {
	private EditorEngine engine;
	
	public Delete(EditorEngine e) {
		this.engine = e;
	}
	
	public void execute() {
		engine.delete();
	}
}