package GUI;

public class Insert implements Command {
	private receiver.EditorEngine engine;
	private String text;
	
	public Insert(receiver.EditorEngine e, String t) {
		this.engine = e;
		this.text = t;
	}
	
	public void execute() {
		engine.insert(text);
	}
}
