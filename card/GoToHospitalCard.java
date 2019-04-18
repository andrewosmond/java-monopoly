package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public class GoToHospitalCard extends Card {
	
	public GoToHospitalCard(int col) {
		this.name = "Go To Hospital";
		this.col = col;
	}

	public void effect(GamePanel gamePanel, Player user) {
		user.setCurrTile(gamePanel.getMedicalBillTile());
		gamePanel.getMedicalBillTile().billFee(user, gamePanel.getChest());
	}

}
