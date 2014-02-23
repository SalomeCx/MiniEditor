package commands;

import receiver.EditorEngine;

public class Select implements Command {
	private EditorEngine engine;
	private int start;
	private int lenght;
	
	public Select(EditorEngine e, int s, int l) {
		this.engine = e;
		this.start = s;
		this.lenght = l;
	}
	
	public void setSelect(int s, int l) {
		this.start = s;
		this.lenght = l;
	}
	
	public void execute() {
		engine.select(start, lenght);
	}
}