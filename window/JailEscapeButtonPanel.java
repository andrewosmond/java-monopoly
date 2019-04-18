package com.monopoly.window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;
import com.monopoly.model.Player;

@SuppressWarnings("serial")
public class JailEscapeButtonPanel extends JPanel implements ActionListener, Runnable {
	private JButton btnOk = new JButton("Ok");
	private JailEscapePanel jailEscapePanel = null;
	private JailEscapeWindow jailEscapeWindow = null;
	private GamePanel gamePanel = null;
	private Thread thread = null;
	private boolean running = false;
	private Player player = null;
	
	public JailEscapeButtonPanel(JailEscapePanel jailEscapePanel, JailEscapeWindow jailEscapeWindow, GamePanel gamePanel) {
		this.jailEscapePanel = jailEscapePanel;
		this.jailEscapeWindow = jailEscapeWindow;
		this.gamePanel = gamePanel;
		setLayout(new FlowLayout());
		add(btnOk);
		btnOk.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOk) {
			if (!jailEscapePanel.isOptionSelected()) {
				JOptionPane.showMessageDialog(this, "Option must be choosed!");
				return;
			}
			
			player = jailEscapePanel.getPlayer();
			
			if (jailEscapePanel.isRollDoubleSelected()) {
				jailEscapeWindow.getFrame().setVisible(false);
				gamePanel.getDice().start();
				this.start();
			} else if (jailEscapePanel.isPayFeeSelected()) {
				gamePanel.getJailTile().payFee(player);
				gamePanel.getJailTile().releasePlayer(player);
				gamePanel.setGameState(GAMESTATE.ROLL);
				
			} else if (jailEscapePanel.isEscapeCardSelected()) {
				gamePanel.getJailTile().releasePlayer(player);
				player.setCard(null);
				gamePanel.setGameState(GAMESTATE.ROLL);
			}
			
			jailEscapeWindow.getFrame().setVisible(false);
		}
	}
	
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void end() {
		if (!running) return;
		running = false;
	}

	public void run() {
		long timer = System.currentTimeMillis();
		
		while(running) {
			if (System.currentTimeMillis() - timer > 1700) {
				end();
			}
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if (gamePanel.getDice().isDouble()) {
			JOptionPane.showMessageDialog(this, "You have escaped from the jail!");
			gamePanel.getJailTile().releasePlayer(player);
			gamePanel.setGameState(GAMESTATE.ROLL);
		} else {
			JOptionPane.showMessageDialog(this, "You have failed to roll double!");
			gamePanel.setGameState(GAMESTATE.TURNEND);
		}
	}

}
