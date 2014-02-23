package receiver;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 3.0
 *
 */
public class Clipboard {

	private String sc;

	/**
	 * Constructeur.
	 */
	public Clipboard() {
		this.sc = null;
	}

	/**
	 * Getter.
	 * @return le champ sc du Clipboard.
	 */
	public String getSc() {
		return sc;
	}

	/**
	 * Setter.
	 * @param s la nouvelle valeur de sc.
	 */
	public void setSc(String s) {
		sc = s;
	}
}