package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public class RedemptionCard extends IndirectCard{
	
	public RedemptionCard(int col) {
		this.name = "Redemption";
		this.col = col;
	}

	public void effect(GamePanel gamePanel, Player user) {
		user.setJailDuration(0);
		removeCard(user);
	}
}
