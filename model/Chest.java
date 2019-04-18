package com.monopoly.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;
import com.monopoly.utility.MoneyFormatter;

public class Chest extends Tiles{
	private long money;
	
	public Chest(GamePanel gamePanel, CHARADIR charaDir, int tileCoorX, int tileCoorY) {
		this.name = "Chest";
		this.gamePanel = gamePanel;
		this.charaDir = charaDir;
		this.tileCoorX = tileCoorX;
		this.tileCoorY = tileCoorY;
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
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 20));
		int len = MoneyFormatter.getFormat(money).length();
		g.drawString(MoneyFormatter.getFormat(money), (1016-10*len)/2, 258);
	}

	@Override
	public void event(Player player) {
		// Player gets money from the chest
		giveMoney(player);
		gamePanel.setGameState(GAMESTATE.TURNEND);
	}
}
