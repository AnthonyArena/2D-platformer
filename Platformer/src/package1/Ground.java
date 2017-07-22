package package1;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Ground {
	
	private Integer x, y;
	private Integer width, height;
	private Integer groundLeft, groundRight, groundTop, groundBot;
	private Rectangle2D shape;
	private Image groundImage;
	
	public Ground(Integer x, Integer y, Integer width, Integer height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		groundLeft = x;
		groundRight = x + width;
		groundTop = y;
		groundBot = y + height;
		
		shape = new Rectangle();
		shape.setRect(x, y, width, height);
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

	public Integer getGroundLeft() {
		return groundLeft;
	}

	public void setGroundLeft(Integer groundLeft) {
		this.groundLeft = groundLeft;
	}

	public Integer getGroundRight() {
		return groundRight;
	}

	public void setGroundRight(Integer groundRight) {
		this.groundRight = groundRight;
	}

	public Integer getGroundTop() {
		return groundTop;
	}

	public void setGroundTop(Integer groundTop) {
		this.groundTop = groundTop;
	}

	public Integer getGroundBot() {
		return groundBot;
	}

	public void setGroundBot(Integer groundBot) {
		this.groundBot = groundBot;
	}

	public Rectangle2D getShape() {
		return shape;
	}

	public void setShape(Rectangle2D shape) {
		this.shape = shape;
	}

	public Image getGroundImage() {
		return groundImage;
	}

	public void setGroundImage(Image groundImage) {
		this.groundImage = groundImage;
	}
	
}
