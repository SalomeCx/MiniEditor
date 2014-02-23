package gui;

import java.awt.*;
import java.awt.event.*;

//import javax.print.attribute.standard.MediaSize.Engineering;
import javax.swing.*;

import commands.*;
import receiver.*;

/**
 * 
 * @author NATTA & COAVOUX
 * @version 3.0
 *
 */
public class Window extends JFrame {
	
	/************************************************************/
	/**********Definition des composants de l'interface**********/
	/************************************************************/
	//Composants du menu
	private JMenuBar laBarre;	//la barre de menu de cette fenetre
	private JMenu menuAction, menuAide;	//les deux rubriques de cette barre
	private JMenuItem editer, quitter;	//les sous rubriques du menu Action
	private JMenuItem aPropos, aide;	//les sous rubriques du menu aide
	
	//Conteneurs
	private CardLayout cl = new CardLayout();	//conteneur des differentes fenetres
	private JPanel content = new JPanel();
	private String[] listContent = {"CARD_1", "CARD_2"};	//Liste des noms de nos conteneurs pour la pile de cartes
	
	//Composants du cardEditer
	private JPanel panel;
	private Text text;
	private JButton couper, copier, coller, enregistrer, arreter, rejouer, defaire, refaire;
	
	// Les commandes correspondant aux boutons.
	private Cut cut;
	private Copy copy;
	private Insert insert; 
	private Paste paste;
	private Select select;
	private Delete delete;
	private StartRecord startRecord;
	private StopRecord stopRecord;
	private ReplayRecord replayRecord;
	private Undo undo;
	private Redo redo;
	
	//Composants de placement
	private GridBagLayout placeur = new GridBagLayout();	//layout pour le placement des composants
	
	// Pour afficher les coller. Correspond au clipboard du receiver.
	private String clip;
	
	/**
	 * Constructeur de la fenetre qui prepare les composants et les places.
	 * @param engine l'EditorEngine qur lequel on travaille.
	 */
	public Window(EditorEngine engine){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setTitle("Mini Editeur");	//On donne un titre a l'application
		this.setSize(1000,500);	//dimension de la fenetre
		//this.addWindowListener(new GestionWindow());	//traitement des evements de la fenetre
		this.setLocationRelativeTo(null); //On centre la fenetre sur l'ecran
		
		this.buildMenu();
		
		//On definit le layout
		content.setLayout(cl);
		//On ajoute les cartes a la pile avec un nom pour les retrouver
		content.add(cardBienvenue(), listContent[0]);
		content.add(cardEditer(engine), listContent[1]);
			 
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.setVisible(true);
	} 
	
