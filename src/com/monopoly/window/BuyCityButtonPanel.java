package com.monopoly.window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;
import com.monopoly.model.City;
import com.monopoly.model.Player;

@SuppressWarnings("serial")
public class BuyCityButtonPanel extends JPanel implements ActionListener {
	private JButton btnBuy = new JButton("Buy");
	private BuyCityPanel buyCityPanel = null;
	private BuyCityWindow buyCityWindow = null;
	private GamePanel gamePanel = null;
	
	public BuyCityButtonPanel(BuyCityPanel buyCityPanel, BuyCityWindow buyCityWindow, GamePanel gamePanel) {
		setLayout(new FlowLayout());
		this.buyCityPanel = buyCityPanel;
		this.buyCityWindow = buyCityWindow;
		this.gamePanel = gamePanel;
		add(btnBuy);
		btnBuy.addActionListener(this);
	}
	
	public JButton getBtnBuy() {
		return btnBuy;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuy) {
			City city = buyCityPanel.getCity();
			Player player = buyCityPanel.getPlayer();
			player.setMoney(player.getMoney() - buyCityPanel.getTotalPrice());
			city.setOwner(player);
			
			if (!player.getPropertyList().contains(city)) {
				player.addProperty(city);
			}
			
			if (!city.isLandBought()) city.setLandBought(true);
			if (buyCityPanel.isChbHouseSelected()) city.setHouseBought(true);
			if (buyCityPanel.isChbBuildingSelected()) city.setBuildingBought(true);
			if (buyCityPanel.isChbHotelSelected()) city.setHotelBought(true);
			
			gamePanel.setGameState(GAMESTATE.TURNEND);
			buyCityWindow.getFrame().setVisible(false);
		}
	}
	
}
