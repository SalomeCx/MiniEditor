package commands;

import receiver.EditorEngine;

public class StopRecord implements Command {
	private EditorEngine engine;
	
	public StopRecord(EditorEngine e) {
		this.engine = e;
	}
	
	public void execute() {
		engine.stopRecord();
	}
}