	/**
	 * Construire le menu.
	 */
	private void buildMenu(){
		menuAction = new JMenu("Actions");	//rubrique des actions possible
		editer = new JMenuItem("Editer");	//fenetre pour editer
		quitter = new JMenuItem("Quitter");	//quitter l'application
		
		menuAction.add(editer);	//ajoute a la rubrique
		menuAction.add(quitter);
		
		editer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Via cette instruction, on passe au conteneur correspondant au nom fourni en parametre
			    cl.show(content, listContent[1]);
				}
		});	//traitement lie a l'edition
		
		
		quitter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int retour = JOptionPane.showConfirmDialog
						(null, "Attention, l'application est sur le point d'etre fermee!",
								"Fermeture", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (retour == 0)
					System.exit(0);
			} 
		});	//traitement lie a la fermeture
		
		menuAide = new JMenu("?");	//rubrique d'aide
		aPropos = new JMenuItem("A propos");	//information sur l'application
		aide = new JMenuItem("Aide");
		
		menuAide.add(aPropos);	//ajoute a la rubrique
		menuAide.add(aide);
		
		aPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog
				(null, 
				"<html><body>Ce programme a ete developpe par Natta Vincente et Coavoux Salome<br/>Pour le module ACO</body></html>",
				"Developpeur",
				JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		aide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog
				(null, 
				"<html><body>Depuis le menu choissez editer dans le menu des actions<\br>Chaque bouton correspond a une action</body></html>",
				"Developpeur",
				JOptionPane.INFORMATION_MESSAGE);		
			}
		});
		
		laBarre = new JMenuBar();	//barre de menu
		laBarre.add(menuAction);	//ajout des rubriques
		laBarre.add(menuAide);	
		
		setJMenuBar(laBarre);	//ajout de la barre dans la fenetre
	}
	
	/**
	 * Construire le panneau d'accueil.
	 * @return le panneau d'accueil.
	 */
	private JPanel cardBienvenue(){		
		JPanel panel = new JPanel();	//zone ou on affiche 
		
		JLabel texteBienvenue = new JLabel("<html><body>Bienvenue sur notre mini editeur,<br/>Version 3<br/><br/>");	//message de bienvenue
		panel.add(texteBienvenue);	//Ajout au panel
		
		
		panel.setBackground(Color.white);	//couleur du fond
		panel.setLayout(placeur);	//placement des composants

		return panel;
	}
	
	/**
	 * Construire le panneau principal.
	 * @param engine l'EditorEngine sur lequel on travaille.
	 * @return le panneau principal avec la zone de texte et les boutons.
	 */
	private JPanel cardEditer(EditorEngine engine){		
		panel = new JPanel();
		panel.setBackground(Color.gray);	//couleur du fond
		
		text = new Text(this);	//zone ou  on ecrit
		text.setEditable(true);
		
		panel.add(text, BorderLayout.CENTER);

		panel.setLayout(new BorderLayout());
		
		initCommand(engine);
		panel.add(createButtons(engine), BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * Initialiser les commandes.
	 * @param edit l'editorEngine dont on va se servir.
	 */
	private void initCommand(EditorEngine edit) {
		cut = new Cut(edit);
		copy = new Copy(edit);
		paste = new Paste(edit);
		insert = new Insert(edit, "");
		select = new Select(edit, 0, 0);
		delete = new Delete(edit);
		startRecord = new StartRecord(edit);
		stopRecord = new StopRecord(edit);
		replayRecord = new ReplayRecord(edit);
		undo = new Undo(edit);
		redo = new Redo(edit);
	}
	
	/**
	 * Creer les boutons qui vont exploiter notre code: copier, coller, etc...
	 * @return p le panel dans lequel seront crees les boutons.
	 */
	private JPanel createButtons(EditorEngine engine) {
		JPanel p = new JPanel();
		p.setBackground(Color.gray);
		couper = new JButton("Couper");
		copier = new JButton("Copier");
		coller = new JButton("Coller");
		enregistrer = new JButton("Enregistrer");
		arreter = new JButton("Arreter");
		rejouer = new JButton("Rejouer");
		defaire = new JButton("Defaire");
		refaire = new JButton("Refaire");
		
		couper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Garder dans le clipboard de l'IHM
				clip = text.getSelectedText();
				
				// Mettre a jour la selection
				int x = text.getSelectionStart();
	    		int y = text.getSelectionEnd();
	    		select.setSelect(x, (y - x));
	    		select.execute();
	    		
	    		// Mettre a jour le JTextArea 
				text.replaceRange(null, text.getSelectionStart(), text.getSelectionEnd());
				
				// Executer les commandes implementees.
				cut.execute();
			}
		});	//traitement lie au bouton
		
		copier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clip = text.getSelectedText();
				
				// Mettre a jour la selection
				int x = text.getSelectionStart();
	    		int y = text.getSelectionEnd();
	    		select.setSelect(x, (y - x));
	    		select.execute();
				
				copy.execute();
			}
		});
		
		coller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mettre a jour la selection
				int x = text.getSelectionStart();
	    		int y = text.getSelectionEnd();
	    		select.setSelect(x, (y - x));
	    		select.execute();
	    		
				paste.execute();
				text.replaceRange(clip, text.getSelectionStart(), text.getSelectionEnd());
			}
		});
		
		enregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mettre a jour la selection
				int x = text.getSelectionStart();
	    		int y = text.getSelectionEnd();
	    		select.setSelect(x, (y - x));
	    		select.execute();
	    		
				startRecord.execute();
			}
		});
		
		arreter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mettre a jour la selection
				int x = text.getSelectionStart();
	    		int y = text.getSelectionEnd();
	    		select.setSelect(x, (y - x));
	    		select.execute();
	    		
				stopRecord.execute();
			}
		});
		
		rejouer.addActionListener(new ReplayControler(select, text, replayRecord, engine.getBuffer()));

		defaire.addActionListener(new UndoControler(text, undo, engine.getBuffer()));
		
		refaire.addActionListener(new RedoControler(text, redo, engine.getBuffer()));
		
		p.add(couper);
		p.add(copier);
		p.add(coller);
		p.add(enregistrer);
		p.add(arreter);
		p.add(rejouer);
		p.add(defaire);
		p.add(refaire);
		
		return p;
	}
	
	/**
	 * Getter de la commande Insert.
	 * Utilise par Text pour lier le KeyListener.
	 * @return la commande Insert.
	 */
	public Insert getInsert() {
		return this.insert;
	}
	
	/**
	 * Getter de la commande Select.
	 * Utilise par Text pour lier le KeyListener.
	 * @return la commande Select.
	 */
	public Select getSelect() {
		return this.select;
	}
	
	/**
	 * Getter de la commande Delete.
	 * Utilise par Text pour lier le KeyListener.
	 * @return la commande Delete.
	 */
	public Delete getDelete() {
		return this.delete;
	}
}
