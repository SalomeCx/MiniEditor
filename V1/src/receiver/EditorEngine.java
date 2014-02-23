package receiver;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 1.0
 *
 */
public class EditorEngine {
	
	private Buffer b;
	private Selection s;
	private Clipboard c;
	
	/**
	 * Constructeur.
	 */
	public EditorEngine() {
		this.b = new Buffer();
		this.s = new Selection();
		this.c = new Clipboard();
	}
	
	/**
	 * Getter du Buffer de l'EditorEngine.
	 * @return le Buffer.
	 */
	public Buffer getBuffer() {
		return this.b;
	}
	
	/**
	 * Getter de la Selection de l'EditorEngine
	 * @return la Selection.
	 */
	public Selection getSelection() {
		return this.s;
	}
	
	/**
	 * Getter du Clipboard de l'EditorEngine.
	 * @return le Clipboard.
	 */
	public Clipboard getClipboard() {
		return this.c;
	}
	
	/**
	 * Copie le texte selectionne dans le Clipboard.
	 * On copie uniquement une selection non vide:
	 * une selection vide ne modifie pas le Clipboard.
	 */
	public void copy() {
		if (s.getLength() != 0) {
			String tmp = b.substring(s.getStart(), s.getLength() + s.getStart());
			c.setSc(tmp);
		}
		System.out.println("Clipboard: " + c.getSc());
	}
	
	/**
	 * Coupe le texte selectionne: supprime le texte du Buffer, copie le texte dans le Clipboard.
	 */
	public void cut() {
		if (s.getLength() != 0) {
			copy();
			basicDelete();
		}
		
		System.out.println("Buffer: " + b.toString());
		System.out.println("Clipboard: " + c.getSc());
	}
	
	/**
	 * Colle le texte du Clipboard dans le Buffer au niveau de la Selection.
	 */
	public void paste() {
		if (s.getLength() > 0) {
			basicDelete();
		}
		if (c.getSc() != null) {
			basicInsert(c.getSc());
		}
		
		System.out.println("Buffer: " + b.toString());
		System.out.println("Clipboard: " + c.getSc());
	}
	
	/**
	 * Insere le texte passe en parametre a l'endroit Selection dans le Buffer.
	 * Fonction de base appele par les commandes, y compris insert.
	 * @param str le texte a inserer
	 */
	private void basicInsert(String str) {		
		if (s.getLength() > 0) {
			delete();
		}
		b.insert(s.getStart(), str);
		s.setStart(str.length() + s.getStart());
	}
	
	/**
	 * Inserer le texte en parametre.
	 * @param str la String a inserer.
	 */
	public void insert(String str) {
		basicInsert(str);
		
		System.out.println("Buffer: " + b.toString());
	}
	
	/**
	 * Effacer la Selection courante.
	 * Fonction de base appele par les commandes, y compris delete.
	 */
	private void basicDelete() {
		if (s.getLength() > 0) {
			b.delete(s.getStart(), s.getStart() + s.getLength());
		}
		s.setLength(0);
	}
	
	/**
	 * Efface une selection non vide uniquement.
	 */
	public void delete() {		
		basicDelete();
		
		System.out.println("Buffer: " + b.toString());
	}
	
	/**
	 * Selectionne le texte situe entre start et end
	 * @param start le debut de la Selection
	 * @param length la taille de la Selection
	 */
	public void select(int start, int length) {
		// Cas avec des nombres negatifs.
		if (start < 0) {
			s.setStart(0);
			if (length < 0) {
				s.setLength(0);
			}
			else if (start + length > b.length()) {
				s.setLength(b.length());
			}
			// On choisi de compter length a partir de la valeur de start (< 0)
			else {
				s.setLength(start + length);
			}
		}
		
		// Selection a l'envers.
		else if (length < 0) {
			if (start + length < 0) {
				s.setLength(start);
				s.setStart(0);
			}
			else {
				s.setStart(start+length);
				s.setLength(-length);
			}
		}
		
		// Start en dehors du buffer (a droite)
		else if (start > b.length()) {
			s.setStart(b.length());
			s.setLength(0);
		}
		
		// Selection trop grande.
		else if (start + length > b.length()) {
			s.setStart(start);
			s.setLength(b.length()-start);
		}

		// Cas oublie?
		else {
			s.setStart(start);
			s.setLength(length);
		}
	}
}
