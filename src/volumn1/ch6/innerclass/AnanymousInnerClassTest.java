package volumn1.ch6.innerclass;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

/**
 * 演示匿名内部类
 * @author lee
 *
 */
public class AnanymousInnerClassTest {
	public static void main(String[] args) {
		TalkingClock3 clock = new TalkingClock3();
		clock.start(1000, true);
		
		javax.swing.JOptionPane.showMessageDialog(null, "Quit program?");
	}
}

class TalkingClock3 {
	public void start(int interval, final boolean beep) {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date now = new Date();
				System.out.println("The time is " + now);
				if (beep) {
					java.awt.Toolkit.getDefaultToolkit().beep();
				}
			}
		}; 
		
		Timer t = new Timer(interval, listener);
		t.start();
	}
}