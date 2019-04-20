package com.monopoly.model;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.monopoly.model.Tiles.CHARADIR;

public class Character implements Runnable {
	private Player player = null;
	private ImageIcon imgChara = null;
	
	private Thread thread;
	private boolean running = false;
	private int frame = 0;
	
	public Character(String charaName) {
		switch(charaName) {
		case "Claire": imgChara = new ImageIcon("assets/characters/char1.png"); break;
		case "Harold": imgChara = new ImageIcon("assets/characters/char2.png"); break;
		case "Barton": imgChara = new ImageIcon("assets/characters/char3.png"); break;
		case "Michael": imgChara = new ImageIcon("assets/characters/char4.png"); break;
		}
	}
	
	public ImageIcon getImgChara() {
		return imgChara;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
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
	
	public void tick() {
		frame++;
		frame = frame % 3;
	}

	public void run() {
		while(running) {
			tick();
			try {
				Thread.sleep(1000/10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void render(Graphics g) {
		int w = imgChara.getIconWidth() / 3;
		int h = imgChara.getIconHeight() / 5;
		
		int row = 0;
		if (running) {
			if (player.getCurrTile().charaDir == CHARADIR.LEFT) {
				row = 1;
			} else if (player.getCurrTile().charaDir == CHARADIR.RIGHT){
				row = 2;
			}
		} else {
			if (player.getCurrTile().charaDir == CHARADIR.LEFT) {
				row = 3;
				frame = 2;
			} else if (player.getCurrTile().charaDir == CHARADIR.RIGHT){
				row = 4;
				frame = 2;
			}
		}
		
		g.drawImage(imgChara.getImage(), player.getCurrTile().tileCoorX, player.getCurrTile().tileCoorY, 
				player.getCurrTile().tileCoorX + w, player.getCurrTile().tileCoorY + h, 
				frame*w, row*h, frame*w + w, row*h + h, null);
	}
	
}
