package com.monopoly.model;

public class JailTile extends Tiles {
	
	public JailTile() {
		this.name = "Jail";
	}
	
	public void jailPlayer(Player p) {
		p.setJailDuration(3);
		p.setCurrTile(this);
	}
	
	public void decrementDuration(Player p) {
		if (p.getJailDuration() > 0)
			p.setJailDuration(p.getJailDuration() - 1);
	}
	
	public void payFee(Player p) {
		p.setMoney(p.getMoney() - 200000);
	}
	
	public void releasePlayer(Player p) {
		p.setJailDuration(0);
	}
	
}
