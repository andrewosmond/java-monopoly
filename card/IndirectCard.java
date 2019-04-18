package com.monopoly.card;

import com.monopoly.model.Player;

public abstract class IndirectCard extends Card {

	public void addCard(Player p) {
		p.setCard(this);
	}
	
	public void removeCard(Player p) {
		p.setCard(null);
	}
}
