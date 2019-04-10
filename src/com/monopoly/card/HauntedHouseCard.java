package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public class HauntedHouseCard extends IndirectCard {
	
	public HauntedHouseCard(int col) {
		this.name = "Haunted House";
		this.col = col;
	}

	public void effect(GamePanel gamePanel, Player user) {
		gamePanel.setRentFee(gamePanel.getRentFee() / 2);
		removeCard(user);
	}
	
}
