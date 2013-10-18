package receiver;

/** 
 * @author Vincente NATTA & Salome COAVOUX
 * @version 0.1
 */

public class EditorEngine {
	
	public Buffer b;
	public Selection s;
	public Clipboard c;
	
	public EditorEngine()
	{
		this.b = new Buffer();
		this.s = new Selection();
		this.c = new Clipboard();
	}
	
	/**
	 * Copie le texte sélectionné dans le Clipboard.
	 */
	public void copy()
	{
		try {
			throw new Exception("to do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Coupe le texte sélectionné: supprime le texte du Buffer, copie le texte dans le Clipboard.
	 */
	public void cut()
	{
		try {
			throw new Exception("to do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Colle le texte du Clipboard dans le Buffer au niveau de la Sélection.
	 */
	public void paste()
	{
		try {
			throw new Exception("to do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Insère le texte passé en paramètre à l'endroit Séléction dans le Buffer.
	 * @param s le texte à insérer
	 */
	public void insert(String s)
	{
		try {
			throw new Exception("to do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sélectionne le texte situé entre start et end
	 * @param start le début de la Sélection
	 * @param lenght la taille de la Sélection
	 */
	public void select(int start, int lenght)
	{
		try {
			throw new Exception("to do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
