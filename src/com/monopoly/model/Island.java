package com.monopoly.model;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import com.monopoly.card.HauntedHouseCard;
import com.monopoly.card.VIPCard;
import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;

public class Island extends Property{
	private long price;
	private final long rent = 80000;
	
	public Island(DIRECTION buildDir, int buildCoorX, int buildCoorY, int tilesRow, int tilesCol, CHARADIR charaDir, int tileCoorX, int tileCoorY, String name, long price, GamePanel gamePanel) {
		this.buildDir = buildDir;
		this.buildCoorX = buildCoorX;
		this.buildCoorY = buildCoorY;
		this.owner = null;
		this.tilesRow = tilesRow;
		this.tilesCol = tilesCol;
		this.charaDir = charaDir;
		this.tileCoorX = tileCoorX;
		this.tileCoorY = tileCoorY;
		this.name = name;
		this.price = price;
		this.gamePanel = gamePanel;
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
		
		if (buildDir == DIRECTION.LEFT) {
			islandIdx = 11;
		} else if (buildDir == DIRECTION.RIGHT) {
			islandIdx = 5;
		}
		
		g.drawImage(gamePanel.getPropertyImage().getImage(), buildCoorX, buildCoorY, buildCoorX + x, buildCoorY + y, x * playerColor,
				y * islandIdx, x * playerColor + x, y * islandIdx + y, null);
	}

	@Override
	public void event(Player player) {
		// Island have no owner
		if (owner == null) {
			// Have sufficient money
			if (player.getMoney() >= price) {
				int input = JOptionPane.showConfirmDialog(null, "Do you want to buy " + name + " ?",
						"Confirmation", JOptionPane.YES_NO_OPTION);
				if (input == 0)
					buy(player);					
			}
			gamePanel.setGameState(GAMESTATE.TURNEND);
		}
		// Island's owner is not this player
		else if (owner != player) {
			// Have no card
			if (player.getCard() == null) {
				player.checkPayRent(getRentFee());
				player.setMoney(player.getMoney() - getRentFee());
				owner.setMoney(owner.getMoney() + getRentFee());
			} else if (player.getCard() instanceof VIPCard || player.getCard() instanceof HauntedHouseCard) {
				int input = JOptionPane.showConfirmDialog(null, "Do you want to use " + player.getCard().getName() + " ?",
						"Confirmation", JOptionPane.YES_NO_OPTION);
				gamePanel.setRentFee(getRentFee());
				// If answer = yes, apply the card effect to gamePanel rentFee
				if (input == 0) {
					player.getCard().effect(gamePanel, player);
					player.checkPayRent(gamePanel.getRentFee());
					player.setMoney(player.getMoney() - gamePanel.getRentFee());
					owner.setMoney(owner.getMoney() + gamePanel.getRentFee());
				}
				// Apply the normal island rent fee
				else {
					player.checkPayRent(getRentFee());
					player.setMoney(player.getMoney() - getRentFee());
					owner.setMoney(owner.getMoney() + getRentFee());
				}
			}
		}
		gamePanel.setGameState(GAMESTATE.TURNEND);
	}
}
