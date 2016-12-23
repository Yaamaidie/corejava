package volumn1.ch7.displayinformationinacomponent;

import javax.swing.*;
import java.awt.*;


/**
 * 在组件中显示信息
 * @author lee
 *
 */
public class NotHellowWorld {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				NotHelloWorldFrame frame = new NotHelloWorldFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}
	
}

class NotHelloWorldFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	
	public NotHelloWorldFrame() {
		setTitle("NotHelloWord");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		NotHelloWorldPanel panel = new NotHelloWorldPanel();
		add(panel);
	}
	
}

class NotHelloWorldPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;
	
	//默认调用方法
	public void paintComponent(Graphics g) {
		g.drawString("Not a Hello, World program", MESSAGE_X, MESSAGE_Y);
	}
	
}
