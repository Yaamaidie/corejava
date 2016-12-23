package volumn1.ch14.whatarethreads;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * 一个不断跳动的球，演示单线程的情况
 * @author lee
 *
 */
public class Bounce {
	
	public static void main (String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				JFrame frame = new BounceFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}
	
}

/**
 * 有一个球和按钮的框架
 */
class BounceFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HEIGHT = 350;
	public static final int STEPS = 10000;
	public static final int DELAY = 3;
	
	private BallComponent comp;
	
	public BounceFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("Bounce");
		
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				addBall();
			}
			
		});
		
		addButton(buttonPanel, "close", new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * 添加一个按钮到容器里
	 * @param c 容器
	 * @param title 按钮标题
	 * @param listener 按钮的动作监听器
	 */
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	/**
	 * 每次调用添加一个新的跳动的球到面板并且让他跳动1000次
	 */
	public void addBall() {	
		Ball ball = new Ball();
		comp.add(ball);
		Runnable r = new BallRunable(ball, comp);
		Thread t = new Thread(r);
		t.start();
	}
	
}

class BallRunable implements Runnable {
	
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
	
	private Ball ball;
	private Component component;
	
	
	public BallRunable(Ball b, Component c) {
		ball = b;
		component = c;
	}

	public void run() {
		try {
			for (int i = 1; i <= STEPS; i++) {
				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			
		}
	}
	
}
