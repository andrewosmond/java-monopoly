package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public class EscapeCard extends IndirectCard{
	
	public EscapeCard() {
		this.name = "Escape";
	}

	public void effect(GamePanel gamePanel, Player user) {
		user.setInJail(false);
		user.setJailDuration(0);
		removeCard(user);
	}
}
