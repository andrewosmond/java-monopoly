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
import com.monopoly.model.Character;
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
import com.monopoly.utility.TilesButton;
import com.monopoly.window.BuyCityWindow;
import com.monopoly.window.CardWindow;
import com.monopoly.window.CreatePlayerWindow;
import com.monopoly.window.JailEscapeWindow;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable {

	// game status enumeration
	public static enum STATE {
		TRANSITION, CREATEPLAYER, GAME, HELP, MENU
	};
	
	public static enum GAMESTATE {
		TURNSTART, INJAIL, ROLL, ROLLING, MOVE, MOVING, TURNEND
	};

	private static STATE currState = STATE.TRANSITION;
	private static GAMESTATE gameState = null;

	// game loops
	private boolean running = false;
	private Thread thread;
	private Player currPlayer = null;
	private int turn = 0;
	private int doubleCounter = 0;
	private int diceRolled = 1;
	private long rentFee;

	// assets
	private ImageIcon imgDice = new ImageIcon("assets/dice.png");
	private ImageIcon imgMap = new ImageIcon("assets/map.png");
	private ImageIcon imgProperty = new ImageIcon("assets/property.png");
	private ImageIcon imgTitle = new ImageIcon("assets/titleBG.jpg");
	private ImageIcon imgTransition = new ImageIcon("assets/transition.gif");

	// coordinate
	private int coorX;
	private int coorY;

	// objects
	private Vector<Character> characterList;
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

		characterList = new Vector<Character>();
		playerList = new Vector<Player>();
		propertyList = new Vector<Property>();
		initProperty();
		cardList = new Vector<Card>();
		initCard();
		
		dice = new Dice(this);
		chanceCardTile = new ChanceCardTile(this);
		chest = new Chest();
		goToJailTile = new GoToJailTile(this);
		jailTile = new JailTile();
		medicalBillTile = new MedicalBillTile();
		startTile = new StartTile();
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

	public Character getCharacterByName(String name) {
		for (Character character : characterList) {
			if (character.getName().equals(name)) {
				return character;
			}
		}
		return null;
	}

	public void initProperty() {
		propertyList.add(new City(Property.DIRECTION.LEFT, 431, 493, 0, 0, "Beijing", 20000, 10000, 30000, 50000, 50000));
		propertyList.add(new City(Property.DIRECTION.LEFT, 329, 435, 0, 1, "Bangkok", 26000, 10000, 30000, 50000, 50000));
		propertyList.add(new City(Property.DIRECTION.LEFT, 230, 377, 0, 2, "Taipei", 48000, 20000, 60000, 100000, 100000));
		propertyList.add(new City(Property.DIRECTION.LEFT, 178, 347, 0, 3, "New Delhi", 54000, 20000, 60000, 100000, 100000));
		propertyList.add(new City(Property.DIRECTION.LEFT, 128, 318, 0, 4, "Seoul", 60000, 20000, 60000, 100000, 100000));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 143, 224, 0, 5, "Tokyo", 72000, 30000, 90000, 150000, 150000));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 190, 194, 1, 0, "Sydney", 72000, 30000, 90000, 150000, 150000));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 292, 136, 1, 1, "Singapore", 94000, 40000, 120000, 200000, 200000));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 392, 77, 1, 2, "Sao Paulo", 100000, 40000, 120000, 200000, 200000));
		propertyList.add(new City(Property.DIRECTION.LEFT, 547, 76, 1, 3, "Prague", 118000, 50000, 150000, 250000, 250000));
		propertyList.add(new City(Property.DIRECTION.LEFT, 648, 134, 1, 4, "Berlin", 124000, 50000, 150000, 250000, 250000));
		propertyList.add(new City(Property.DIRECTION.LEFT, 749, 193, 1, 5, "Moscow", 140000, 60000, 180000, 300000, 300000));
		propertyList.add(new City(Property.DIRECTION.LEFT, 796, 222, 2, 0, "Geneva", 146000, 60000, 180000, 300000, 300000));
		propertyList.add(new City(Property.DIRECTION.LEFT, 847, 250, 2, 1, "Rome", 146000, 60000, 180000, 300000, 300000));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 758, 348, 2, 2, "London", 164000, 70000, 210000, 350000, 350000));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 711, 376, 2, 3, "Paris", 170000, 70000, 210000, 350000, 350000));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 610, 434, 2, 4, "New York", 192000, 80000, 240000, 400000, 400000));
		propertyList.add(new City(Property.DIRECTION.RIGHT, 510, 493, 2, 5, "Jakarta", 200000, 80000, 240000, 400000, 400000));
		propertyList.add(new Island(Property.DIRECTION.LEFT, 280, 406, 3, 0, "Phuket", 70500));
		propertyList.add(new Island(Property.DIRECTION.RIGHT, 340, 108, 3, 1, "Bali", 70500));
		propertyList.add(new Island(Property.DIRECTION.RIGHT, 92, 254, 3, 2, "Papua", 70500));
		propertyList.add(new Island(Property.DIRECTION.LEFT, 596, 105, 3, 3, "Hawaii", 70500));
		propertyList.add(new Island(Property.DIRECTION.RIGHT, 810, 319, 3, 4, "Bintan", 70500));
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
	
	public void nextTurn() {
		turn++;
		turn = turn % playerList.size();
		doubleCounter = 0;
		gameState = GAMESTATE.TURNSTART;
	}

	public void run() {
		
		while (running) {
			repaint();
			
			if (currState == STATE.GAME) {
				if (gameState == GAMESTATE.TURNSTART) {
					chest.increaseMoney();
					currPlayer = playerList.get(turn);
					jailTile.decrementDuration(currPlayer);
					if (currPlayer.isInJail()) {
						gameState = GAMESTATE.INJAIL;
						jailEscapeWindow.view(currPlayer);
					} else {
						gameState = GAMESTATE.ROLL;
					}
				}
	
				if (gameState == GAMESTATE.MOVE) {
					//Call character render
					gameState = GAMESTATE.MOVING;
				}
				
				if (gameState == GAMESTATE.TURNEND) {
					if (doubleCounter == 3) {
						jailTile.jailPlayer(playerList.get(turn));
						nextTurn();
					} else if (dice.isDouble()) {
						gameState = GAMESTATE.ROLL;
						doubleCounter++;
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
		g.drawString("Current Turn", 1087, 120);
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
		} else if (currState == STATE.GAME) {
			g.setFont(new Font("Calibri", Font.PLAIN, 20));
			g.drawString("Cursor Coordinate : ", 1080, 575);

			g.drawString("X : " + coorX + " Y: " + coorY, 1080, 605);
			
			displayCurrentTurn(g);
			board.render(g);
			chest.render(g);
			dice.render(g);
			scoreboard.render(g);
		
			if (gameState == GAMESTATE.ROLL) {
				displayRollButton(g);
			}
			
			if (gameState == GAMESTATE.MOVING) {
				currPlayer.move(g);
			}
			
		} else if (currState == STATE.TRANSITION) {
			g.setFont(new Font("Calibri", Font.PLAIN, 20));
			g.drawImage(imgTransition.getImage(), 0, 0, null);
			g.setFont(new Font("Helvetia", Font.BOLD, 50));
			g.drawString("PRESS ENTER TO CONTINUE...", 250, 650);
		}

		g.dispose();
	}

	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {
		coorX = e.getX();
		coorY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
		
	}

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
		if (currState == STATE.TRANSITION) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				currState = STATE.MENU;
				break;
			default:
				currState = STATE.TRANSITION;
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}
}
