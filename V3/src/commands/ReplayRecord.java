package commands;

import receiver.EditorEngine;

public class ReplayRecord implements Command {
	private EditorEngine engine;
	
	public ReplayRecord(EditorEngine e) {
		this.engine = e;
	}
	
	public void execute() {
		engine.replayRecord();
	}
}
