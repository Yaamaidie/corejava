package ch6.innerclass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import ch6.innerclass.TalkingClock.TimePrinter;

/**
 * 演示局部内部类
 * @author lee
 *
 */
public class LocalInnerClass {
	public static void main(String[] args) {
		TalkingClock2 clock = new TalkingClock2();
		clock.start(1000, true);

		JOptionPane.showMessageDialog(null, "Quit program?");
	}
}

class TalkingClock2 {
	public void start(int interval, boolean beep) {
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