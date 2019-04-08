package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public class VIPCard extends IndirectCard{
	
	public VIPCard() {
		this.name = "VIP Card";
	}

	public void effect(GamePanel gamePanel, Player user) {
		gamePanel.setRentFee(0);
		removeCard(user);
	}

}
