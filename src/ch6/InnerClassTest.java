package ch6;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class InnerClassTest {
	
	public static void main(String[] args) {
		TalkingClock clock = new TalkingClock(1000, true);
		clock.start();

		JOptionPane.showMessageDialog(null, "Quit program?");
	}
	
}

class TalkingClock {
	
	private int interval;
	private boolean beep;

	public TalkingClock(int it, boolean bp) {
		interval = it;
		beep = bp;
	}

	public void start() {
		ActionListener listener = new TimePrinter();
		Timer t = new Timer(interval, listener);
		t.start();
	}

	public class TimePrinter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Date now = new Date();
			System.out.println("the time is " + now);
			if (beep) {
				Toolkit.getDefaultToolkit().beep();
			}
		}
		
	}
	
}
