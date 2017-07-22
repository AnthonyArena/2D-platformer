package package1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame implements KeyListener, Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4022546767756977426L;
	
	private static Main instance = null;
	private ArrayList<Ground> allItems;
	private Integer frameWidth, frameHeight, panelWidth, panelHeight;
	private DrawImage panel;
	private Character player;
	private Integer peak;
	private Ground ground1, ground2;
	
	protected Main() {
		panel = new DrawImage();
		frameWidth = 1000;
		frameHeight = 600;
		panelWidth = 10 * frameWidth;
		panelHeight = frameHeight;
		panel.setSize(panelWidth, panelHeight);
		setSize(frameWidth, frameHeight);
		setTitle("test");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		add(panel);
		
		initialize();
		
		addKeyListener(this);
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void initialize() {
		levelInitialize();
		playerInitialize();
	}
	
	public void levelInitialize() {
		allItems = new ArrayList<Ground>();
		ground1 = new Ground(0, frameHeight - 100, 1000, 100);
		ground2 = new Ground(0, frameHeight - 250, 1000, 100);
		
		allItems.add(ground1);
		allItems.add(ground2);
	}
	
	public void playerInitialize() {
		player = Character.getInstance();
		player.setX(450);
		player.setY(0);
		
		player.setWidth(25);
		player.setHeight(25);
		
		player.setCharLeft(player.getX());
		player.setCharRight(player.getX() + player.getWidth());
		player.setCharTop(player.getY());
		player.setCharBot(player.getY() + player.getHeight());
		
		player.setJumpVelocity(6);
		player.setFallVelocity(3);
		player.setJumpHeight(100);
		player.setMovementVelocity(4);
		
		player.setAlive(true);
		player.setInAir(true);
		player.setJumping(false);
		
		player.setShape(new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight()));
	}
	
	public void checkYPos() {
		if (player.getCharBot() >= frameHeight) {
			player.setAlive(false);
		}
	}
	
	public void playerJump() {
		if (player.isJumping() && player.getY() > peak) {
			player.setY(player.getY() - player.getJumpVelocity());
			player.setShape(player.getX(), player.getY(), player.getWidth(), player.getHeight());
		} else {
			player.setJumping(false);
		}
	}
	
	public void playerFall() {
		boolean canPass = true;
		Rectangle2D tempDown = new Rectangle(player.getX(), player.getY() + player.getFallVelocity(), player.getWidth(), player.getHeight());
		
		for (int i = 0; i < allItems.size(); i++) {
			if (tempDown.intersects(allItems.get(i).getShape())) {
				canPass = false;
				break;
			}
		}
		
		if (canPass) {
			player.fall();
		}
	}
	
	public void checkJump() {
		boolean canPass = true;
		Rectangle2D tempUp = new Rectangle(player.getX(), player.getY() - player.getJumpVelocity(), player.getWidth(), player.getHeight());
		
		for (int i = 0; i < allItems.size(); i++) {
			if (tempUp.intersects(allItems.get(i).getShape())) {
				canPass = false;
				break;
			}
		}
		
		if (!canPass) {
			player.setJumping(false);
		}
	}
	
	public void levelMove() {
		boolean canPass = true;
		Rectangle2D tempRight = new Rectangle(player.getX() + player.getMovementVelocity(), player.getY(), player.getWidth(), player.getHeight());
		Rectangle2D tempLeft = new Rectangle(player.getX() - player.getMovementVelocity(), player.getY(), player.getWidth(), player.getHeight());
		
		if (player.isRight()) {
			for (int i = 0; i < allItems.size(); i++) {
				if (tempRight.intersects(allItems.get(i).getShape())) {
					canPass = false;
					break;
				}
			}
			
			if (canPass) {
				for (int i = 0; i < allItems.size(); i++) {
					Ground current = allItems.get(i);
					
					current.setX(current.getX() - player.getMovementVelocity());
					current.setShape(new Rectangle(current.getX(), current.getY(), current.getWidth(), current.getHeight()));
				}
			}
			
		} else if (player.isLeft()) {
			for (int i = 0; i < allItems.size(); i++) {
				if (tempLeft.intersects(allItems.get(i).getShape())) {
					canPass = false;
					break;
				}
			}
			
			if (canPass) {
				for (int i = 0; i < allItems.size(); i++) {
					Ground current = allItems.get(i);
					
					current.setX(current.getX() + player.getMovementVelocity());
					current.setShape(new Rectangle(current.getX(), current.getY(), current.getWidth(), current.getHeight()));
				}
			}
		}
	}
	
	public void checkGrounded() {
		Rectangle2D tempPlayer = new Rectangle(player.getX(), player.getY() + player.getFallVelocity(), player.getWidth(), player.getHeight());
		int counter = 0;
		
		for (int i = 0; i < allItems.size(); i++) {
			Ground current = allItems.get(i);
			if (!tempPlayer.intersects(current.getShape())) {
				counter++;
			}
		}
		
		if (counter == allItems.size()) {
			player.setInAir(true);
		} else {
			player.setInAir(false);
		}
	}
	
	public static Main getInstance() {
		if (instance == null) {
			instance = new Main();
		}
		
		return instance;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			playerFall();
			playerJump();
			checkJump();
			checkYPos();
			checkGrounded();
			levelMove();
			
			
			
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE && !player.isJumping() && !player.isInAir() && player.isAlive()) {
			player.setJumping(true);
			peak = player.getY() - player.getJumpHeight();
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT && player.isAlive()) {
			player.setLeft(true);
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT && player.isAlive()) {
			player.setRight(true);
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_X && !player.isAlive()) {
			System.exit(0);
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_O && !player.isAlive()) {
			initialize();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setLeft(false);
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setRight(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private class DrawImage extends JPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -249772197008847521L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.setColor(Color.BLUE);
			g2d.fillRect(0, 0, panelWidth, panelHeight);
			
			for (int i = 0; i < allItems.size(); i++) {
				g2d.setColor(Color.BLACK);
				g2d.fill(allItems.get(i).getShape());
			}
			
			if (player.isAlive()) {
				g2d.setColor(Color.WHITE);
				g2d.fill(player.getShape());
			} 
			
			if (!player.isAlive()) {
				g2d.setColor(Color.WHITE);
				g2d.setFont(new Font("TimesRoman", Font.PLAIN, 75));
				g2d.drawString("GAME OVER", frameWidth / 4, frameHeight / 2);
				g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
				g2d.drawString("Press the o key to play again, or the x key to exit", frameWidth * 3 / 11 , frameHeight * 11 / 20);
			}
			
			
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			Toolkit.getDefaultToolkit().sync();
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.setVisible(true);
	}
	
}
