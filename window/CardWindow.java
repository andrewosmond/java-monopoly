package com.monopoly.window;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.monopoly.card.Card;
import com.monopoly.main.GamePanel;
import com.monopoly.main.GamePanel.GAMESTATE;

@SuppressWarnings("serial")
public class CardWindow extends JFrame implements WindowListener{
	private static CardWindow instance = new CardWindow();
	private Card card = null;
	private GamePanel gamePanel = null;
	private ImageIcon imgCard = new ImageIcon("assets/card.png");
			
	private CardWindow() {
		setSize(400,500);
		setTitle("Card Window");
		addWindowListener(this);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public static CardWindow getInstance() {
		return instance;
	}
	
	public void view(Card card, GamePanel gamePanel) {
		if (isVisible()) return;
		this.card = card;
		this.gamePanel = gamePanel;
		repaint();
		setVisible(true);
	}

	public void paint(Graphics g) {	
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	
		int x = imgCard.getIconWidth() / 8;
		int y = imgCard.getIconHeight();
		
		g.drawImage(imgCard.getImage(), 0, 0, 400, 500, x*card.getCol(), 0, x*card.getCol() + x, y, null);
		
		g.dispose();
	}

	public void windowActivated(WindowEvent e) {}

	public void windowClosed(WindowEvent e) {}

	public void windowClosing(WindowEvent e) {
		gamePanel.setGameState(GAMESTATE.TURNEND);
	}

	public void windowDeactivated(WindowEvent e) {}

	public void windowDeiconified(WindowEvent e) {}

	public void windowIconified(WindowEvent e) {}

	public void windowOpened(WindowEvent e) {}
}
