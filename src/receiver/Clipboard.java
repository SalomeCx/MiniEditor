package receiver;

public class Clipboard {

	private String sc;

	public Clipboard() {
		this.sc = null;
	}

	// Ceci aurait aussi du etre un pattern delegate, mais String est cool en Java, donc pas besoin
	public String getSc() {
		return sc;
	}

	public void setSc(String s) {
		sc = s;
	}
}