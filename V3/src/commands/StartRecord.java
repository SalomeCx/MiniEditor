package commands;

import receiver.EditorEngine;

public class StartRecord implements Command {
	private EditorEngine engine;
	
	public StartRecord(EditorEngine e) {
		this.engine = e;
	}
	
	public void execute() {
		engine.startRecord();
	}
}
