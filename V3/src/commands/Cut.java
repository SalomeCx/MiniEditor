package commands;

import receiver.EditorEngine;

public class Cut implements Command {
	private EditorEngine engine;
	
	public Cut(EditorEngine e) {
		this.engine = e;
	}
	
	public void execute() {
		engine.cut();
	}
}