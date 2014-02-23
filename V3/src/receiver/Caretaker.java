package receiver;

import java.util.ArrayList;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 3.0
 *
 */
public class Caretaker {
	private ArrayList<Object> savedStates = new ArrayList<Object>();
	
	/**
	 * Ajouter un Memento, c'est-a-dire sauvegarder un nouvel etat.
	 * @param m l'etat a sauvegarder
	 * @param index l'endroit ou le sauvegarder.
	 * Utile dans le cas d'un Undo suivi de l'utilisation d'une commande:
	 * on ne doit alors pas pouvoir faire un Redo.
	 */
    public void addMemento(Object m, int index) { 
    	savedStates.add(index, m); 
    }
    
    /**
     * Recuperer un Memento precedent, c'est-a-dire utiliser la commande Undo.
     * @param index l'index du Memento a retourner.
     * @return le Memento en question.
     */
    public Object getMemento(int index) { 
    	return savedStates.get(index); 
    }
}
