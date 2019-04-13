package com.monopoly.window;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.monopoly.model.City;
import com.monopoly.model.Player;
import com.monopoly.utility.MoneyFormatter;

@SuppressWarnings("serial")
public class BuyCityPanel extends JPanel implements ItemListener {
	private BuyCityWindow buyCityWindow = null;
	private City city = null;
	private Player player = null;
	
	private JCheckBox chbLand = new JCheckBox("Land");
	private JCheckBox chbHouse = new JCheckBox("House");
	private JCheckBox chbBuilding = new JCheckBox("Building");
	private JCheckBox chbHotel = new JCheckBox("Hotel");

	private JLabel lblCityName = new JLabel();
	private JLabel lblLandStatus = new JLabel();
	private JLabel lblHouseStatus = new JLabel();
	private JLabel lblBuildingStatus = new JLabel();
	private JLabel lblHotelStatus = new JLabel();
	private JLabel lblLandPrice = new JLabel();
	private JLabel lblHousePrice = new JLabel();
	private JLabel lblBuildingPrice = new JLabel();
	private JLabel lblHotelPrice = new JLabel();
	private JLabel lblTotalPrice = new JLabel();

	public BuyCityPanel(BuyCityWindow buyCityWindow) {
		this.buyCityWindow = buyCityWindow;
		setLayout(new GridLayout(5, 4));
		
		add(lblCityName);
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		
		add(lblLandStatus);
		add(lblHouseStatus);
		add(lblBuildingStatus);
		add(lblHotelStatus);

		add(chbLand);
		add(chbHouse);
		add(chbBuilding);
		add(chbHotel);

		add(lblLandPrice);
		add(lblHousePrice);
		add(lblBuildingPrice);
		add(lblHotelPrice);

		add(new JLabel());
		add(new JLabel("Total Price"));
		add(lblTotalPrice);
		add(new JLabel());

		chbLand.setEnabled(false);

		chbHouse.addItemListener(this);
		chbBuilding.addItemListener(this);
		chbHotel.addItemListener(this);
	}
	
	public void init(City city, Player player) {
		this.city = city;
		this.player = player;
		lblCityName.setText(city.getName());
		
		chbLand.setSelected(false);
		chbHouse.setSelected(false);
		chbBuilding.setSelected(false);
		chbHotel.setSelected(false);
		
		lblTotalPrice.setText(MoneyFormatter.getFormat(getTotalPrice()));
		if (getTotalPrice() > player.getMoney())
			buyCityWindow.getButtonPanel().getBtnBuy().setEnabled(false);
		else 
			buyCityWindow.getButtonPanel().getBtnBuy().setEnabled(true);
		
		if (city.isLandBought())
			lblLandStatus.setText("Owned");
		else
			lblLandStatus.setText("Required");
		
		if (city.isHouseBought()) {
			lblHouseStatus.setText("Owned");
			chbHouse.setEnabled(false);
		} else {
			lblHouseStatus.setText("Not Owned");
			chbHouse.setEnabled(true);
		}
			
		if (city.isBuildingBought()) {
			lblBuildingStatus.setText("Owned");
			chbBuilding.setEnabled(false);
		} else {
			lblBuildingStatus.setText("Not Owned");
			chbBuilding.setEnabled(true);
		}	
			
		if (city.isHotelBought()) {
			lblHotelStatus.setText("Owned");
			chbHotel.setEnabled(false);
		} else {
			lblHotelStatus.setText("Not Owned");
			chbHotel.setEnabled(true);
		}
			
		lblLandPrice.setText(MoneyFormatter.getFormat(city.getLandPrice()));
		lblHousePrice.setText(MoneyFormatter.getFormat(city.getHousePrice()));
		lblBuildingPrice.setText(MoneyFormatter.getFormat(city.getBuildingPrice()));
		lblHotelPrice.setText(MoneyFormatter.getFormat(city.getHotelPrice()));
		lblTotalPrice.setText(MoneyFormatter.getFormat(getTotalPrice()));
	}
	
	public City getCity() {
		return city;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean isChbHouseSelected() {
		return chbHouse.isSelected();
	}
	
	public boolean isChbBuildingSelected() {
		return chbBuilding.isSelected();
	}
	
	public boolean isChbHotelSelected() {
		return chbHotel.isSelected();
	}
	
	public long getTotalPrice() {
		long result = 0;
		if (!city.isLandBought()) result += city.getLandPrice();
		if (chbHouse.isSelected()) result += city.getHousePrice();
		if (chbBuilding.isSelected()) result += city.getBuildingPrice();
		if (chbHotel.isSelected()) result += city.getHotelPrice();
		return result;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == chbHouse || e.getSource() == chbBuilding || e.getSource() == chbHotel) {
			lblTotalPrice.setText(MoneyFormatter.getFormat(getTotalPrice()));
			
			if (getTotalPrice() > player.getMoney())
				buyCityWindow.getButtonPanel().getBtnBuy().setEnabled(false);
			else 
				buyCityWindow.getButtonPanel().getBtnBuy().setEnabled(true);
		}
	}
}
