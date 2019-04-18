package com.monopoly.window;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class LogoAnimation {
	private ImageIcon logo = new ImageIcon("assets/background/logo.png");
	
	private int Opacity;
	private int tick;
	
	private final int FADE_IN = 60;
	private final int FADE_OUT = 60;
	private final int DURATION = 120;
	
	public LogoAnimation() {
		
	}

	public void init() {
		tick = 0;
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int render() {
		tick++;
		if (tick < FADE_IN) {
			Opacity = (int) (255 - 255 * (1.0 * tick / FADE_IN));
			if (Opacity < 0) {
				Opacity = 0;
			}
		}
		if (tick > FADE_IN + DURATION) {
			Opacity = (int) (255 * (1.0 * tick - FADE_IN - DURATION) / FADE_OUT);
			if (Opacity > 255) {
				Opacity = 255;
				}
		}
		if (tick > FADE_IN + DURATION + FADE_OUT) {
			return 1;
		}
		return 0;
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, 1280, 720);
		g.drawImage(logo.getImage(), 0, 0, null);
		g.setColor(new Color(0, 0, 0, Opacity));
		g.fillRect(0, 0, 1280, 720);
	}
}