package receiver;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 3.0
 *
 */
public class EditorEngine {
	
	private Buffer b;
	private Selection s;
	private Clipboard c;
	private Record r;
	private Caretaker care;
	private int index;
	private int indexMax;
	
	/**
	 * Constructeur.
	 */
	public EditorEngine() {
		this.b = new Buffer();
		this.s = new Selection();
		this.c = new Clipboard();
		this.r = new Record();
		this.care = new Caretaker();
		// Initialiser le Memento a un Buffer vide.
		this.care.addMemento(b.cloneSB(), 0);
		this.index = 0;
		this.indexMax = 0;
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
	 * Getter du Record de l'EditorEngine.
	 * @return le Record.
	 */
	public Record getRecord() {
		return this.r;
	}
	
	/**
	 * Copie le texte selectionne dans le Clipboard.
	 * On copie uniquement une selection non vide:
	 * une selection vide ne modifie pas le Clipboard.
	 */
	public void copy() {
		// Enregistrer
		if (r.getIsRec()){
			r.addAll(s, 1, null); // 1 = copier
		}
		
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
		// Enregistrer
		if (r.getIsRec()) {
			r.addAll(s, 2, null); // 2 = couper
		}
		
		if (s.getLength() != 0) {
			copy();
			basicDelete();
		}
		
		System.out.println("Clipboard: " + c.getSc());
		System.out.println("Buffer: " + b.toString());

		incIndexes();
		care.addMemento(b.cloneSB(), index);
		
        
	}
	
	/**
	 * Colle le texte du Clipboard dans le Buffer au niveau de la Selection.
	 */
	public void paste() {
		// Enregistrer
		if (r.getIsRec()) {
			r.addAll(s, 3, null); // 3 = coller
		}
		
		if (s.getLength() > 0) {
			basicDelete();
		}
		if (c.getSc() != null) {
			basicInsert(c.getSc());
		}
		
		incIndexes();
		care.addMemento(b.cloneSB(), index);
		
		System.out.println("Clipboard: " + c.getSc());
		System.out.println("Buffer: " + b.toString());
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
		// Enregistrer
		if (r.getIsRec()){
			r.addAll(s, 4, str); // 4 = inserer
		}
		
		basicInsert(str);
	
		System.out.println("Buffer: " + b.toString());
		
		incIndexes();
		care.addMemento(b.cloneSB(), index);
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
		// Enregistrer
		if (r.getIsRec()) {
			r.addAll(s, 5, null); // 5 = effacer
		}
		
		basicDelete();

		System.out.println("Buffer: " + b.toString());
		
		incIndexes();
		care.addMemento(b.cloneSB(), index);
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
			// On choisit de compter length a partir de la valeur de start (< 0)
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
	
	/**
	 * Commencer a enregistrer.
	 * On doit reinitialiser l'objet record.
	 */
	public void startRecord() {
		r.initRecord();
		r.setRecord(true);
		Selection sel = new Selection();
		sel.setLength(s.getLength());
		sel.setStart(s.getStart());
		r.setDebut(sel);
	}
	
	/**
	 * Arreter l'enregistrement.
	 */
	public void stopRecord() {
		r.setRecord(false);
	}
	
	/**
	 * Rejouer l'enregistrement.
	 * On n'autorise pas a rejouer et a enregistrer en meme temps.
	 */
	public void replayRecord() {
		if (!r.getIsRec()) {
			int translate = r.getDebut().getStart();
			for (int i = 0; i < r.size(); i++) {
				// La selection du replay est la selection enregistree
				// translatee de la valeur du debut.
				Selection tmp = r.getSelects(i);
				select(tmp.getStart() + translate, tmp.getLength());
				
				switch (r.getCommands(i)) {
					case 1:
						copy();
						break;
					case 2:
						cut();
						break;
					case 3:
						paste();
						break;
					case 4:
						insert(r.getInserts(i));
						break;
					case 5:
						delete();
						break;
					case 6:
						undo();
						break;
					case 7:
						redo();
						break;
					default:
						System.err.println("NOT SUPPOSED TO HAPPEN!");
						break;
				}
			}
		}
	}
	
	/**
	 * Permet d'incrementer l'indice courant et l'indice max
	 * du Memento lors de chaque action. Si les deux indices sont differents,
	 * cela signifie que l'on s'apprete a faire une action apres un ou plusieurs undo.
	 * On ne doit donc plus permettre les redo pour l'instant, c'est-a-dire 
	 * que l'on doit ecraser les valeurs dans le Memento pour les remplacer par les nouvelles actions.
	 */
	private void incIndexes() {
		if (this.index == this.indexMax) {
			this.index++;
			this.indexMax++;
		}
		else {
			this.index++;
			this.indexMax = this.index;
		}
	}
	
	/**
	 * Defaire une action. On recupere la valeur du Memento d'indice
	 * courant pour remplacer par sa valeur le contenu du Buffer.
	 */
	public void undo() {
		if (r.getIsRec()) {
			r.addAll(null, 6, null); // 6 = defaire
		}
		
		String str = "";
		if (this.index != 0) {
			this.index--;
			str = (String) care.getMemento(this.index);
			
		}
		if (str != null) {
			b.replace(0, b.length(), str);
		}
	}
	
	/**
	 * Refaire une action. On recupere la valeur du Memento d'indice
	 * courant pour remplacer par sa valeur le contenu du Buffer.
	 */
	public void redo() {
		if (r.getIsRec()) {
			r.addAll(null, 7, null); // 7 = refaire
		}
		
		String str = null;
		if (this.index != this.indexMax) {
			this.index++;
			str = (String) care.getMemento(this.index);
		}
		if (str != null) {
			b.replace(0, b.length(), str);
		}
	}	
}
