package com.monopoly.model;

public class Island extends Property{
	private long price;
	private final long rent = 80000;
	
	public Island(int tilesRow, int tilesCol, String name, long price) {
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
}
