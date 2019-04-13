package com.monopoly.model;

import java.util.Vector;

import com.monopoly.card.Card;
import com.monopoly.main.GamePanel;

public class Player implements Runnable {
	private String name;
	private Character character;
	private long money;
	private Vector<Property> propertyList;
	private Tiles currTile;
	private Card card;
	private GamePanel gamePanel = null;
	private int islandCount = 0;
	private int jailDuration = 0;
	
	private Thread thread;
	private boolean running = false;

	public Player(String name, Character character, GamePanel gamePanel) {
		this.name = name;
		this.character = character;
		this.money = 2000000;
		this.propertyList = new Vector<Property>();
		this.currTile = gamePanel.getStartTile();
		this.card = null;
		this.gamePanel = gamePanel;
		this.islandCount = 0;
		this.jailDuration = 0;
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
		return jailDuration > 0;
	}

	public Vector<Property> getPropertyList() {
		return propertyList;
	}

	public long getTotalAssets() {
		long result = money;
		for (Property property : propertyList) {
			result += property.getPropertyValue();
		}
		return result;
	}
	
	public void checkPayRent(long rent) {
		while(!propertyList.isEmpty()) {
			if (money >= rent) break;
			propertyList.get(0).sell();
		}
	}

	public boolean isBankrupt() {
		return money < 0;
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void end() {
		if (!running) return;
		running = false;
	}

	public void move() {
		start();
	}

	public void run() {
		for (int i = 0; i < gamePanel.getDiceRolled(); i++) {
			try {
				Thread.sleep(1000/10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currTile = gamePanel.getBoard().getNextTiles(currTile);
			if (i != gamePanel.getDiceRolled() - 1) {
				if (currTile instanceof StartTile) {
					StartTile startTile = (StartTile) currTile;
					startTile.giveSalary(this);
					System.out.println(this.getName() + " received normal salary!");
				}
			}
		}
	
		currTile.event(this);
		System.out.println(getName() + " moved to " + currTile.getName());
		end();
	}
}
