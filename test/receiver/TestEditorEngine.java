package receiver;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestEditorEngine {

	
	// private, sinon on se fait incendier à la correction.
	private EditorEngine e;

	
	@Before
	public void init() {
			e = new EditorEngine();
	}
	
	@Test
	public void testCopy() {
		e.b.append("good morning");
		
		// Le clipboard de depart est bien vide
		e.copy();
		assertEquals("Copie vide de depart", e.c, "");
		
		// Copie normale
		e.select(0, 4);
		e.copy();
		assertEquals("Non-copie", e.c, "good");
		assertEquals("Non-copie", e.b, "good morning");
		
		// Copie vide: on ne change pas la valeur precedente du clipboard
		e.select(4, 0);
		e.copy();
		assertEquals("Copie vide", e.c, "good");
		
		
	}

	@Test
	public void testCut() {
		e.b.append("are you my mommy");
		
		// Coupe vide
		e.cut();
		assertEquals("Coupe vide de depart", e.c, "");
		assertEquals("Coupe vide de depart", e.b, "are you my mommy");
		
		// Coupe normale
		e.select(0, 11);
		e.cut();
		assertEquals("Coupe normale", e.c, "are you my ");
		assertEquals("Coupe normale", e.b, "mommy");
		
		// Coupe vide: on ne change pas la valeur precedente du clipboard (dixit: eclipse, gedit & sublime text)
		e.select(4, 0);
		e.cut();
		assertEquals("Coupe vide", e.b, "mommy");
		assertEquals("Coupe vide", e.c, "are you my ");
		

	}

	@Test
	public void testPaste() {
		e.b.append("koala girafe mais pas demain");
		
		// Coller sans ecraser
		e.select(0, 6);
		e.cut();
		e.select(7, 0);
		e.paste();
		assertEquals("Coller sans ecraser", e.b, "girafe koala mais pas demain");
		assertEquals("Coller: clipboard?", e.c, "koala");
		
		// Coller en ecrasant
		e.select(13, 4);
		e.paste();
		assertEquals("Coller et ecraser", e.b, "girafe koala koala pas demain");
		
		
	}

	@Test
	public void testInsert() {
		e.b.append("tests Insertion");
		
		// Insertion sans selection = au debut du buffer
		e.insert("4");
		assertEquals("Insertion sans selection", e.b, "4tests Insertion");
		
		// Insertion en supprimant du texte au debut
		e.select(0, 6);
		e.insert("42");
		assertEquals("Selection depuis le debut", e.b, "42 Insertion");
		
		// Insertion en supprimant du texte au milieu
		e.select(3, 2);
		e.insert("ex");
		assertEquals("Selection dans le milieu", e.b, "42 exsertion");
		
	}

	@Test
	public void testSelect() {
		// La sélection ne sort pas de la taille du texte
		e.select(12,15);
		assertEquals("Out of bounds", 0, e.s.getLenght());
		assertEquals("Out of bounds", 0, e.s.getStart());
		
		// Cas normal
		e.b.append("hello world");
		e.select(1, 4);
		assertEquals("Not good number", 1, e.s.getStart());
		assertEquals("Not good number", 4, e.s.getLenght());
		
		// Mauvais indice de départ
		e.select(-8, 9);
		assertEquals("Negative number", 0, e.s.getStart());
		
		// Selection a l'envers
		e.select(5, -3);
		assertEquals("Wrong start", 2, e.s.getStart());
		
		// Selection nulle
		e.select(3, 0);
		assertEquals("Not null selection", 0, e.s.getLenght());
		
	}

}
