package ch7.createframe;

import javax.swing.*;

/**
 * 演示创建框架
 * 
 * @author lee
 *
 */
public class SimpleFrameTest {

	public static void main(String[] args) {
		SimpleFrame frame = new SimpleFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class SimpleFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;

	public SimpleFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

}