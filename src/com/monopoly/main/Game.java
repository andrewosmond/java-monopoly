package com.monopoly.main;

import javax.swing.JFrame;

public class Game{
	private JFrame frame = null;
	
	public Game() {
		frame = new JFrame("Monopoly");
		frame.pack();
	    frame.setSize(1280, 720);

	    frame.add(new GamePanel());
		frame.setLocationRelativeTo(null);	
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
