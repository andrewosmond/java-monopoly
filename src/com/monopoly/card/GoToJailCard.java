package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public class GoToJailCard extends Card{
	
	public GoToJailCard(int col) {
		this.name = "Go To Jail";
		this.col = col;
	}
	
	public void effect(GamePanel gamePanel, Player user) {
		user.setCurrTile(gamePanel.getJailTile());
		user.setJailDuration(3);
	}

}
