package com.monopoly.model;

import com.monopoly.main.GamePanel;

public abstract class Tiles {
	protected String name;
	protected GamePanel gamePanel = null;
	public static enum CHARADIR{
		LEFT, RIGHT;
	}
	protected CHARADIR charaDir;
	protected int tileCoorX;
	protected int tileCoorY;
	public abstract void event(Player player);
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public CHARADIR getCharaDir() {
		return charaDir;
	}

	public void setCharaDir(CHARADIR charaDir) {
		this.charaDir = charaDir;
	}

	public int getTileCoorX() {
		return tileCoorX;
	}

	public void setTileCoorX(int tileCoorX) {
		this.tileCoorX = tileCoorX;
	}

	public int getTileCoorY() {
		return tileCoorY;
	}

	public void setTileCoorY(int tileCoorY) {
		this.tileCoorY = tileCoorY;
	}
}