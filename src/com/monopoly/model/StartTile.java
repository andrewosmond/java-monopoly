package com.monopoly.model;

public class StartTile extends Tiles{
	private int salary = 300000;
	
	public void giveSalary(Player p) {
		p.setMoney(p.getMoney() + salary);
	}
	
	public void giveDoubleSalary(Player p) {
		p.setMoney(p.getMoney() + salary * 2);
	}
}
