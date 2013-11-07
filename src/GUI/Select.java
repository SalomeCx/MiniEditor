package GUI;

public class Select implements Command {
	private receiver.EditorEngine engine;
	private int start;
	private int lenght;
	
	public Select(receiver.EditorEngine e, int s, int l) {
		this.engine = e;
		this.start = s;
		this.lenght = l;
	}
	
	public void execute() {
		engine.select(start, lenght);
	}
}