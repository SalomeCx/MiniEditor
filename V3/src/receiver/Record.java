package receiver;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 3.0
 *
 */
public class Record {
	// On garde la liste des selections successives.
	private List<Selection> selects;
	// On garde la liste des commandes.
	private List<Integer> commands;
	// S'il y a un insert, on garde le String insere.
	private List<String> inserts;
	// On garde la selection de debut pour translater quand on veut rejouer. 
	private Selection debut;
	// On verifie si on est ou non en train d'enregistrer.
	private boolean isRec;
	
	/**
	 * Constructeur.
	 */
	public Record() {
		this.selects = new ArrayList<Selection>();
		this.commands = new ArrayList<Integer>();
		this.inserts = new ArrayList<String>();
		this.debut = null;
		this.isRec = false;
	}
	
	/**
	 * Ajoute une commande: on ajoute une selection, un identificateur de
	 * commande et une eventuelle String (pour la commande insert).
	 * @param s la Selection au moment du Record. 
	 * @param i l'identificateur de la commande (explicite dans EditorEngine).
	 * @param str la String a sauvegarder pour la commande Insert.
	 */
	public void addAll(Selection s, Integer i, String str) {
		selects.add(s);
		commands.add(i);
		inserts.add(str);
	}
	
	/**
	 * Reinitialiser un Record, lorsque l'on commence un nouvel enregistrement.
	 */
	public void initRecord() {
		this.selects.clear();
		this.commands.clear();
		this.inserts.clear();
		this.debut = null;
		this.isRec = false;
	}
	
	/**
	 * Setter de la Selection de debut.
	 * @param s la Selection a appliquer.
	 */
	public void setDebut(Selection s) {
		this.debut = s;
	}
	
	/**
	 * Commencer ou stopper le Record.
	 * @param b un booleen, true si on commence l'enrestrement, false si on le stoppe.
	 */
	public void setRecord(boolean b) {
		this.isRec = b;
	}
	
	/**
	 * Getter du booleen isRec.
	 * @return le booleen en question.
	 */
	public boolean getIsRec() {
		return this.isRec;
	}
	
	/**
	 * Getter d'une des Selections enregistrees.
	 * utile pour rejouer, lorsque l'on parcours la liste.
	 * @param index l'indice de la Selection voulue.
	 * @return la Selection demandee.
	 */
	public Selection getSelects(int index) {
		return this.selects.get(index);
	}
	
	/**
	 * Getter d'un des identifiants des commandes enregistres.
	 * utile pour rejouer, lorsque l'on parcours la liste.
	 * @param index l'indice de l'identifiant voulu.
	 * @return l'identifiant demande.
	 */
	public Integer getCommands(int index) {
		return this.commands.get(index);
	}
	
	/**
	 * Getter d'une des Strings enregistrees.
	 * utile pour rejouer, lorsque l'on parcours la liste.
	 * @param index l'indice de la String voulue.
	 * @return la String demandee.
	 */
	public String getInserts(int index) {
		return this.inserts.get(index);
	}
	
	/**
	 * Recuperer la taille des listes. (Toutes ont la meme taille.)
	 * @return la taille demandee.
	 */
	public int size() {
		return this.commands.size();
	}

	/**
	 * Getter de la Selection de debut.
	 * @return la Selection en question.
	 */
	public Selection getDebut() {
		return this.debut;
	}
}
