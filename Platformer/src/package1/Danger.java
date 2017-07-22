package package1;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Danger {
	
	private Integer x, y;
	private Integer width, height;
	private Integer dangerLeft, dangerRight, dangerTop, dangerBot;
	private Rectangle2D shape;
	private Image dangerImage;
	
	public Danger(Integer x, Integer y, Integer width, Integer height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		dangerLeft = x;
		dangerRight = x + width;
		dangerTop = y;
		dangerBot = y + height;
		
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

	public Integer getDangerLeft() {
		return dangerLeft;
	}

	public void setDangerLeft(Integer dangerLeft) {
		this.dangerLeft = dangerLeft;
	}

	public Integer getDangerRight() {
		return dangerRight;
	}

	public void setDangerRight(Integer dangerRight) {
		this.dangerRight = dangerRight;
	}

	public Integer getDangerTop() {
		return dangerTop;
	}

	public void setDangerTop(Integer dangerTop) {
		this.dangerTop = dangerTop;
	}

	public Integer getDangerBot() {
		return dangerBot;
	}

	public void setDangerBot(Integer dangerBot) {
		this.dangerBot = dangerBot;
	}

	public Rectangle2D getShape() {
		return shape;
	}

	public void setShape(Rectangle2D shape) {
		this.shape = shape;
	}

	public Image getDangerImage() {
		return dangerImage;
	}

	public void setDangerImage(Image dangerImage) {
		this.dangerImage = dangerImage;
	}
	
}
