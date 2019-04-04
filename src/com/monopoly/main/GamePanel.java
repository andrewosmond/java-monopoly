package com.monopoly.main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.monopoly.database.Database;

public class GamePanel extends JPanel implements Runnable{
	
	// game status enumeration
	private static enum STATE{
		TRANSITION,
		GAME,
		HELP,
		MENU
	};
	private static STATE currState = STATE.TRANSITION;
	
	// game loops
	private boolean running = false;
	private Thread thread;
	
	// assets
	private ImageIcon map = new ImageIcon("assets/map.png");
	private ImageIcon title = new ImageIcon("assets/titleBG.jpg");
	private ImageIcon transition = new ImageIcon("assets/transition.gif");
	
	// coordinate
	private int coorX;
	private int coorY;
	
	// View buttons : comment setLayout & uncomment btnPaint
	public GamePanel() {
		setLayout(null);
		Database.initProperty();
		Database.initButton();
		
		setTileButton(false);
		
		addKeyListener(keyListener);
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseMotionListener);
		
		start();
	}
	
	public void setTileButton(boolean b) {
		for (JButton btn : Database.getButtonsList()) {
			this.remove(btn);
		}
		
		if (b) {
			for (JButton btn : Database.getButtonsList()) {
				this.add(btn);
			}
		}
	}
	
	private synchronized void start() {
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void end() {
		if(!running) {
			return;
		}
		running = false;
		try {
		thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	private void tick() {
		
	}
	
	public void run() {
		requestFocus();
		long prev =  System.nanoTime();
		final double ticks =  60.0;
		double limit = 1000000000 / ticks;
		double diff = 0;
		long now;
		int update = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running) {
			//will be the game
			now = System.nanoTime();
			diff += (now - prev) / limit;
			prev = now;
			if(diff >= 1) {
				tick();
				update++;
				diff--;
			}
			repaint();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer+= 1000;
				update = 0;
				frames = 0;
			}
		}
		end();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(currState == STATE.MENU) {
			g.drawImage(title.getImage(), 0, 0, null);
		}
		if(currState == STATE.GAME) {
			g.setFont(new Font("Calibri", Font.PLAIN, 20));
			g.drawString("Cursor Coordinate : ", 1080, 50);
			g.drawImage(map.getImage(), 0, 0, null);
			g.drawString("X : " + coorX + " Y: " + coorY, 1080, 80);
		}
		if(currState == STATE.TRANSITION) {
			g.setFont(new Font("Calibri", Font.PLAIN, 20));
			g.drawImage(transition.getImage(), 0, 0, null);
			g.setFont(new Font("Helvetia",Font.BOLD,50));
			g.drawString("PRESS ENTER TO CONTINUE...",250,650);
		}
		
//		for (JButton btn : Database.getButtonsList()) {
//			btn.paint(g);
//		}
	}
		
	
	private KeyListener keyListener = new KeyListener() {
		public void keyTyped(KeyEvent e) {}
		
		public void keyReleased(KeyEvent e) {}
		
		public void keyPressed(KeyEvent e) {
			if(currState == STATE.TRANSITION) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_ENTER : 
					currState = STATE.MENU;
					break;
					
					default : 
					currState = STATE.TRANSITION;
					break;
				}
			}
		}
	};

	private MouseListener mouseListener = new MouseListener() {
		
		public void mouseReleased(MouseEvent e) {}
		
		public void mousePressed(MouseEvent e) {}
		
		public void mouseExited(MouseEvent e) {}
		
		public void mouseEntered(MouseEvent e) {}
	
		public void mouseClicked(MouseEvent e) {
			if(currState == STATE.MENU) {
				if(coorX >= 460 && coorX <= 815) {
					if(coorY >= 355 && coorY <= 458) {
						currState = STATE.GAME;
						setTileButton(true);
					}
				}
				
				if(coorX >= 460 && coorX <= 815) {
					if(coorY >= 480 && coorY <= 574) {
						currState = STATE.HELP;
						setTileButton(false);
					}
				}
				
				if(coorX >= 460 && coorX <= 815) {
					if(coorY >= 600 && coorY <= 690) {
						setTileButton(false);
						System.exit(1);
					}
				}
			}	
		}
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
