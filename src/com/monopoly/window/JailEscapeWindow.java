package com.monopoly.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;
import com.monopoly.model.Player;

public class JailEscapeWindow implements WindowListener{
	private JFrame frame = null;
	private GamePanel gamePanel = null;
	private JailEscapePanel jailEscapePanel = null;
	private JailEscapeButtonPanel jailEscapeButtonPanel = null;
	
	public JailEscapeWindow(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		frame = new JFrame("Jail Escape Window");
		frame.setLayout(new BorderLayout());
		frame.add(new JPanel(new FlowLayout()), BorderLayout.NORTH);
		frame.add(new JPanel(new FlowLayout()), BorderLayout.WEST);
		frame.add(new JPanel(new FlowLayout()), BorderLayout.EAST);
		jailEscapePanel = new JailEscapePanel();
		frame.add(jailEscapePanel, BorderLayout.CENTER);
		jailEscapeButtonPanel = new JailEscapeButtonPanel(jailEscapePanel, this, gamePanel);
		frame.add(jailEscapeButtonPanel, BorderLayout.SOUTH);
		frame.setSize(500, 400);
		frame.pack();
		frame.addWindowListener(this);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void view(Player player) {
		if (frame.isVisible()) return;
		jailEscapePanel.init(player);
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
