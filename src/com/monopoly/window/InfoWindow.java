package com.monopoly.window;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import com.monopoly.model.City;
import com.monopoly.model.Island;
import com.monopoly.model.Property;
import com.monopoly.utility.MoneyFormatter;

@SuppressWarnings("serial")
public class InfoWindow extends JFrame {
	public static enum STATE{
		CITY, ISLAND, CHANCECARD, CHEST, GOTOJAIL, JAIL, MEDICALBILL, START
	};
	
	private static InfoWindow instance = new InfoWindow();
	private STATE currState = null;
	private Property property = null;
	
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
	}
	
	public static InfoWindow getInstance() {
		return instance;
	}

	public void setState(STATE currState) {
		this.currState = currState;
	}
	
	public void view() {
		switch(currState) {
		case CHANCECARD:
			setTitle("Chance Card Information");
			break;
		case CHEST:
			setTitle("Chest Information");
			break;
		case GOTOJAIL:
			setTitle("Go to Jail Information");
			break;
		case JAIL:
			setTitle("Jail Information");
			break;
		case MEDICALBILL:
			setTitle("Medical Bill Information");
			break;
		case START:
			setTitle("Start Information");
			break;
		default: break;
		}
		repaint();
		setVisible(true);
	}

	public void view(Property property) {
		this.property = property;
		if (property instanceof City) {
			currState = STATE.CITY;
			setTitle("City Information");
		} else if (property instanceof Island) {
			currState = STATE.ISLAND;
			setTitle("Island Information");
		}
		repaint();
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		int x = tilesSprite.getIconWidth() / 6;
		int y = tilesSprite.getIconHeight() / 5;
		
		switch(currState) {
		case CITY:
			g.drawImage(cityWindow.getImage(), 0, 16, null);
			
			g.drawImage(tilesSprite.getImage(), 44, 115, 44+x, 115+y, 
					x*property.getTilesCol(), y*property.getTilesRow(), 
					x*property.getTilesCol() + x, y*property.getTilesRow() + y, null);
			
			City city = (City)property;
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
			
			Island island = (Island)property;
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
		
		g.dispose();
	}
}
