package ch7.positonframe;

import javax.swing.JFrame;

public class SimpleFrameTest2 {
	
	public static void main(String[] args) {
		SimpleFrame2 frame = new SimpleFrame2();
		
//		frame.setLocation(50, 50);
//		frame.setSize(400, 200);
		frame.setTitle("my frame");
		frame.setResizable(false);
		frame.setBounds(0, 0, 400, 400);
		frame.setLocationByPlatform(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}

class SimpleFrame2 extends JFrame { }