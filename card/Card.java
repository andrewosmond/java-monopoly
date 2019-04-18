package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public abstract class Card {
	protected String name;
	protected int col;
	
	public String getName() {
		return name;
	}
	
	public int getCol() {
		return col;
	}
	
	public abstract void effect(GamePanel gamePanel, Player user);
}
