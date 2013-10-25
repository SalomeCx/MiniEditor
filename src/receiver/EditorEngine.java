package receiver;

/** 
 * @author Vincente NATTA & Salome COAVOUX
 * @version 0.1
 */

public class EditorEngine {
	
	public Buffer b;
	public Selection s;
	public Clipboard c;
	
	public EditorEngine() {
		this.b = new Buffer();
		this.s = new Selection();
		this.c = new Clipboard();
	}
	
	/**
	 * Copie le texte sélectionné dans le Clipboard.
	 */
	public void copy() {
		if (s.getLenght() != 0) {
			String tmp = b.substring(s.getStart(), s.getLenght() + s.getStart());
			c.setSc(tmp);
		}
	}
	
	/**
	 * Coupe le texte sélectionné: supprime le texte du Buffer, copie le texte dans le Clipboard.
	 */
	public void cut() {
		if (s.getLenght() != 0) {
			copy();
			delete();
		}
	}
	
	/**
	 * Colle le texte du Clipboard dans le Buffer au niveau de la Sélection.
	 */
	public void paste() {
		if (s.getLenght() > 0) {
			b.replace(s.getStart(), s.getStart() + s.getLenght(), c.getSc());
			s.setLenght(0);
			s.setStart(s.getStart() + c.getSc().length());
		}
		else {
			insert(c.getSc());
		}
	}
	
	/**
	 * Insère le texte passé en paramètre à l'endroit Séléction dans le Buffer.
	 * @param s le texte à insérer
	 */
	public void insert(String str) {
		if (s.getLenght() > 0) {
			delete();
		}
		b.insert(s.getStart(), str);
		s.setStart(str.length() + s.getStart());
	}
	
	/**
	 * Efface une sélection non vide uniquement.
	 */
	public void delete() {
		if (s.getLenght() > 0) {
			b.delete(s.getStart(), s.getStart() + s.getLenght());
		}
		s.setLenght(0);
	}
	
	/**
	 * Sélectionne le texte situé entre start et end
	 * @param start le début de la Sélection
	 * @param lenght la taille de la Sélection
	 */
	public void select(int start, int lenght) {
		// Cas avec des nombres négatifs.
		if (start < 0) {
			s.setStart(0);
			if (lenght < 0) {
				s.setLenght(0);
			}
			else if (start + lenght > b.lenght()) {
				s.setLenght(b.lenght());
			}
			// On choisi de compter lenght à partir de la valeur de start (< 0)
			else {
				s.setLenght(start + lenght);
			}
		}
		
		// Sélection à l'envers.
		else if (lenght < 0) {
			if (start + lenght < 0) {
				s.setLenght(start);
				s.setStart(0);
			}
			else {
				s.setStart(start+lenght);
				s.setLenght(-lenght);
			}
		}
		
		// Start en dehors du buffer (à droite)
		else if (start > b.lenght()) {
			s.setStart(b.lenght());
			s.setLenght(0);
		}
		
		// Sélection trop grande.
		else if (start + lenght > b.lenght()) {
			s.setStart(start);
			s.setLenght(b.lenght()-start);
		}

		// Cas oublié?
		else {
			s.setStart(start);
			s.setLenght(lenght);
		}
	}
	
}
