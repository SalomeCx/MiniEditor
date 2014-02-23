package commands;

import receiver.EditorEngine;

public class Paste implements Command {
	private EditorEngine engine;
	
	public Paste(EditorEngine e) {
		this.engine = e;
	}
	
	public void execute() {
		engine.paste();
	}
}
