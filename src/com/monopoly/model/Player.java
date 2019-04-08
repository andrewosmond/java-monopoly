package com.monopoly.model;

import java.util.Vector;

import com.monopoly.card.Card;
import com.monopoly.main.GamePanel;

public class Player {
	private String name;
	private Character character;
	private long money;
	private Vector<Property> propertyList;
	private Tiles currTile;
	private Card card;
	
	private int islandCount = 0;
	private int jailDuration = 0;
	private boolean inJail = false;
	
	public Player(String name, Character character, GamePanel gamePanel) {
		this.name = name;
		this.character = character;
		this.money = 2000000;
		this.propertyList = new Vector<Property>();
		this.currTile = gamePanel.getStartTile();
		this.card = null;
		this.islandCount = 0;
		this.jailDuration = 0;
		this.inJail = false;
	}
	
	public void addProperty(Property p) {
		if (!propertyList.contains(p))
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

	public Tiles getCurrTile() {
		return currTile;
	}

	public void setCurrTile(Tiles currTile) {
		this.currTile = currTile;
	}
	
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public int getIslandCount() {
		return islandCount;
	}

	public void setIslandCount(int islandCount) {
		this.islandCount = islandCount;
	}

	public int getJailDuration() {
		return jailDuration;
	}

	public void setJailDuration(int jailDuration) {
		this.jailDuration = jailDuration;
	}

	public boolean isInJail() {
		return inJail;
	}

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}
	
	public Vector<Property> getPropertyList(){
		return propertyList;
	}
	
	public long getTotalAssets() {
		long result = money;
		for (Property property : propertyList) {
			result += property.getPropertyValue();
		}
		return result;
	}
	
	public boolean isBankrupt() {
		return money < 0;
	}
}
