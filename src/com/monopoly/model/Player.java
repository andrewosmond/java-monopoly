package com.monopoly.model;

import java.util.Vector;

public class Player {
	private String name;
	private Character character;
	private long money;
	private Vector<Property> propertyList;
	private int islandCount = 0;
	
	public Player(String name, Character character) {
		this.character = character;
		this.money = 2000000;
		this.propertyList = new Vector<Property>();
	}
	
	public void addProperty(Property p) {
		propertyList.add(p);
	}
	
	public void removeProperty(Property p) {
		propertyList.remove(p);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public int getIslandCount() {
		return islandCount;
	}

	public void setIslandCount(int islandCount) {
		this.islandCount = islandCount;
	}
}
