package com.monopoly.model;

import java.awt.Graphics;

import com.monopoly.main.GamePanel;

public abstract class Property extends Tiles{
	protected Player owner;
	protected DIRECTION direction;
	protected int coorX;
	protected int coorY;
	protected int tilesRow;
	protected int tilesCol;
	protected int multiplier;
	
	public static enum DIRECTION{
		LEFT, RIGHT
	};
	
	public abstract void buy(Player p);
	public abstract void sell();
	public abstract void render(Graphics g, GamePanel gamePanel);
	public abstract boolean isTakeOverAble();
	public abstract long getPropertyValue();
	public abstract long getSellValue();
	public abstract long getTakeOverFee();
	public abstract long getRentFee();

	public int getTilesRow() {
		return tilesRow;
	}

	public int getTilesCol() {
		return tilesCol;
	}
	
	public String getName() {
		return name;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public DIRECTION getDirection() {
		return direction;
	}
	
	public int getCoorX() {
		return coorX;
	}
	
	public int getCoorY() {
		return coorY;
	}
	
	public int getMultiplier() {
		return multiplier;
	}
	
	public void setMutliplier(int multiplier) {
		this.multiplier = multiplier;
	}
}
