package com.monopoly.model;

import com.monopoly.main.GamePanel;

public class GoToJailTile extends Tiles{
	private GamePanel gamePanel = null;
	
	public GoToJailTile(GamePanel gamePanel) {
		this.name = "Go To Jail";
		this.gamePanel = gamePanel;
	}
	
	public void send(Player p) {
		p.setJailDuration(3);
		p.setCurrTile(gamePanel.getJailTile());
	}
}
