package GUI;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public Window(){
		super();
		build();
	}
 
	private void build(){
		setTitle("Text Editor"); 
		setSize(800,800);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
