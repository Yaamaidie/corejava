package ch6.innerclass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import ch6.innerclass.TalkingClock.TimePrinter;

public class LocalInnerClass {
	public static void main(String[] args) {
		TalkingClock clock = new TalkingClock(1000, true);
		clock.start();

		JOptionPane.showMessageDialog(null, "Quit program?");
	}
}

class TalkingClock2 {
	private int interval;
	private boolean beep;
	
	public TalkingClock2(int it,  boolean bp) {
		interval = it;
		beep = bp;
	}
	
	public void satrt() {
		class TimePrinter implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				Date now = new Date();
				System.out.println("The time is " + now);
				if (beep) {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		}
		
		ActionListener listener = new TimePrinter();
		Timer t = new Timer(interval, listener);
		t.start();
	}
}