package GUI;

public class Paste implements Command {
	private receiver.EditorEngine engine;
	
	public Paste(receiver.EditorEngine e) {
		this.engine = e;
	}
	
	public void execute() {
		engine.paste();
	}
}
