package receiver;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 1.0
 *
 */
public class Selection {
	private int start;
	private int length;
	
	/**
	 * Constructeur.
	 */
	public Selection() {
		this.start = 0;
		this.length = 0;
	}

	/**
	 * Getter de la valeur start.
	 * @return start.
	 */
	public int getStart() {
		return start; 
	}

	/**
	 * Setter de la valeur start.
	 * @param start la nouvelle valeur de start.
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * Getter de la valeur length.
	 * @return length.
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Setter de la valeur length.
	 * @param length la nouvelle valeur de length.
	 */
	public void setLength(int length) {
		this.length = length;
	}

	
}
