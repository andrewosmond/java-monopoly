package com.monopoly.window;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import com.monopoly.model.City;
import com.monopoly.model.Island;
import com.monopoly.model.Property;
import com.monopoly.utility.MoneyFormatter;

public class InfoWindow extends JFrame {
	private static InfoWindow instance = new InfoWindow();
	public static enum STATE{
		CITY,
		ISLAND,
		CHANCECARD,
		CHEST,
		GOTOJAIL,
		JAIL,
		MEDICALBILL,
		START
	};
	private static STATE currState = null;
	private static Property property = null;
	
	private ImageIcon cityWindow = new ImageIcon("assets/cityInfoWindow.png");
	private ImageIcon islandWindow = new ImageIcon("assets/islandInfoWindow.png");
	private ImageIcon tilesSprite = new ImageIcon("assets/tiles.png");
	private ImageIcon chanceCardWindow = new ImageIcon("assets/chanceCardWindow.png");
	private ImageIcon chestWindow = new ImageIcon("assets/chestWindow.png");
	private ImageIcon goToJailWindow = new ImageIcon("assets/goToJailWindow.png");
	private ImageIcon jailWindow = new ImageIcon ("assets/jailWindow.png");
	private ImageIcon medicalBillWindow = new ImageIcon ("assets/medicalBillWindow.png");
	private ImageIcon startWindow = new ImageIcon("assets/startWindow.png");
	
	private InfoWindow() {
		setSize(500, 400);
		setLocationRelativeTo(null);	
		setResizable(false);
		setVisible(true);
	}
	
	public static InfoWindow getInstance() {
		return instance;
	}

	public static void setState(STATE currState) {
		InfoWindow.currState = currState;
	}
	
	public static void view() {
		switch(currState) {
		case CHANCECARD:
			instance.setTitle("Chance Card Information");
			break;
		case CHEST:
			instance.setTitle("Chest Information");
			break;
		case GOTOJAIL:
			instance.setTitle("Go to Jail Information");
			break;
		case JAIL:
			instance.setTitle("Jail Information");
			break;
		case MEDICALBILL:
			instance.setTitle("Medical Bill Information");
			break;
		case START:
			instance.setTitle("Start Information");
			break;
		}
		instance.repaint();
		instance.setVisible(true);
	}

	public static void view(Property property) {
		InfoWindow.property = property;
		if (property instanceof City) {
			currState = currState.CITY;
			instance.setTitle("City Information");
			instance.repaint();
			instance.setVisible(true);
		} else if (property instanceof Island) {
			currState = currState.ISLAND;
			instance.setTitle("Island Information");
			instance.repaint();
			instance.setVisible(true);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		int x = tilesSprite.getIconWidth() / 6;
		int y = tilesSprite.getIconHeight() / 5;
		
		switch(currState) {
		case CITY:
			g.drawImage(cityWindow.getImage(), 0, 16, null);
			
			g.drawImage(tilesSprite.getImage(), 44, 115, 44+x, 115+y, 
					x*property.getTilesCol(), y*property.getTilesRow(), 
					x*property.getTilesCol() + x, y*property.getTilesRow() + y, null);
			
			City city = (City)InfoWindow.property;
			g.setFont(new Font("Calibri", Font.PLAIN, 13));
			g.drawString(MoneyFormatter.getFormat(city.getLandPrice()), 385, 154);
			g.drawString(MoneyFormatter.getFormat(city.getHousePrice()), 385, 176);
			g.drawString(MoneyFormatter.getFormat(city.getBuildingPrice()), 385, 201);
			g.drawString(MoneyFormatter.getFormat(city.getHotelPrice()), 385, 227);
			g.drawString(MoneyFormatter.getFormat(city.getLandmarkPrice()), 385, 252);
			
			g.setFont(new Font("Calibri", Font.PLAIN, 20));
			if (!city.isTakeOverAble()) {
				g.drawString("Tidak bisa ambil alih", 265, 311);
			} else {
				if (city.getTakeOverFee() == 0) {
					g.drawString(MoneyFormatter.getFormat(city.getTakeOverFee()), 347, 311);
				} else {
					g.drawString(MoneyFormatter.getFormat(city.getTakeOverFee()), 320, 311);
				}
			}
			
			if (city.getRentFee() == 0) {
				g.drawString(MoneyFormatter.getFormat(city.getRentFee()), 347, 370);
			} else {
				g.drawString(MoneyFormatter.getFormat(city.getRentFee()), 320, 370);
			}
			break;
		case ISLAND:
			g.drawImage(islandWindow.getImage(), 0, 16, null);
			
			g.drawImage(tilesSprite.getImage(), 44, 115, 44+x, 115+y, 
					x*property.getTilesCol(), y*property.getTilesRow(), 
					x*property.getTilesCol() + x, y*property.getTilesRow() + y, null);
			
			Island island = (Island)InfoWindow.property;
			g.setFont(new Font("Calibri", Font.PLAIN, 13));
			g.drawString(MoneyFormatter.getFormat(island.getPrice()), 375, 135);
			g.drawString(MoneyFormatter.getFormat(island.getRent() * 1), 385, 261);
			g.drawString(MoneyFormatter.getFormat(island.getRent() * 2), 385, 286);
			g.drawString(MoneyFormatter.getFormat(island.getRent() * 3), 385, 313);
			
			g.setFont(new Font("Calibri", Font.PLAIN, 20));
			if (island.getRentFee() == 0) {
				g.drawString(MoneyFormatter.getFormat(island.getRentFee()), 347, 370);
			} else {
				g.drawString(MoneyFormatter.getFormat(island.getRentFee()), 335, 370);
			}
			break;
		case CHANCECARD:
			g.drawImage(chanceCardWindow.getImage(), 0, 16, null);
			break;
		case CHEST:
			g.drawImage(chestWindow.getImage(), 0, 16, null);
			break;
		case GOTOJAIL:
			g.drawImage(goToJailWindow.getImage(), 0, 16, null);
			break;
		case JAIL:
			g.drawImage(jailWindow.getImage(), 0, 16, null);
			break;
		case MEDICALBILL:
			g.drawImage(medicalBillWindow.getImage(), 0, 16, null);
			break;
		case START:
			g.drawImage(startWindow.getImage(), 0, 16, null);
			break;
		}
		
	}
}
