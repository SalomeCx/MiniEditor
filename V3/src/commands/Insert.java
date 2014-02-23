package commands;

import receiver.EditorEngine;

public class Insert implements Command {
	private EditorEngine engine;
	private String text;
	
	public Insert(EditorEngine e, String t) {
		this.engine = e;
		this.text = t;
	}
	
	public void setInsert(String s) {
		this.text = s;
	}
	
	public void execute() {
		engine.insert(text);
	}
}
