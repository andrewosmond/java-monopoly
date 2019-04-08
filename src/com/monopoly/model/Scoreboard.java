package com.monopoly.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import com.monopoly.main.GamePanel;
import com.monopoly.utility.MoneyFormatter;

public class Scoreboard {
	private GamePanel gamePanel = null;
	
	public Scoreboard(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void setColor(Graphics g, Player p) {
		int playerIdx = gamePanel.getPlayerList().indexOf(p);
		switch(playerIdx) {
		case 0: //Red
			g.setColor(new Color(235, 148, 134));
			break;
		case 1: //Blue
			g.setColor(new Color(151, 167, 179));
			break;
		case 2: //Green
			g.setColor(new Color(202, 231, 185));
			break;
		case 3: //Yellow
			g.setColor(new Color(243, 222, 138));
			break;
		}
	}
	
	public void render(Graphics g) {
		Vector<Player> playerList = (Vector<Player>)gamePanel.getPlayerList().clone();
		
		Collections.sort(playerList, new Comparator<Player>() {
			public int compare(Player p1, Player p2) {
				return p1.getTotalAssets() > p2.getTotalAssets() ? -1 : 1;
			}
		});
		
		for(int i = 0; i < playerList.size(); i++) {
			Player player = playerList.get(i);
			
			setColor(g, player);
			//Panel
			g.fillRect(1016, 175 + i*75, 258, 75);
			
			g.setColor(Color.WHITE);
			//Image
			g.fillRect(1016+5, 175+5 + i*75, 65, 65);
			
			//Text
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.setColor(Color.BLACK);
			g.drawString("#"+(i+1)+" - "+player.getName(), 1093, 192 + i*75);
			g.drawString("Money : "+MoneyFormatter.getFormat(player.getMoney()), 1093, 192+17 + i*75);
			g.drawString("Assets : "+MoneyFormatter.getFormat(player.getTotalAssets()), 1093, 192+34 + i*75);
			if (player.getCard() == null) {
				g.drawString("Card     : -", 1093, 192+51 + i*75);
			} else {
				g.drawString("Card     : "+player.getCard().getName(), 1093, 192+51 + i*75);
			}
		}		
	}
}
