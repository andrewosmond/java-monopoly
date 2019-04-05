package com.monopoly.model;

import java.awt.Font;
import java.awt.Graphics;

import com.monopoly.utility.MoneyFormatter;

public class Chest {
	private long money;
	
	public Chest() {
		money = 0;
	}
	
	public void increaseMoney() {
		money += 10000;
	}
	
	public void increaseMoney(long money) {
		this.money += money;
	}
	
	public void giveMoney(Player p) {
		p.setMoney(p.getMoney() + money);
		money = 0;
	}
	
	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public void render(Graphics g) {
		g.setFont(new Font("Calibri", Font.PLAIN, 20));
		if (money == 0) {
			g.drawString(MoneyFormatter.getFormat(money), 506, 258);
		} else {
			g.drawString(MoneyFormatter.getFormat(money), 490, 258);
		}
	}
}