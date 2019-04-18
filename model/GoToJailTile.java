package com.monopoly.model;

import com.monopoly.main.GamePanel;

public class GoToJailTile extends Tiles{
	
	public GoToJailTile(GamePanel gamePanel, CHARADIR charaDir, int tileCoorX, int tileCoorY) {
		this.name = "Go To Jail";
		this.gamePanel = gamePanel;
		this.charaDir = charaDir;
		this.tileCoorX = tileCoorX;
		this.tileCoorY = tileCoorY;
	}
	
	public void send(Player p) {
		p.setJailDuration(3);
		p.setCurrTile(gamePanel.getJailTile());
	}

	@Override
	public void event(Player player) {
		// Player is sent to the jail
		send(player);
		gamePanel.nextTurn();
	}
}
