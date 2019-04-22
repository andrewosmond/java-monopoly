package com.monopoly.window;

import javax.swing.JFrame;
import com.monopoly.model.City;
import com.monopoly.model.Island;
import com.monopoly.model.Property;

public class InfoWindow extends Window {
	public static enum STATE{
		CITY, ISLAND, CHANCECARD, CHEST, GOTOJAIL, JAIL, MEDICALBILL, START
	};
	
	private static InfoWindow instance = new InfoWindow();
	private InfoPanel infoPanel = null;
	private STATE currState = null;
	
	private InfoWindow() {
		frame = new JFrame();
		frame.setSize(500, 400);
		infoPanel = new InfoPanel();
		frame.add(infoPanel);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
	
	public static InfoWindow getInstance() {
		return instance;
	}

	public void setState(STATE currState) {
		this.currState = currState;
	}
	
	public STATE getState() {
		return currState;
	}
	
	public void view() {
		switch(currState) {
		case CHANCECARD:
			frame.setTitle("Chance Card Information");
			break;
		case CHEST:
			frame.setTitle("Chest Information");
			break;
		case GOTOJAIL:
			frame.setTitle("Go to Jail Information");
			break;
		case JAIL:
			frame.setTitle("Jail Information");
			break;
		case MEDICALBILL:
			frame.setTitle("Medical Bill Information");
			break;
		case START:
			frame.setTitle("Start Information");
			break;
		default: break;
		}
		infoPanel.repaint();
		frame.setVisible(true);
	}

	public void view(Property property) {
		infoPanel.setProperty(property);
		if (property instanceof City) {
			currState = STATE.CITY;
			frame.setTitle("City Information");
		} else if (property instanceof Island) {
			currState = STATE.ISLAND;
			frame.setTitle("Island Information");
		}
		infoPanel.repaint();
		frame.setVisible(true);
	}
}
