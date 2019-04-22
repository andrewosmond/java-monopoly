package com.monopoly.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;
import com.monopoly.model.City;
import com.monopoly.model.Player;

public class BuyCityWindow extends Window{
	private BuyCityPanel buyCityPanel = null;
	private BuyCityButtonPanel buyCityButtonPanel = null;
	
	public BuyCityWindow(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		frame = new JFrame("Buy City Window");
		frame.setLayout(new BorderLayout());
		frame.add(new JPanel(new FlowLayout()), BorderLayout.NORTH);
		frame.add(new JPanel(new FlowLayout()), BorderLayout.WEST);
		frame.add(new JPanel(new FlowLayout()), BorderLayout.EAST);
		buyCityPanel = new BuyCityPanel(this);
		frame.add(buyCityPanel, BorderLayout.CENTER);
		buyCityButtonPanel = new BuyCityButtonPanel(buyCityPanel, this, gamePanel); 
		frame.add(buyCityButtonPanel, BorderLayout.SOUTH);
		frame.setSize(600, 600);
		frame.pack();
		frame.addWindowListener(this);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void view(City city, Player player) {
		buyCityPanel.init(city, player);
		frame.setVisible(true);
	}
	
	public BuyCityButtonPanel getButtonPanel() {
		return buyCityButtonPanel;
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
