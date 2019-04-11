package com.monopoly.card;

import java.util.Vector;

import com.monopoly.main.GamePanel;
import com.monopoly.model.City;
import com.monopoly.model.Island;
import com.monopoly.model.Player;
import com.monopoly.model.Property;

public class ExplosionCard extends Card{
	
	public ExplosionCard(int col) {
		this.name = "Explosion";
		this.col = col;
	}

	public void effect(GamePanel gamePanel, Player user) {
		Vector<Property> tempProperty = new Vector<Property>();
		
		for (Property property : gamePanel.getPropertyList()) {
			if (property.getOwner() != null) {
				tempProperty.add(property);
			}
		}
		
		Property desProperty = tempProperty.get((int)(Math.random() * tempProperty.size()));
		
		if (desProperty instanceof City) {
			((City)desProperty).setLandBought(false);
			((City)desProperty).setHouseBought(false);
			((City)desProperty).setBuildingBought(false);
			((City)desProperty).setHotelBought(false);
			((City)desProperty).setLandmarkBought(false);
		} else if (desProperty instanceof Island){
			((Island)desProperty).getOwner().setIslandCount(
					((Island)desProperty).getOwner().getIslandCount() - 1);
		}
		
		desProperty.getOwner().removeProperty(desProperty);
		desProperty.setOwner(null);
	}
	
}
