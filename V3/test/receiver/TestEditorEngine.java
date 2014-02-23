package receiver;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestEditorEngine {
	
	// private, sinon on se fait incendier a la correction.
	private EditorEngine e;

	
	@Before
	public void init() {
		e = new EditorEngine();
	}
	
	@Test
	public void testCopy() {
		Buffer buf = e.getBuffer();
		Clipboard clip = e.getClipboard();
		buf.append("good morning");
		
		// Le clipboard de depart est bien vide
		e.copy();
		assertEquals("Copie vide de depart", clip.getSc(), null);
		
		// Copie normale
		e.select(0, 4);
		e.copy();
		assertEquals("Non-copie", clip.getSc(), "good");
		assertEquals("Non-copie", buf.toString(), "good morning");
		
		// Copie vide: on ne change pas la valeur precedente du clipboard
		e.select(4, 0);
		e.copy();
		assertEquals("Copie vide", clip.getSc(), "good");
			
	}

	@Test
	public void testCut() {
		Buffer buf = e.getBuffer();
		Clipboard clip = e.getClipboard();
		
		buf.append("are you my mommy");
		
		// Coupe vide
		e.select(0, 0);
		e.cut();
		assertEquals("Coupe vide de depart", clip.getSc(), null);
		assertEquals("Coupe vide de depart", buf.toString(), "are you my mommy");
		
		// Coupe normale
		e.select(0, 11);
		e.cut();
		assertEquals("Coupe normale", clip.getSc(), "are you my ");
		assertEquals("Coupe normale", buf.toString(), "mommy");
		
		// Coupe vide: on ne change pas la valeur precedente du clipboard (dixit: eclipse, gedit & sublime text)
		e.select(4, 0);
		e.cut();
		assertEquals("Coupe vide", buf.toString(), "mommy");
		assertEquals("Coupe vide", clip.getSc(), "are you my ");	

	}

	@Test
	public void testPaste() {
		Buffer buf = e.getBuffer();
		Clipboard clip = e.getClipboard();
		
		buf.append("koala girafe mais pas demain");
		
		// Coller sans ecraser
		e.select(0, 6);
		e.cut();
		e.select(7, 0);
		e.paste();
		assertEquals("Coller sans ecraser", buf.toString(), "girafe koala mais pas demain");
		assertEquals("Coller: clipboard?", clip.getSc(), "koala ");
		
		// Coller en ecrasant
		e.select(13, 5);
		e.paste();
		assertEquals("Coller et ecraser", buf.toString(), "girafe koala koala pas demain");
	}

	@Test
	public void testInsert() {
		Buffer buf = e.getBuffer();
		buf.append("tests Insertion");
		
		// Insertion sans selection = au debut du buffer
		e.select(0, 0);
		e.insert("4");
		assertEquals("Insertion sans selection", buf.toString(), "4tests Insertion");
		
		// Insertion en supprimant du texte au debut
		e.select(0, 6);
		e.insert("42");
		assertEquals("Selection depuis le debut", buf.toString(), "42 Insertion");
		
		// Insertion en supprimant du texte au milieu
		e.select(3, 2);
		e.insert("ex");
		assertEquals("Selection dans le milieu", buf.toString(), "42 exsertion");
		
	}

	@Test
	public void testSelect() {
		Buffer buf = e.getBuffer();
		Selection sel = e.getSelection();
		
		// La selection ne sort pas de la taille du texte
		e.select(12,15);
		assertEquals("Out of bounds", 0, sel.getLength());
		assertEquals("Out of bounds", 0, sel.getStart());
		
		// Cas normal
		buf.append("hello world");
		e.select(1, 4);
		assertEquals("Not good number", 1, sel.getStart());
		assertEquals("Not good number", 4, sel.getLength());
		
		// Mauvais indice de depart
		e.select(-8, 9);
		assertEquals("Negative number", 0, sel.getStart());
		
		// Selection a l'envers
		e.select(5, -3);
		assertEquals("Wrong start", 2, sel.getStart());
		
		// Selection nulle
		e.select(3, 0);
		assertEquals("Not null selection", 0, sel.getLength());
		
	}

}