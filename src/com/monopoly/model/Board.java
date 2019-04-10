package com.monopoly.model;

import java.awt.Graphics;
import java.util.Vector;

import com.monopoly.main.GamePanel;

public class Board{
	private Vector<Tiles> tilesList;
	private GamePanel gamePanel = null;

	public Board(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		tilesList = new Vector<Tiles>();
		tilesList.add(gamePanel.getStartTile());
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Beijing")));
		tilesList.add(gamePanel.getChanceCardTile());
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Bangkok")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Phuket")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Taipei")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("New Delhi")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Seoul")));
		tilesList.add(gamePanel.getJailTile());
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Papua")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Tokyo")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Sydney")));
		tilesList.add(new ChanceCardTile(gamePanel));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Singapore")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Bali")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Sao Paulo")));
		tilesList.add(gamePanel.getChest());
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Prague")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Hawaii")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Berlin")));
		tilesList.add(new ChanceCardTile(gamePanel));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Moscow")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Geneva")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Rome")));
		tilesList.add(gamePanel.getGoToJailTile());
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Bintan")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("London")));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Paris")));
		tilesList.add(new ChanceCardTile(gamePanel));
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("New York")));
		tilesList.add(gamePanel.getMedicalBillTile());
		tilesList.add(gamePanel.getPropertyList().get(gamePanel.getPropertyIndex("Jakarta")));
	}
	
	public void checkMultiplier() {
		Vector<Property> propertyList = gamePanel.getPropertyList();
	
		// Beijing, Bangkok
		if (propertyList.get(0).getOwner() == propertyList.get(1).getOwner()) {
			propertyList.get(0).setMutliplier(2);
			propertyList.get(1).setMutliplier(2);
		} else {
			propertyList.get(0).setMutliplier(1);
			propertyList.get(1).setMutliplier(1);
		}
		
		// Taipei, New Delhi, Seoul
		if (propertyList.get(2).getOwner() == propertyList.get(3).getOwner() 
				&& propertyList.get(3).getOwner() == propertyList.get(4).getOwner()) {
			propertyList.get(2).setMutliplier(2);
			propertyList.get(3).setMutliplier(2);
			propertyList.get(4).setMutliplier(2);
		} else {
			propertyList.get(2).setMutliplier(1);
			propertyList.get(3).setMutliplier(1);
			propertyList.get(4).setMutliplier(1);
		}
		
		// Tokyo, Sydney
		if (propertyList.get(5).getOwner() == propertyList.get(6).getOwner()) {
			propertyList.get(5).setMutliplier(2);
			propertyList.get(6).setMutliplier(2);
		} else {
			propertyList.get(5).setMutliplier(1);
			propertyList.get(6).setMutliplier(1);
		}
		
		// Singapore, Sao Paulo
		if (propertyList.get(7).getOwner() == propertyList.get(8).getOwner()) {
			propertyList.get(7).setMutliplier(2);
			propertyList.get(8).setMutliplier(2);
		} else {
			propertyList.get(7).setMutliplier(1);
			propertyList.get(8).setMutliplier(1);
		}
		
		// Prague, Berlin
		if (propertyList.get(9).getOwner() == propertyList.get(10).getOwner()) {
			propertyList.get(9).setMutliplier(2);
			propertyList.get(10).setMutliplier(2);
		} else {
			propertyList.get(9).setMutliplier(1);
			propertyList.get(10).setMutliplier(1);
		}
		
		// Moscow, Geneva, Rome
		if (propertyList.get(11).getOwner() == propertyList.get(12).getOwner() 
				&& propertyList.get(12).getOwner() == propertyList.get(13).getOwner()) {
			propertyList.get(11).setMutliplier(2);
			propertyList.get(12).setMutliplier(2);
			propertyList.get(13).setMutliplier(2);
		} else {
			propertyList.get(11).setMutliplier(1);
			propertyList.get(12).setMutliplier(1);
			propertyList.get(13).setMutliplier(1);
		}
		
		// Prague, Berlin
		if (propertyList.get(14).getOwner() == propertyList.get(15).getOwner()) {
			propertyList.get(14).setMutliplier(2);
			propertyList.get(15).setMutliplier(2);
		} else {
			propertyList.get(14).setMutliplier(1);
			propertyList.get(15).setMutliplier(1);
		}
		
		// New York, Jakarta
		if (propertyList.get(16).getOwner() == propertyList.get(17).getOwner()) {
			propertyList.get(16).setMutliplier(2);
			propertyList.get(17).setMutliplier(2);
		} else {
			propertyList.get(16).setMutliplier(1);
			propertyList.get(17).setMutliplier(1);
		}
	}
	
	public Vector<Tiles> getTilesList() {
		return tilesList;
	}
	
	public Tiles getNextTiles(Tiles t) {
		int currIdx = tilesList.indexOf(t);
		int size = tilesList.size();
		return tilesList.get((currIdx + 1) % size);
	}
	
	public void render(Graphics g) {
		g.drawImage(gamePanel.getMapImage().getImage(), 0, 0, null);
		
		checkMultiplier();
		
		for (Property property : gamePanel.getPropertyList()) {
			property.render(g, gamePanel);
		}
	}
}
