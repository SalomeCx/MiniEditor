package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextArea;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 2.0
 *
 */
public class Text extends JTextArea implements KeyListener{	   
  
	private Window papa;
	
	/**
	 * Constructeur pour la zone de texte que l'on utilise.
	 * @param w la fenetre dans laquelle apparaitra le texte.
	 */
	public Text(Window w) {
    	this.setSize(950, 400);
        this.addKeyListener(this);
        this.papa = w;
    }
    
	/**
     * Determiner les actions du KeyListener.
     */
    @Override 
    public void keyPressed(KeyEvent e)
    {
    	int n = e.getKeyCode();

    	// Backspace
    	if (n == 8) {
    		int x = this.getSelectionStart();
    		int y = this.getSelectionEnd();
    		
    		// Si la selection est vide
    		if (x == y) {
    			papa.getSelect().setSelect((x - 1), 1);
    		}
    		else {
    			papa.getSelect().setSelect(x, (y - x));
    		}
    		papa.getSelect().execute();
    		
    		papa.getDelete().execute();
    	}
    	// Pour une certaine raison, ne semble pas fonctionner avec alt gr.
    	// Si la touche est un caractere ascii (= imprimable)
    	else if (!(e.isActionKey()) && ((n < 16) || (n > 18)) && !e.isControlDown()) {
    		String s = Character.toString(e.getKeyChar());
    		
    		int x = this.getSelectionStart();
    		int y = this.getSelectionEnd();
    		
    		papa.getSelect().setSelect(x, (y - x));
    		papa.getSelect().execute();
    		
    		papa.getInsert().setInsert(s);
    		papa.getInsert().execute();
    	}
    }
    
    // On n'a besoin d'ecouter qu'une seule des trois actions differentes.
    @Override
    public void keyReleased(KeyEvent e)
    {
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
    }
}