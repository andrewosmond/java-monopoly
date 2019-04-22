package com.monopoly.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.monopoly.model.City;
import com.monopoly.model.Island;
import com.monopoly.model.Property;
import com.monopoly.utility.MoneyFormatter;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel {
	private Property property = null;
	private ImageIcon cityWindow = new ImageIcon("assets/window/cityInfoWindow.png");
	private ImageIcon islandWindow = new ImageIcon("assets/window/islandInfoWindow.png");
	private ImageIcon tilesSprite = new ImageIcon("assets/tiles/tiles.png");
	private ImageIcon chanceCardWindow = new ImageIcon("assets/window/chanceCardWindow.png");
	private ImageIcon chestWindow = new ImageIcon("assets/window/chestWindow.png");
	private ImageIcon goToJailWindow = new ImageIcon("assets/window/goToJailWindow.png");
	private ImageIcon jailWindow = new ImageIcon ("assets/window/jailWindow.png");
	private ImageIcon medicalBillWindow = new ImageIcon ("assets/window/medicalBillWindow.png");
	private ImageIcon startWindow = new ImageIcon("assets/window/startWindow.png");
	
	public InfoPanel() {
		setLayout(null);
	}
	
	public void setProperty(Property property) {
		this.property = property;
	}
	
	@Override
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
		
		g.setColor(Color.BLACK);
		
		switch(InfoWindow.getInstance().getState()) {
		case CITY:
			g.drawImage(cityWindow.getImage(), 0, 0, null);
			
			g.drawImage(tilesSprite.getImage(), 44, 99, 44+x, 99+y, 
					x*property.getTilesCol(), y*property.getTilesRow(), 
					x*property.getTilesCol() + x, y*property.getTilesRow() + y, null);
			
			City city = (City)property;
			g.setFont(new Font("Calibri", Font.PLAIN, 13));
			g.drawString(MoneyFormatter.getFormat(city.getLandPrice()), 385, 138);
			g.drawString(MoneyFormatter.getFormat(city.getHousePrice()), 385, 160);
			g.drawString(MoneyFormatter.getFormat(city.getBuildingPrice()), 385, 185);
			g.drawString(MoneyFormatter.getFormat(city.getHotelPrice()), 385, 211);
			g.drawString(MoneyFormatter.getFormat(city.getLandmarkPrice()), 385, 236);
			
			g.setFont(new Font("Calibri", Font.PLAIN, 20));
			if (!city.isTakeOverAble()) {
				g.drawString("Tidak bisa ambil alih", 265, 295);
			} else {
				if (city.getTakeOverFee() == 0) {
					g.drawString(MoneyFormatter.getFormat(city.getTakeOverFee()), 347, 295);
				} else {
					g.drawString(MoneyFormatter.getFormat(city.getTakeOverFee()), 320, 295);
				}
			}
			
			if (city.getRentFee() == 0) {
				g.drawString(MoneyFormatter.getFormat(city.getRentFee()), 347, 354);
			} else {
				g.drawString(MoneyFormatter.getFormat(city.getRentFee()), 320, 354);
			}
			break;
		case ISLAND:
			g.drawImage(islandWindow.getImage(), 0, 0, null);
			
			g.drawImage(tilesSprite.getImage(), 44, 99, 44+x, 99+y, 
					x*property.getTilesCol(), y*property.getTilesRow(), 
					x*property.getTilesCol() + x, y*property.getTilesRow() + y, null);
			
			Island island = (Island)property;
			g.setFont(new Font("Calibri", Font.PLAIN, 13));
			g.drawString(MoneyFormatter.getFormat(island.getPrice()), 375, 119);
			g.drawString(MoneyFormatter.getFormat(island.getRent() * 1), 385, 245);
			g.drawString(MoneyFormatter.getFormat(island.getRent() * 2), 385, 270);
			g.drawString(MoneyFormatter.getFormat(island.getRent() * 3), 385, 297);
			
			g.setFont(new Font("Calibri", Font.PLAIN, 20));
			if (island.getRentFee() == 0) {
				g.drawString(MoneyFormatter.getFormat(island.getRentFee()), 347, 354);
			} else {
				g.drawString(MoneyFormatter.getFormat(island.getRentFee()), 335, 354);
			}
			break;
		case CHANCECARD:
			g.drawImage(chanceCardWindow.getImage(), 0, 0, null);
			break;
		case CHEST:
			g.drawImage(chestWindow.getImage(), 0, 0, null);
			break;
		case GOTOJAIL:
			g.drawImage(goToJailWindow.getImage(), 0, 0, null);
			break;
		case JAIL:
			g.drawImage(jailWindow.getImage(), 0, 0, null);
			break;
		case MEDICALBILL:
			g.drawImage(medicalBillWindow.getImage(), 0, 0, null);
			break;
		case START:
			g.drawImage(startWindow.getImage(), 0, 0, null);
			break;
		}
		
		g.dispose();
	}
}
