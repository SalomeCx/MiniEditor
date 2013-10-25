package receiver;

public class Buffer {
	
	private StringBuilder sb;
	
	public Buffer() {
		this.sb = new StringBuilder();
	} 
	
	public int lenght()	{
		return sb.length();
	}
	
	// Ceci est un pattern delegate
	public void append(String s) {
		sb.append(s);
	}
	
	public void delete(int start, int end) {
		sb.delete(start, end);
	}
	
	public void insert(int start, String s) {
		sb.insert(start, s);
	}
	
	public String substring(int start, int end) {
		return sb.substring(start, end);
	}
	
	public void replace(int start, int end, String str) {
		sb.replace(start, end, str);
	}
	
	public String toString() {
		return sb.toString();
	}
}
