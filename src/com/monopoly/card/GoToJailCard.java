package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public class GoToJailCard extends Card{
	
	public GoToJailCard() {
		this.name = "Go To Jail";
	}
	
	public void effect(GamePanel gamePanel, Player user) {
		user.setCurrTile(gamePanel.getJailTile());
		user.setInJail(true);
		user.setJailDuration(3);
	}

}
