package volumn1.ch14.whatarethreads;

import java.awt.geom.*;
import java.util.Random;

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
	private double dx;
	private double dy;
	private int xfactor = 1;
	private int yfactor = 1;

	/**
	 * 移动球到下一个位置，碰到边界会反转方向
	 */
	public void move(Rectangle2D bounds) {
		Random r = new Random();
		dx = xfactor * r.nextInt(10);
		dy = yfactor * r.nextInt(10);
		x += dx;
		y += dy;
		if (x < bounds.getMinX()) {//左边界反转
			x = bounds.getMinX();
			xfactor = -xfactor;
		}
		if (x + XSIZE >= bounds.getMaxX()) {//右边界反转
			x = bounds.getMaxX() - XSIZE;
			xfactor = -xfactor;
		}
		if (y < bounds.getMinY()) {//上边界反转
			yfactor = -yfactor;
		}
		if (y + YSIZE >= bounds.getMaxY()) {//下边界反转
			yfactor = -yfactor;
		}
	}
	
	/**
	 * 获取球当前位置的形状
	 */
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}
	
}
