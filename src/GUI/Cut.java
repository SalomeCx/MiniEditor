package GUI;

public class Cut implements Command {
	private receiver.EditorEngine engine;
	
	public Cut(receiver.EditorEngine e) {
		this.engine = e;
	}
	
	public void execute() {
		engine.cut();
	}
}