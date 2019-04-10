package com.monopoly.model;

import java.awt.Graphics;

import com.monopoly.main.GamePanel;

public class Island extends Property{
	private long price;
	private final long rent = 80000;
	
	public Island(DIRECTION direction, int coorX, int coorY, int tilesRow, int tilesCol, String name, long price) {
		this.direction = direction;
		this.coorX = coorX;
		this.coorY = coorY;
		this.owner = null;
		this.tilesRow = tilesRow;
		this.tilesCol = tilesCol;
		this.name = name;
		this.price = price;
	}

	public void buy(Player p) {
		owner = p;
		owner.setMoney(owner.getMoney() - price);
		owner.setIslandCount(owner.getIslandCount() + 1);
		owner.addProperty(this);
	}
	
	public void sell() {
		owner.setMoney(owner.getMoney() + getSellValue());
		owner.setIslandCount(owner.getIslandCount() - 1);
		owner.removeProperty(this);
		owner = null;
	}
	
	public long getPropertyValue() {
		return price;
	}

	public boolean isTakeOverAble() {
		return false;
	}
	
	public long getSellValue() {
		return (long)((double)price * 0.75);
	}
	
	public long getTakeOverFee() {
		return 0;
	}
	
	public long getPrice() {
		return price;
	}
	
	public long getRent() {
		return rent;
	}

	public long getRentFee() {
		if (owner == null) return 0;
		int islandCount = owner.getIslandCount();
		long res = islandCount >= 3 ? rent * 3 : rent * islandCount;
		return res;
	}

	public void render(Graphics g, GamePanel gamePanel) {
		int x = gamePanel.getPropertyImage().getIconWidth() / 4;
		int y = gamePanel.getPropertyImage().getIconHeight() / 12;
	
		if (owner == null) return;
		
		int playerColor = gamePanel.getPlayerList().indexOf(owner);
		int islandIdx = 0;
		
		if (direction == DIRECTION.LEFT) {
			islandIdx = 11;
		} else if (direction == DIRECTION.RIGHT) {
			islandIdx = 5;
		}
		
		g.drawImage(gamePanel.getPropertyImage().getImage(), coorX, coorY, coorX + x, coorY + y, x * playerColor,
				y * islandIdx, x * playerColor + x, y * islandIdx + y, null);
	}
}
