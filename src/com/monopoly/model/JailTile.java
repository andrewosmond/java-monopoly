package com.monopoly.model;

public class JailTile extends Tiles {
	
	public JailTile() {
		this.name = "Jail";
	}
	
	public void decrementDuration(Player p) {
		if (p.getJailDuration() > 0)
			p.setJailDuration(p.getJailDuration() - 1);
	}
	
	public void releasePlayer(Player p) {
		if (p.getJailDuration() == 0) {
			p.setInJail(false);
		}
	}
	
}
