package receiver;

public class Buffer {
	
	private StringBuilder sb;
	
	public Buffer() {
		this.sb = new StringBuilder();
	} 
	
	// Ceci est un pattern delegate
	public void append(String s) {
		sb.append(s);
	}

}
