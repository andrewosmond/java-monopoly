package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public class DiscountCard extends IndirectCard {
	
	public DiscountCard() {
		this.name = "Discount 50%";
	}

	public void effect(GamePanel gamePanel, Player user) {
		gamePanel.setRentFee(gamePanel.getRentFee() / 2);
		removeCard(user);
	}
	
}
