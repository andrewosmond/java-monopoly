package com.monopoly.window;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.monopoly.card.Card;
import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;

public class CardWindow extends Window{
	private static CardWindow instance = new CardWindow();
	private CardPanel cardPanel = null;
			
	private CardWindow() {
		frame = new JFrame("Card Window");
		frame.setSize(400,500);
		cardPanel = new CardPanel();
		frame.add(cardPanel);
		frame.addWindowListener(this);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
	
	public static CardWindow getInstance() {
		return instance;
	}
	
	public void view(Card card, GamePanel gamePanel) {
		if (frame.isVisible()) return;
		this.gamePanel = gamePanel;
		cardPanel.setCard(card);
		cardPanel.repaint();
		frame.setVisible(true);
	}
	
	public void windowActivated(WindowEvent e) {}

	public void windowClosed(WindowEvent e) {}

	public void windowClosing(WindowEvent e) {
		gamePanel.setGameState(GAMESTATE.TURNEND);
	}

	public void windowDeactivated(WindowEvent e) {}

	public void windowDeiconified(WindowEvent e) {}

	public void windowIconified(WindowEvent e) {}

	public void windowOpened(WindowEvent e) {}
}
