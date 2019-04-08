package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public class GoToMedicalBillCard extends Card {
	
	public GoToMedicalBillCard() {
		this.name = "Go To Medical Bill";
	}

	public void effect(GamePanel gamePanel, Player user) {
		user.setCurrTile(gamePanel.getMedicalBillTile());
		gamePanel.getMedicalBillTile().billFee(user, gamePanel.getChest());
	}

}
