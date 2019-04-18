package com.monopoly.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.monopoly.card.Card;
import com.monopoly.card.CharityCard;
import com.monopoly.card.HauntedHouseCard;
import com.monopoly.card.ExplosionCard;
import com.monopoly.card.RedemptionCard;
import com.monopoly.card.GoToJailCard;
import com.monopoly.card.GoToHospitalCard;
import com.monopoly.card.GoToStartCard;
import com.monopoly.card.VIPCard;
import com.monopoly.model.Board;
import com.monopoly.model.ChanceCardTile;
import com.monopoly.model.Chest;
import com.monopoly.model.City;
import com.monopoly.model.Dice;
import com.monopoly.model.GoToJailTile;
import com.monopoly.model.Island;
import com.monopoly.model.JailTile;
import com.monopoly.model.MedicalBillTile;
import com.monopoly.model.Player;
import com.monopoly.model.Property;
import com.monopoly.model.Scoreboard;
import com.monopoly.model.StartTile;
import com.monopoly.model.Tiles.CHARADIR;
import com.monopoly.utility.TilesButton;
import com.monopoly.window.BuyCityWindow;
import com.monopoly.window.CardWindow;
import com.monopoly.window.CreatePlayerWindow;
import com.monopoly.window.JailEscapeWindow;
import com.monopoly.window.LogoAnimation;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable {
	// game status enumeration
	public static enum STATE {
		TRANSITION, CREATEPLAYER, GAME, HELP, MENU, LOGO
	};
	
	public static enum GAMESTATE {
		TURNSTART, INJAIL, ROLL, ROLLING, MOVE, MOVING, TURNEND, WINSCREEN
	};

	private static STATE currState = STATE.LOGO;
	private static STATE prevState = null;
	private static GAMESTATE gameState = null;

	// game loops
	private boolean running = false;
	private Thread thread;
	private Player currPlayer = null;
	private int turn = 0;
	private int doubleCounter = 0;
	private int diceRolled = 1;
	private int turnCounter = 0;
	private final int turnLimit = 30;
	private long rentFee;

	// assets
	private ImageIcon imgDice = new ImageIcon("assets/dice.png");
	private ImageIcon imgMap = new ImageIcon("assets/map.png");
	private ImageIcon imgProperty = new ImageIcon("assets/property.png");
	private ImageIcon imgTitle = new ImageIcon("assets/titleBG.jpg");
	private ImageIcon imgTransition = new ImageIcon("assets/transition.gif");
	private ImageIcon imgWinScreen = new ImageIcon("assets/winScreen.png");
	
	// frames
	private int winScreenFrame = 0;
	private long prevTime = 0;

	// coordinate
	private int coorX;
	private int coorY;
	
	// objects
	private LogoAnimation la;
	
	private Vector<Player> playerList;
	private Vector<Property> propertyList;
	private Vector<Card> cardList;
	private Dice dice;
	private TilesButton tilesButton;
	private Board board;
	private ChanceCardTile chanceCardTile;
	private Chest chest;
	private GoToJailTile goToJailTile;
	private JailTile jailTile;
	private MedicalBillTile medicalBillTile;
	private StartTile startTile;
	private Scoreboard scoreboard;
	
	private BuyCityWindow buyCityWindow;
	private CardWindow cardWindow;
	private CreatePlayerWindow createPlayerWindow;
	private JailEscapeWindow jailEscapeWindow;
	
	public GamePanel() {
		setLayout(null);
		setFocusable(true);

		la = new LogoAnimation();
		
		playerList = new Vector<Player>();
		propertyList = new Vector<Property>();
		initProperty();
		cardList = new Vector<Card>();
		initCard();
		
		dice = new Dice(this);
		chanceCardTile = new ChanceCardTile(this, CHARADIR.LEFT, 336, 438);
		chest = new Chest(this, CHARADIR.RIGHT, 440, 21);
		goToJailTile = new GoToJailTile(this, CHARADIR.LEFT, 879, 268);
		jailTile = new JailTile(this, CHARADIR.RIGHT, 38, 259);
		medicalBillTile = new MedicalBillTile(this, CHARADIR.LEFT, 577, 440);
		startTile = new StartTile(this, CHARADIR.LEFT, 456, 503);
		board = new Board(this);
		scoreboard = new Scoreboard(this);
		tilesButton = new TilesButton(this);
		setTilesButton(false);
		
		buyCityWindow = new BuyCityWindow(this);
		cardWindow = CardWindow.getInstance();
		createPlayerWindow = new CreatePlayerWindow(this);
		jailEscapeWindow = new JailEscapeWindow(this);
		
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

		start();
	}
	
	public ImageIcon getDiceImage() {
		return imgDice;
	}

	public ImageIcon getMapImage() {
		return imgMap;
	}

	public ImageIcon getPropertyImage() {
		return imgProperty;
	}
	
	public STATE getCurrState() {
		return currState;
	}

	public void setCurrState(STATE currState) {
		GamePanel.currState = currState;
	}
	
	public GAMESTATE getGameState() {
		return gameState;
	}
	
	public void setGameState(GAMESTATE gameState) {
		GamePanel.gameState = gameState;
	}
	
	public int getDiceRolled() {
		return diceRolled;
	}
	
	public void setDiceRolled(int diceRolled) {
		this.diceRolled = diceRolled;
	}

	public Vector<Player> getPlayerList() {
		return playerList;
	}

	public void initProperty() {
		propertyList.add(new City(Property.DIRECTION.LEFT, 431, 493, 0, 0, CHARADIR.LEFT, 386, 470, "Beijing", 20000, 10000, 30000, 50000, 50000, this));
		propertyList.add(new City(Property.DIRECTION.LEFT, 329, 435, 0, 1, CHARADIR.LEFT, 287, 415, "Bangkok", 26000, 10000, 30000, 50000, 50000, this));
		propertyList.add(new City(Property.DIRECTION.LEFT, 230, 377, 0, 2, CHARADIR.LEFT, 184, 354, "Taipei", 48000, 20000, 60000, 100000, 100000, this));
		propertyList.add(new City(Property.DIRECTION.LEFT, 178, 347, 0, 3, CHARADIR.LEFT, 136, 325, "New Delhi", 54000, 20000, 60000, 100000, 100000, this));
		propertyList.add(new City(Property.DIRECTION.LEFT, 128, 318, 0, 4, CHARADIR.LEFT, 85, 297, "Seoul", 60000, 20000, 60000, 100000, 100000, this));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 143, 224, 0, 5, CHARADIR.RIGHT, 140, 203, "Tokyo", 72000, 30000, 90000, 150000, 150000, this));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 190, 194, 1, 0, CHARADIR.RIGHT, 188, 174, "Sydney", 72000, 30000, 90000, 150000, 150000, this));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 292, 136, 1, 1, CHARADIR.RIGHT, 291, 115, "Singapore", 94000, 40000, 120000, 200000, 200000, this));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 392, 77, 1, 2, CHARADIR.RIGHT, 388, 56, "Sao Paulo", 100000, 40000, 120000, 200000, 200000, this));
		propertyList.add(new City(Property.DIRECTION.LEFT, 547, 76, 1, 3, CHARADIR.RIGHT, 488, 54, "Prague", 118000, 50000, 150000, 250000, 250000, this));
		propertyList.add(new City(Property.DIRECTION.LEFT, 648, 134, 1, 4, CHARADIR.RIGHT, 590,114, "Berlin", 124000, 50000, 150000, 250000, 250000, this));
		propertyList.add(new City(Property.DIRECTION.LEFT, 749, 193, 1, 5, CHARADIR.RIGHT, 695, 172, "Moscow", 140000, 60000, 180000, 300000, 300000, this));
		propertyList.add(new City(Property.DIRECTION.LEFT, 796, 222, 2, 0, CHARADIR.RIGHT, 740, 201, "Geneva", 146000, 60000, 180000, 300000, 300000, this));
		propertyList.add(new City(Property.DIRECTION.LEFT, 847, 250, 2, 1, CHARADIR.RIGHT, 790, 228, "Rome", 146000, 60000, 180000, 300000, 300000, this));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 758, 348, 2, 2, CHARADIR.LEFT, 775, 326, "London", 164000, 70000, 210000, 350000, 350000, this));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 711, 376, 2, 3, CHARADIR.LEFT, 723, 355, "Paris", 170000, 70000, 210000, 350000, 350000, this));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 610, 434, 2, 4, CHARADIR.LEFT, 625, 411, "New York", 192000, 80000, 240000, 400000, 400000, this));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 510, 493, 2, 5, CHARADIR.LEFT, 526,472, "Jakarta", 200000, 80000, 240000, 400000, 400000, this));
		propertyList.add(new Island(Property.DIRECTION.LEFT, 280, 406, 3, 0, CHARADIR.LEFT, 237,385, "Phuket", 70500, this));
		propertyList.add(new Island(Property.DIRECTION.RIGHT, 340, 108, 3, 1, CHARADIR.RIGHT, 337, 87, "Bali", 70500, this));
		propertyList.add(new Island(Property.DIRECTION.RIGHT, 92, 254, 3, 2, CHARADIR.RIGHT, 89, 231, "Papua", 70500, this));
		propertyList.add(new Island(Property.DIRECTION.LEFT, 596, 105, 3, 3, CHARADIR.RIGHT, 536, 84, "Hawaii", 70500, this));
		propertyList.add(new Island(Property.DIRECTION.RIGHT, 810, 319, 3, 4, CHARADIR.LEFT, 824, 298, "Bintan", 70500, this));
	}

	public Vector<Property> getPropertyList() {
		return propertyList;
	}

	public int getPropertyIndex(String name) {
		int idx = 0;
		for (Property property : propertyList) {
			if (property.getName().equals(name))
				return idx;
			idx++;
		}
		return -1;
	}
	
	public void initCard() {
		cardList.add(new CharityCard(0));
		cardList.add(new HauntedHouseCard(1));
		cardList.add(new ExplosionCard(2));
		cardList.add(new RedemptionCard(3));
		cardList.add(new GoToJailCard(4));
		cardList.add(new GoToHospitalCard(5));
		cardList.add(new GoToStartCard(6));
		cardList.add(new VIPCard(7));
	}
	
	public Vector<Card> getCardList(){
		return cardList;
	}
	
	public Dice getDice() {
		return dice;
	}

	public Board getBoard() {
		return board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}

	public ChanceCardTile getChanceCardTile() {
		return chanceCardTile;
	}

	public Chest getChest() {
		return chest;
	}

	public GoToJailTile getGoToJailTile() {
		return goToJailTile;
	}

	public JailTile getJailTile() {
		return jailTile;
	}

	public MedicalBillTile getMedicalBillTile() {
		return medicalBillTile;
	}

	public StartTile getStartTile() {
		return startTile;
	}

	public long getRentFee() {
		return rentFee;
	}

	public void setRentFee(long rentFee) {
		this.rentFee = rentFee;
	}

	public void setTilesButton(boolean isOn) {
		for (JButton btn : tilesButton.getButtonList()) {
			this.remove(btn);
		}

		if (isOn)
			for (JButton btn : tilesButton.getButtonList()) {
				this.add(btn);
			}
	}
	
	public BuyCityWindow getBuyCityWindow() {
		return buyCityWindow;
	}
	
	public CardWindow getCardWindow() {
		return cardWindow;
	}
	
	public JailEscapeWindow getJailEscapeWindow() {
		return jailEscapeWindow;
	}

	private synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void end() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public Player getCurrPlayer() {
		return currPlayer;
	}
	
	public void setTurnCounter(int turnCounter) {
		this.turnCounter = turnCounter;
	}
	
	public void nextTurn() {
		currPlayer.getCharacter().end();
		turn++;
		turn = turn % playerList.size();
		doubleCounter = 0;
		gameState = GAMESTATE.TURNSTART;
	}
	
	public boolean isGameOver() {
		int result = 0;
		for (Player player : playerList) {
			if (player.isBankrupt()) continue;
			result++;
		}
		if (result == 1 || turnCounter > turnLimit) {
			gameState = GAMESTATE.WINSCREEN;
			return true;
		}
		return false;
	}

	public void run() {
		
		while (running) {
			repaint();
			
			if (currState == STATE.GAME) {
				if (gameState == GAMESTATE.WINSCREEN) {
					if (System.currentTimeMillis() - prevTime > 150) {
						prevTime = System.currentTimeMillis();
						winScreenFrame++;
						winScreenFrame = winScreenFrame % 5; 
					}
				} else if (gameState == GAMESTATE.TURNSTART) {
					if (turn == 0) turnCounter++;
					if (!isGameOver()) {
						currPlayer = playerList.get(turn);
						
						if (currPlayer.isBankrupt()) {
							nextTurn();
						} else {
							currPlayer.getCharacter().start();
							chest.increaseMoney();
							jailTile.decrementDuration(currPlayer);
							if (currPlayer.isInJail()) {
								gameState = GAMESTATE.INJAIL;
								jailEscapeWindow.view(currPlayer);
							} else {
								gameState = GAMESTATE.ROLL;
							}
						}
					}
				} else if (gameState == GAMESTATE.MOVE) {
					if (dice.isDouble()) doubleCounter++;
					if (doubleCounter == 3) {
						jailTile.jailPlayer(playerList.get(turn));
						nextTurn();
					} else {
						gameState = GAMESTATE.MOVING;
						currPlayer.move();
					}
				} else if (gameState == GAMESTATE.TURNEND) {
					if (dice.isDouble()) {
						gameState = GAMESTATE.ROLL;
					} else {
						nextTurn();
					}
				}		
			}
	
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		end();
	}
	
	public void displayCurrentTurn(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 20));
		String turnText = String.format("Current Turn (%d/%d)", turnCounter, turnLimit);
		g.drawString(turnText, 1016 + (258-8*turnText.length())/2, 120);
		int x = playerList.get(turn).getName().length();
		g.drawString(playerList.get(turn).getName(), 1016 + (258-12*x)/2, 148);
	}
	
	public void displayRollButton(Graphics g) {
		g.setColor(new Color(113, 62, 90));
		g.fillRect(1095, 500, 100, 40);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Calibri", Font.BOLD, 25));
		g.drawString("Roll", 1125, 528);
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
		
		if (currState == STATE.MENU || currState == STATE.CREATEPLAYER) {
			g.drawImage(imgTitle.getImage(), 0, 0, null);
			prevState = currState;
		} else if (currState == STATE.GAME) {
			if (gameState == GAMESTATE.WINSCREEN) {
				setTilesButton(false);
				int w = imgWinScreen.getIconWidth();
				int h = imgWinScreen.getIconHeight() / 5;
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 1280, 720);
				g.drawImage(imgWinScreen.getImage(), 0, 0, w, h, 0, winScreenFrame*h, w, winScreenFrame*h + h, null);
				g.setColor(Color.BLACK);
				g.setFont(new Font("Calibri", Font.BOLD, 80));
				String name = scoreboard.getFirstPlayer().getName();
				g.drawString(name, (1274-(30*name.length()))/2, 256);
				g.setFont(new Font("Helvetia", Font.BOLD, 50));
				g.drawString("PRESS ENTER TO CONTINUE...", 250, 650);
			} else {
				displayCurrentTurn(g);
				board.render(g);
				chest.render(g);
				dice.render(g);
				scoreboard.render(g);
				for (Player player : playerList) {
					if (player.isBankrupt()) continue;
					player.getCharacter().render(g);
				}
				if (gameState == GAMESTATE.ROLL) displayRollButton(g);
			}
			prevState = currState;
		} else if (currState == STATE.TRANSITION) {
			g.setFont(new Font("Calibri", Font.PLAIN, 20));
			g.drawImage(imgTransition.getImage(), 0, 0, null);
			g.setFont(new Font("Helvetia", Font.BOLD, 50));
			g.drawString("PRESS ENTER TO CONTINUE...", 250, 650);
			prevState = currState;
		} else if(currState == STATE.LOGO) {
			if(prevState != STATE.LOGO) {
				la.init();
			}
			prevState = currState;
			if(la.render() == 1) {
				currState = STATE.TRANSITION;
			}
			la.draw(g);
		}

		g.dispose();
	}

	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {
		coorX = e.getX();
		coorY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		if (currState == STATE.MENU) {
			if (coorX >= 460 && coorX <= 815 && coorY >= 355 && coorY <= 458) {
				currState = STATE.CREATEPLAYER;
				createPlayerWindow.getFrame().setVisible(true);
			}

			if (coorX >= 460 && coorX <= 815 && coorY >= 480 && coorY <= 574) {
				currState = STATE.HELP;
				setTilesButton(false);
			}

			if (coorX >= 460 && coorX <= 815 && coorY >= 600 && coorY <= 690) {
				setTilesButton(false);
				System.exit(1);
			}
		} else if (currState == STATE.GAME) {
			if (gameState == GAMESTATE.ROLL) {
				if (coorX >= 1095 && coorX <= 1195 && coorY >= 500 && coorY <= 540) {
					gameState = GAMESTATE.ROLLING;
					dice.start();
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			if (currState == STATE.TRANSITION)
				currState = STATE.MENU;
			else if (currState == STATE.GAME) {
				if (gameState == GAMESTATE.WINSCREEN) 
					currState = STATE.MENU;
			}
			break;
		default: break;
		}
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}
}
