package package1;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Character {
	
	private static Character instance = null;
	private Integer x, y;
	private Integer width, height;
	private Integer charLeft, charRight, charTop, charBot;
	private boolean alive;
	private boolean inAir, jumping;
	private boolean right, left;
	private Integer jumpVelocity, fallVelocity, jumpHeight, movementVelocity;
	private Rectangle2D shape;
	private Image characterImage;
	
	protected Character() {
		x = 450;
		y = 0;
		
		width = 25;
		height = 25;
		
		charLeft = x;
		charRight = x + width;
		charTop = y;
		charBot = y + height;
		
		jumpVelocity = 6;
		fallVelocity = 3;
		jumpHeight = 100;
		movementVelocity = 4;
		
		alive = true;
		inAir = true;
		jumping = false;
		
		shape = new Rectangle(x, y, width, height);
	}
	

	public void fall() {
		if (inAir && !jumping) {
			y += fallVelocity;
			charTop = y;
			charBot = y + height;
			shape.setRect(x, y, width, height);
		}
	}


	public static Character getInstance() {
		if (instance == null) {
			instance = new Character();
		}
		
		return instance;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getCharLeft() {
		return charLeft;
	}

	public void setCharLeft(Integer charLeft) {
		this.charLeft = charLeft;
	}

	public Integer getCharRight() {
		return charRight;
	}

	public void setCharRight(Integer charRight) {
		this.charRight = charRight;
	}

	public Integer getCharTop() {
		return charTop;
	}

	public void setCharTop(Integer charTop) {
		this.charTop = charTop;
	}

	public Integer getCharBot() {
		return charBot;
	}

	public void setCharBot(Integer charBot) {
		this.charBot = charBot;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isInAir() {
		return inAir;
	}

	public void setInAir(boolean inAir) {
		this.inAir = inAir;
	}

	public Integer getMovementVelocity() {
		return movementVelocity;
	}

	public void setMovementVelocity(Integer movementVelocity) {
		this.movementVelocity = movementVelocity;
	}

	public void setShape(Rectangle2D shape) {
		this.shape = shape;
	}

	public Integer getJumpVelocity() {
		return jumpVelocity;
	}

	public void setJumpVelocity(Integer jumpVelocity) {
		this.jumpVelocity = jumpVelocity;
	}
	
	public Integer getFallVelocity() {
		return fallVelocity;
	}

	public void setFallVelocity(Integer fallVelocity) {
		this.fallVelocity = fallVelocity;
	}
	
	public Integer getJumpHeight() {
		return jumpHeight;
	}

	public void setJumpHeight(Integer jumpHeight) {
		this.jumpHeight = jumpHeight;
	}

	public Rectangle2D getShape() {
		return shape;
	}

	public void setShape(Integer x, Integer y, Integer width, Integer height) {
		this.shape.setRect(x, y, width, height);
	}

	public Image getCharacterImage() {
		return characterImage;
	}

	public void setCharacterImage(Image characterImage) {
		this.characterImage = characterImage;
	}
	
}
