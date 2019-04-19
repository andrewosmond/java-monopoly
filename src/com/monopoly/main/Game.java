package com.monopoly.main;

import javax.swing.JFrame;

import com.monopoly.utility.JukeBox;

public class Game{
	private JFrame frame = null;
	
	public Game() {
		frame = new JFrame("Monopoly");
	    frame.setSize(1280, 720);

	    JukeBox.init();
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
