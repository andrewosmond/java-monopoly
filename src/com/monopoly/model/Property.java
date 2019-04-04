package com.monopoly.model;

public abstract class Property {
	protected String name;
	protected Player owner;
	protected int tilesRow;
	protected int tilesCol;
	
	public abstract void buy(Player p);
	public abstract void sell();
	public abstract boolean isTakeOverAble();
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

	public void setName(String name) {
		this.name = name;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
}
