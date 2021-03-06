package volumn1.ch14.whatarethreads;

import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * 画ball的组件
 * @author lee
 *
 */
public class BallComponent extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private LinkedList<Ball> balls = new LinkedList<>();
	
	/**
	 * 添加一个球到这个组件
	 * @param b 要添加的球
	 */
	public void add(Ball b) {
		balls.add(b);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);//擦除背景
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : balls) {
			g2.fill(b.getShape());
		}
	}
	
}
