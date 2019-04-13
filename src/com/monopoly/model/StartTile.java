package com.monopoly.model;

import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;

public class StartTile extends Tiles{
	private int salary = 300000;
	
	public StartTile(GamePanel gamePanel, CHARADIR charaDir, int tileCoorX, int tileCoorY) {
		this.name = "Start";
		this.gamePanel = gamePanel;
		this.charaDir = charaDir;
		this.tileCoorX = tileCoorX;
		this.tileCoorY = tileCoorY;
	}
	
	public void giveSalary(Player p) {
		p.setMoney(p.getMoney() + salary);
	}
	
	public void giveDoubleSalary(Player p) {
		p.setMoney(p.getMoney() + salary * 2);
	}

	@Override
	public void event(Player player) {
		// Double salary effect resolves directly
		giveDoubleSalary(player);
		System.out.println(player.getName() + " received double salary!");
		gamePanel.setGameState(GAMESTATE.TURNEND);
		
	}
}
