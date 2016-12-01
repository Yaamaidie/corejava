package ch6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class CallBack {
	public static void main(String[] args) {
		//演示回调，见TimerTest
		TimerTest.main(args);
	}
}

class TimerTest {
	public static void main(String[] args) {
		ActionListener listener = new TimerPrinter();
		
		Timer t = new Timer(100, listener);
		t.start();
		
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}

class TimerPrinter implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Date now = new Date();
		System.out.println("the time is " + now);
//		java.awt.Toolkit.getDefaultToolkit().beep();
	}
	
}
