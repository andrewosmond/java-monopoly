package com.monopoly.model;

import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.monopoly.card.Card;
import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;

public class Player {
	private String name;
	private Character character;
	private long money;
	private Vector<Property> propertyList;
	private Tiles currTile;
	private Card card;
	private GamePanel gamePanel = null;

	private int islandCount = 0;
	private int jailDuration = 0;

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

	public boolean isBankrupt() {
		return money < 0;
	}

	public void move(Graphics g) {
		if (gamePanel.getDiceRolled() == 0) return;
		
		gamePanel.setDiceRolled(gamePanel.getDiceRolled() - 1);
		currTile = gamePanel.getBoard().getNextTiles(currTile);
		
		if (gamePanel.getDiceRolled() == 0) {
			if (currTile instanceof City) {
				City city = (City) currTile;
				if (city.getOwner() == null) {
					gamePanel.getBuyCityWindow().view(city, this);
				} else if (city.getOwner() == this) {
					if (city.isLandBought() && city.isHouseBought() && city.isHotelBought()
							&& !city.isLandmarkBought()) {
						if (this.getMoney() >= city.getLandmarkPrice()) {
							int input = JOptionPane.showConfirmDialog(null, "Do you want to upgrade " + city.getName() + " to Landmark?",
									"Confirmation", JOptionPane.YES_NO_OPTION);
							if (input == 0) {
								city.setLandmarkBought(true);
								this.setMoney(this.getMoney() - city.getLandmarkPrice());
							}
							gamePanel.setGameState(GAMESTATE.TURNEND);
						}
					} else if (!city.isLandmarkBought()){
						gamePanel.getBuyCityWindow().view(city, this);
					}
				} else {
					this.setMoney(this.getMoney() - city.getRentFee());
					city.getOwner().setMoney(city.getOwner().getMoney() + city.getRentFee());
					gamePanel.setGameState(GAMESTATE.TURNEND);
				}
			} else if (currTile instanceof Island) {
				Island island = (Island) currTile;
				if (island.getOwner() == null) {
					if (this.getMoney() >= island.getPrice()) {
						int input = JOptionPane.showConfirmDialog(null, "Do you want to buy " + island.getName() + " ?",
								"Confirmation", JOptionPane.YES_NO_OPTION);
						if (input == 0)
							island.buy(this);
					}
				} else if (island.getOwner() != this) {
					this.setMoney(this.getMoney() - island.getRentFee());
				}
				gamePanel.setGameState(GAMESTATE.TURNEND);
			} else if (currTile instanceof StartTile) {
				StartTile startTile = (StartTile) currTile;
				startTile.giveDoubleSalary(this);
				System.out.println(this.getName() + " received double salary!");
				gamePanel.setGameState(GAMESTATE.TURNEND);
			} else if (currTile instanceof Chest) {
				Chest chest = (Chest) currTile;
				chest.giveMoney(this);
				gamePanel.setGameState(GAMESTATE.TURNEND);
			} else if (currTile instanceof GoToJailTile) {
				GoToJailTile goToJailTile = (GoToJailTile) currTile;
				goToJailTile.send(this);
				gamePanel.setGameState(GAMESTATE.TURNEND);
			} else if (currTile instanceof MedicalBillTile) {
				MedicalBillTile medicalBillTile = (MedicalBillTile) currTile;
				medicalBillTile.billFee(this, gamePanel.getChest());
				gamePanel.setGameState(GAMESTATE.TURNEND);
			} else if (currTile instanceof ChanceCardTile) {
				ChanceCardTile chanceCardTile = (ChanceCardTile) currTile;
				chanceCardTile.randomCard(this);
			} else if (currTile instanceof JailTile) {
				gamePanel.setGameState(GAMESTATE.TURNEND);
			}

			System.out.println(getName() + " moved to " + currTile.getName());

		} else {
			if (currTile instanceof StartTile) {
				StartTile startTile = (StartTile) currTile;
				startTile.giveSalary(this);
				System.out.println(this.getName() + " received normal salary!");
			}
		}

	}
}
