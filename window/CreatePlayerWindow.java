package com.monopoly.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.monopoly.main.GamePanel;

public class CreatePlayerWindow implements WindowListener{
	private JFrame frame = null;
	private GamePanel gamePanel = null;

	public CreatePlayerWindow(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		frame = new JFrame("Player Form");
		frame.setLayout(new BorderLayout());
		frame.add(new JPanel(new FlowLayout()), BorderLayout.NORTH);
		frame.add(new JPanel(new FlowLayout()), BorderLayout.WEST);
		frame.add(new JPanel(new FlowLayout()), BorderLayout.EAST);
		CreatePlayerPanel playerPanel = new CreatePlayerPanel();
		frame.add(playerPanel, BorderLayout.CENTER);
		frame.add(new CreatePlayerButtonPanel(playerPanel, this, gamePanel), BorderLayout.SOUTH);
		frame.setSize(600, 400);
		frame.pack();
		frame.addWindowListener(this);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void view() {
		frame.setVisible(true);
	}

	public void windowActivated(WindowEvent e) {}

	public void windowClosed(WindowEvent e) {}

	public void windowClosing(WindowEvent e) {
		gamePanel.setCurrState(GamePanel.STATE.MENU);
	}

	public void windowDeactivated(WindowEvent e) {}

	public void windowDeiconified(WindowEvent e) {}

	public void windowIconified(WindowEvent e) {}

	public void windowOpened(WindowEvent e) {}
}
