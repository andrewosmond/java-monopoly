package com.monopoly.model;

public class MedicalBillTile extends Tiles {

	public MedicalBillTile() {
		this.name = "Medical Bill";
	}
	
	public void billFee(Player p, Chest chest) {
		long money = p.getMoney() / 10;
		p.setMoney(p.getMoney() - money);
		chest.increaseMoney(money);
	}
}
