package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public abstract class Card {
	protected String name;
	
	public String getName() {
		return name;
	}
	
	public abstract void effect(GamePanel gamePanel, Player user);
}
