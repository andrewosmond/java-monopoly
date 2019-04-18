package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public class VIPCard extends IndirectCard{
	
	public VIPCard(int col) {
		this.name = "VIP";
		this.col = col;
	}

	public void effect(GamePanel gamePanel, Player user) {
		gamePanel.setRentFee(0);
		removeCard(user);
	}

}
