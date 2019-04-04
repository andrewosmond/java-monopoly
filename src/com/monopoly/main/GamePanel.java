package com.monopoly.main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.monopoly.database.Database;

public class GamePanel extends JPanel {
	
	// assets
	private ImageIcon img = new ImageIcon("assets/map.png");
	
	// coordinate
	private int coorX;
	private int coorY;
	
	// View buttons : comment setLayout & uncomment btnPaint
	public GamePanel() {
		setLayout(null);
		Database.initProperty();
		Database.initButton();
		for (JButton btn : Database.getButtonsList()) {
			this.add(btn);
		}
		
//		addKeyListener(keyListener);
//		addMouseListener(mouseListener);
		addMouseMotionListener(mouseMotionListener);
	}
	
	public void setTileButton(boolean b) {
		for (JButton btn : Database.getButtonsList()) {
			btn.setEnabled(b);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font("Calibri", Font.PLAIN, 20));
		g.drawString("Cursor Coordinate : ", 1080, 50);
		g.drawString("X : " + coorX + " Y: " + coorY, 1080, 80);
		g.drawImage(img.getImage(), 0, 0, null);
		
//		for (JButton btn : Database.getButtonsList()) {
//			btn.paint(g);
//		}
	}
	
	private KeyListener keyListener = new KeyListener() {
		public void keyTyped(KeyEvent e) {}
		
		public void keyReleased(KeyEvent e) {}
		
		public void keyPressed(KeyEvent e) {}
	};
	
	private MouseMotionListener mouseMotionListener = new MouseMotionListener() {
	
		public void mouseMoved(MouseEvent e) {
			coorX = e.getX();
			coorY = e.getY();
			repaint();
		}
		
		public void mouseDragged(MouseEvent e) {}
	};
}
