package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public class GoToStartCard extends Card{
	
	public GoToStartCard() {
		this.name = "Go To Start";
	}
	
	public void effect(GamePanel gamePanel, Player user) {
		gamePanel.getStartTile().giveDoubleSalary(user);
	}
}
