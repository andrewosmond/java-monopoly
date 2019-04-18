package com.monopoly.card;

import com.monopoly.main.GamePanel;
import com.monopoly.model.Player;

public class CharityCard extends Card {

	public CharityCard(int col) {
		this.name = "Charity";
		this.col = col;
	}

	public void effect(GamePanel gamePanel, Player user) {
		long money;

		for (Player player : gamePanel.getPlayerList()) {
			if (user != player) {
				if (player.isBankrupt()) continue;
				money = player.getMoney() / 10;
				player.setMoney(player.getMoney() - money);
				user.setMoney(user.getMoney() + money);
			}
		}

	}
}
