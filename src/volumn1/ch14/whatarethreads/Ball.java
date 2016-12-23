package volumn1.ch14.whatarethreads;

import java.awt.geom.*;

/**
 * 一个不断移动的球，碰到边界会弹回
 * @author lee
 *
 */
public class Ball {
	
	private static final int XSIZE = 15;
	private static final int YSIZE = 15;
	
	private double x = 0;
	private double y = 0;
	private double dx = 1;
	private double dy = 1;
	
	/**
	 * 移动球到下一个位置，碰到边界会反转方向
	 */
	public void move(Rectangle2D bounds) {
		x += dx;
		y += dy;
		if (x < bounds.getMinX()) {//左边界反转
			x = bounds.getMinX();
			dx = -dx;
		}
		if (x + XSIZE >= bounds.getMaxX()) {//右边界反转
			x = bounds.getMaxX() - XSIZE;
			dx = -dx;
		}
		if (y < bounds.getMinY()) {//上边界反转
			dy = -dy;
		}
		if (y + YSIZE >= bounds.getMaxY()) {//下边界反转
			dy = -dy;
		}
	}
	
	/**
	 * 获取球当前位置的形状
	 */
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}
	
}
