package GUI;

public class Copy implements Command {
	private receiver.EditorEngine engine;
	
	public Copy(receiver.EditorEngine e) {
		this.engine = e;
	}
	
	public void execute() {
		engine.copy();
	}
}
