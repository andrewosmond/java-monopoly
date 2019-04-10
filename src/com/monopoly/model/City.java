package com.monopoly.model;

import java.awt.Graphics;

import com.monopoly.main.GamePanel;

public class City extends Property {
	private long landPrice;
	private long housePrice;
	private long buildingPrice;
	private long hotelPrice;
	private long landmarkPrice;

	private boolean landBought = false;
	private boolean houseBought = false;
	private boolean buildingBought = false;
	private boolean hotelBought = false;
	private boolean landmarkBought = false;

	public City(DIRECTION direction, int coorX, int coorY, int tilesRow, int tilesCol, String name, long landPrice,
			long housePrice, long buildingPrice, long hotelPrice, long landmarkPrice) {
		this.direction = direction;
		this.coorX = coorX;
		this.coorY = coorY;
		this.owner = null;
		this.tilesRow = tilesRow;
		this.tilesCol = tilesCol;
		this.name = name;
		this.landPrice = landPrice;
		this.housePrice = housePrice;
		this.buildingPrice = buildingPrice;
		this.hotelPrice = hotelPrice;
		this.landmarkPrice = landmarkPrice;
	}

	public void buy(Player p) {
		owner = p;
		owner.addProperty(this);
	}

	public void sell() {
		owner.setMoney(owner.getMoney() + getSellValue());
		owner.removeProperty(this);
		owner = null;
		landBought = false;
		houseBought = false;
		buildingBought = false;
		hotelBought = false;
		landmarkBought = false;
	}

	public long getPropertyValue() {
		long res = 0;
		res += (landBought) ? landPrice : 0;
		res += (houseBought) ? housePrice : 0;
		res += (buildingBought) ? buildingPrice : 0;
		res += (hotelBought) ? hotelPrice : 0;
		res += (landmarkBought) ? landmarkPrice : 0;
		return res;
	}

	public boolean isTakeOverAble() {
		return !landmarkBought;
	}

	public long getSellValue() {
		return (long) ((double) getPropertyValue() * 0.75);
	}

	public long getTakeOverFee() {
		return (long) ((double) getPropertyValue() * 2.00);
	}

	public long getRentFee() {
		return (long) ((double) getPropertyValue() * 1.25) * multiplier;
	}

	public long getLandPrice() {
		return landPrice;
	}

	public long getHousePrice() {
		return housePrice;
	}

	public long getBuildingPrice() {
		return buildingPrice;
	}

	public long getHotelPrice() {
		return hotelPrice;
	}

	public long getLandmarkPrice() {
		return landmarkPrice;
	}

	public boolean isLandBought() {
		return landBought;
	}

	public void setLandBought(boolean landBought) {
		this.landBought = landBought;
	}

	public boolean isHouseBought() {
		return houseBought;
	}

	public void setHouseBought(boolean houseBought) {
		this.houseBought = houseBought;
	}

	public boolean isBuildingBought() {
		return buildingBought;
	}

	public void setBuildingBought(boolean buildingBought) {
		this.buildingBought = buildingBought;
	}

	public boolean isHotelBought() {
		return hotelBought;
	}

	public void setHotelBought(boolean hotelBought) {
		this.hotelBought = hotelBought;
	}

	public boolean isLandmarkBought() {
		return landmarkBought;
	}

	public void setLandmarkBought(boolean landmarkBought) {
		this.landmarkBought = landmarkBought;
	}

	public void render(Graphics g, GamePanel gamePanel) {
		int x = gamePanel.getPropertyImage().getIconWidth() / 4;
		int y = gamePanel.getPropertyImage().getIconHeight() / 12;

		if (owner == null) return;

		int playerColor = gamePanel.getPlayerList().indexOf(owner);
		int landIdx = 0;
		int houseIdx = 0;
		int buildingIdx = 0;
		int hotelIdx = 0;
		int landmarkIdx = 0;

		if (direction == DIRECTION.LEFT) {
			landIdx = 6;
			houseIdx = 7;
			buildingIdx = 8;
			hotelIdx = 9;
			landmarkIdx = 10;
		} else if (direction == DIRECTION.RIGHT) {
			landIdx = 0;
			houseIdx = 1;
			buildingIdx = 2;
			hotelIdx = 3;
			landmarkIdx = 4;
		}

		if (landmarkBought) {
			g.drawImage(gamePanel.getPropertyImage().getImage(), coorX, coorY, coorX + x, coorY + y, x * playerColor,
					y * landmarkIdx, x * playerColor + x, y * landmarkIdx + y, null);
		} else if (hotelBought || buildingBought || houseBought) {
			if (hotelBought) {
				g.drawImage(gamePanel.getPropertyImage().getImage(), coorX, coorY, coorX + x, coorY + y, x * playerColor,
						y * hotelIdx, x * playerColor + x, y * hotelIdx + y, null);
			}
			if (buildingBought) {
				g.drawImage(gamePanel.getPropertyImage().getImage(), coorX, coorY, coorX + x, coorY + y, x * playerColor,
						y * buildingIdx, x * playerColor + x, y * buildingIdx + y, null);
			}
			if (houseBought) {
				g.drawImage(gamePanel.getPropertyImage().getImage(), coorX, coorY, coorX + x, coorY + y, x * playerColor,
						y * houseIdx, x * playerColor + x, y * houseIdx + y, null);
			}
		} else if (landBought) {
			g.drawImage(gamePanel.getPropertyImage().getImage(), coorX, coorY, coorX + x, coorY + y, x * playerColor,
					y * landIdx, x * playerColor + x, y * landIdx + y, null);
		}
	}
}
