package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public class GoToStartCard extends Card{
	
	public GoToStartCard(int col) {
		this.name = "Go To Start";
		this.col = col;
	}
	
	public void effect(GamePanel gamePanel, Player user) {
		user.setCurrTile(gamePanel.getStartTile());
		gamePanel.getStartTile().giveDoubleSalary(user);
	}
}
