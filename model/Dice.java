package com.monopoly.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;

public class Dice implements Runnable{
	private GamePanel gamePanel = null;
	private int firstValue = 1;
	private int secondValue = 1;
	private Random rand = new Random();
	private boolean running = false;
	private Thread thread = null;
	
	public Dice(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public int random(int min, int max) {
		return rand.nextInt(max-min+1) + min;
	}
	
	public void roll() {
		firstValue = random(1,6);
		secondValue = random(1,6);
	}
	
	public boolean isDouble() {
		return (firstValue == secondValue);
	}

	public int getFirstValue() {
		return firstValue;
	}

	public int getSecondValue() {
		return secondValue;
	}
	
	public int getSum() {
		return firstValue + secondValue;
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 20));
		g.drawString("Dice", 1126, 24);
		
		int w = gamePanel.getDiceImage().getIconWidth() / 6;
		int h = gamePanel.getDiceImage().getIconHeight();
		
		g.drawImage(gamePanel.getDiceImage().getImage(), 1090, 35, 1090+w, 35+h, 
				(firstValue-1)*w, 0, (firstValue-1)*w + w, h, null);
		
		g.drawImage(gamePanel.getDiceImage().getImage(), 1150, 35, 1150+w, 35+h, 
				(secondValue-1)*w, 0, (secondValue-1)*w + w, h, null);
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void end() {
		if (!running) return;
		running = false;
	}

	public void run() {
		long timer = System.currentTimeMillis();
		
		while(running) {
			roll();
			if (System.currentTimeMillis() - timer > 1500) {
				end();
				if (gamePanel.getGameState() == GAMESTATE.ROLLING) {
					gamePanel.setDiceRolled(getSum());
					gamePanel.setGameState(GAMESTATE.MOVE);
				}
			}
			
			try {
				Thread.sleep(1000/30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
