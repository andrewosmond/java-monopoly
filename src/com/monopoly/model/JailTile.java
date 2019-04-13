package com.monopoly.model;

import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;

public class JailTile extends Tiles {
	
	public JailTile(GamePanel gamePanel, CHARADIR charaDir, int tileCoorX, int tileCoorY) {
		this.name = "Jail";
		this.gamePanel = gamePanel;
		this.charaDir = charaDir;
		this.tileCoorX = tileCoorX;
		this.tileCoorY = tileCoorY;
	}
	
	public void jailPlayer(Player p) {
		p.setJailDuration(3);
		p.setCurrTile(this);
	}
	
	public void decrementDuration(Player p) {
		if (p.getJailDuration() > 0)
			p.setJailDuration(p.getJailDuration() - 1);
	}
	
	public void payFee(Player p) {
		p.setMoney(p.getMoney() - 200000);
	}
	
	public void releasePlayer(Player p) {
		p.setJailDuration(0);
	}

	@Override
	public void event(Player player) {
		gamePanel.setGameState(GAMESTATE.TURNEND);
	}
	
}
