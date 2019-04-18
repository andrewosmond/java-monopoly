package com.monopoly.model;

import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.monopoly.card.Card;
import com.monopoly.card.IndirectCard;
import com.monopoly.main.GamePanel;
import com.monopoly.window.CardWindow;

public class ChanceCardTile extends Tiles{
	private Random rand = new Random();

	public ChanceCardTile(GamePanel gamePanel, CHARADIR charaDir, int tileCoorX, int tileCoorY) {
		this.name = "Chance Card";
		this.gamePanel = gamePanel;
		this.charaDir = charaDir;
		this.tileCoorX = tileCoorX;
		this.tileCoorY = tileCoorY;
	}
	
	public int random(int min, int max) {
		return rand.nextInt(max-min+1) + min;
	}
	
	public void randomCard (Player p) {
		Vector<Card> cardList = gamePanel.getCardList();
		Card cardResult = null;
		
		/*
		CharityCard = 15
		DC Card = 15
		EQ Card = 8
		Escape Card = 15
		GoToJail Card = 12
		GotoMedical Card = 12 
		GoToStart Card = 15
		VIP Card = 8 
		 */
		
		int randomNum = random(1, 100);
		if (randomNum >= 1 && randomNum <= 15) {
			cardResult = cardList.get(0);
		} else if (randomNum >= 16 && randomNum <= 30) {
			cardResult = cardList.get(1);
		} else if (randomNum >= 31 && randomNum <= 38) {
			cardResult = cardList.get(2);
		} else if (randomNum >= 38 && randomNum <= 52) {
			cardResult = cardList.get(3);
		} else if (randomNum >= 53 && randomNum <= 64) {
			cardResult = cardList.get(4);
		} else if (randomNum >= 65 && randomNum <= 76) {
			cardResult = cardList.get(5);
		} else if (randomNum >= 77 && randomNum <= 91) {
			cardResult = cardList.get(6);
		} else if (randomNum >= 92 && randomNum <= 100) {
			cardResult = cardList.get(7);
		}
		
		CardWindow.getInstance().view(cardResult, gamePanel);
		
		if (cardResult instanceof IndirectCard) {
			// Have no card, save the card directly
			if (p.getCard() == null) {
				p.setCard(cardResult);
			// User may choose to keep/discard the card
			} else {
				int input = JOptionPane.showConfirmDialog(null, "Do you want to keep " + cardResult.getName() + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if (input == 0) {
					p.setCard(cardResult);
				}
			}
		} else {
			cardResult.effect(gamePanel, p);
		}
	}

	@Override
	public void event(Player player) {
		// Player gets random card
		randomCard(player);
	}
}
