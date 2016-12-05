package ch7.determinefrmaesize;

import javax.swing.*;
import java.awt.*;

public class SizedFrameTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				SizedFrame frame = new SizedFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}
	
}

class SizedFrame extends JFrame{
	
	public SizedFrame() {
		//获取屏幕尺寸
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int ScreenWidth = screenSize.width;
		
		//设置框架的宽和高，让platform自己挑选location
		setSize(ScreenWidth / 2, screenHeight / 2);
		setLocationByPlatform(true);
		
		//设置框架的标题和图标
		Image img = kit.getImage("05081240bv3k.png");
		setIconImage(img);
		setTitle("SizedFrame");
	}
	
}