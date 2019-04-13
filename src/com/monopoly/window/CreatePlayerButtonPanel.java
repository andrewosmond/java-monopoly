package com.monopoly.window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;
import com.monopoly.model.Board;
import com.monopoly.model.Character;
import com.monopoly.model.Player;

@SuppressWarnings("serial")
public class CreatePlayerButtonPanel extends JPanel implements ActionListener {
	private JButton btnOk = new JButton("Ok");
	private CreatePlayerPanel playerPanel = null;
	private CreatePlayerWindow playerWindow = null;
	private GamePanel gamePanel = null;

	public CreatePlayerButtonPanel(CreatePlayerPanel playerPanel, CreatePlayerWindow playerWindow, GamePanel gamePanel) {
		setLayout(new FlowLayout());
		this.playerPanel = playerPanel;
		this.playerWindow = playerWindow;
		this.gamePanel = gamePanel;
		add(btnOk);
		btnOk.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOk) {
			int input = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (input == 0) {
				if (playerPanel.isNameEmpty()) {
					JOptionPane.showMessageDialog(this, "Name must be filled!");
					return;
				}
				
				gamePanel.getPropertyList().clear();
				gamePanel.initProperty();
				gamePanel.setBoard(new Board(gamePanel));
				gamePanel.getPlayerList().clear();
				for (int i = 0; i < playerPanel.getNumOfPlayer(); i++) {
					Character c = new Character(playerPanel.getCharacterNames().get(i));
					Player p = new Player(playerPanel.getPlayerNames().get(i), c, gamePanel);
					c.setPlayer(p);
					gamePanel.getPlayerList().add(p);
				}
				
				JOptionPane.showMessageDialog(this, "Player successfully created!");
				gamePanel.setCurrState(GamePanel.STATE.GAME);
				gamePanel.setGameState(GAMESTATE.TURNSTART);
				gamePanel.setTilesButton(true);
				playerWindow.getFrame().setVisible(false);
			}
		}
	}
}
