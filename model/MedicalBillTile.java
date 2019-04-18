package com.monopoly.model;

import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;

public class MedicalBillTile extends Tiles {

	public MedicalBillTile(GamePanel gamePanel, CHARADIR charaDir, int tileCoorX, int tileCoorY) {
		this.name = "Medical Bill";
		this.gamePanel = gamePanel;
		this.charaDir = charaDir;
		this.tileCoorX = tileCoorX;
		this.tileCoorY = tileCoorY;
	}
	
	public void billFee(Player p, Chest chest) {
		long money = p.getMoney() / 10;
		p.setMoney(p.getMoney() - money);
		chest.increaseMoney(money);
	}

	@Override
	public void event(Player player) {
		// Player pays 1/10 medical bill
		billFee(player, gamePanel.getChest());
		gamePanel.setGameState(GAMESTATE.TURNEND);
	}
}
