package com.monopoly.model;

public class City extends Property{
	private long landPrice;
	private long housePrice;
	private long buildingPrice;
	private long hotelPrice;
	private long landmarkPrice;
	
	private boolean isLandBought = false;
	private boolean isHouseBought = false;
	private boolean isBuildingBought = false;
	private boolean isHotelBought = false;
	private boolean isLandmarkBought = false;
	
	public City(int tilesRow, int tilesCol, String name, long landPrice, long housePrice, long buildingPrice, long hotelPrice, long landmarkPrice) {
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
		isLandBought = false;
		isHouseBought = false;
		isBuildingBought = false;
		isHotelBought = false;
		isLandmarkBought = false;
	}
	
	public long countTotalPrice() {
		long res = 0;
		res += (isLandBought) ? landPrice : 0;
		res += (isHouseBought) ? housePrice : 0;
		res += (isBuildingBought) ? buildingPrice : 0;
		res += (isHotelBought) ? hotelPrice : 0;
		res += (isLandmarkBought) ? landmarkPrice : 0;
		return res;
	}
	
	public boolean isTakeOverAble() {
		return !isLandmarkBought;
	}
	
	public long getSellValue() {
		return (long)((double)countTotalPrice() * 0.75);
	}
	
	public long getTakeOverFee() {
		return (long)((double)countTotalPrice() * 2.00);
	}
	
	public long getRentFee() {
		return (long)((double)countTotalPrice() * 1.25);
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
		return isLandBought;
	}

	public void setLandBought(boolean isLandBought) {
		this.isLandBought = isLandBought;
	}

	public boolean isHouseBought() {
		return isHouseBought;
	}

	public void setHouseBought(boolean isHouseBought) {
		this.isHouseBought = isHouseBought;
	}

	public boolean isBuildingBought() {
		return isBuildingBought;
	}

	public void setBuildingBought(boolean isBuildingBought) {
		this.isBuildingBought = isBuildingBought;
	}

	public boolean isHotelBought() {
		return isHotelBought;
	}

	public void setHotelBought(boolean isHotelBought) {
		this.isHotelBought = isHotelBought;
	}

	public boolean isLandmarkBought() {
		return isLandmarkBought;
	}

	public void setLandmarkBought(boolean isLandmarkBought) {
		this.isLandmarkBought = isLandmarkBought;
	}
}
