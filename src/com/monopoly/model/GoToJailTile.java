package com.monopoly.model;

public class GoToJailTile extends Tiles{
	
	public GoToJailTile() {
		this.name = "Go To Jail";
	}
	
	public void send(Player p) {
		p.setInJail(true);
		p.setJailDuration(3);
	}
}
