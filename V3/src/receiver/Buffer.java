package receiver;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 3.0
 *
 */
public class Buffer {
	
	private StringBuilder sb;
	
	/**
	 * Constructeur.
	 */
	public Buffer() {
		this.sb = new StringBuilder();
	} 
	
	/**
	 * La taille du champ sb de Buffer.
	 * @return la taille.
	 */
	public int length()	{
		return sb.length();
	}
	
	/**
	 * Concatener une String a la fin de sb.
	 * @param s la String a concatener.
	 */
	public void append(String s) {
		sb.append(s);
	}
	
	/**
	 * Supprimer une partie de la chaine sb.
	 * @param start le debut de la chaine a supprimer.
	 * @param end la fin de la chaine a supprimer.
	 */
	public void delete(int start, int end) {
		sb.delete(start, end);
	}
	
	/**
	 * Inserer une String dans sb a un indice donne.
	 * @param start l'indice ou inserer la String.
	 * @param s la String a inserer.
	 */
	public void insert(int start, String s) {
		sb.insert(start, s);
	}
	
	/**
	 * Recuperer une sous-chaine de sb.
	 * @param start le debut de la sous-chaine a retourner.
	 * @param end la fin de la sous-chaine a retourner.
	 * @return la sous-chaine en question.
	 */
	public String substring(int start, int end) {
		return sb.substring(start, end);
	}
	
	/**
	 * Remplacer une sous-chaine par une autre String.
	 * @param start le debut de la sous-chaine a remplacer.
	 * @param end la fin de la sous-chaine a remplacer.
	 * @param str La String qui va remplacer la sous-chaine.
	 */
	public void replace(int start, int end, String str) {
		sb.replace(start, end, str);
	}
	
	/**
	 * Recuperer le contenu de sb.
	 * @return sb sous forme de String.
	 */
	public String toString() {
		return sb.toString();
	}

	/**
	 * Un clone du contenu du Buffer.
	 * utile pour le Memento Pattern.
	 * Ici, le Buffer, est l'Originator.
	 * @return Une String, copie du contenu du Buffer.
	 */
	public String cloneSB() {
		String str = new String(this.toString());
		return str;
	}
}
