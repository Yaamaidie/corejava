package ch6;

import java.awt.event.ActionEvent;

public class InnerClass {
	//见下面的TalkingClock
}
class TalkingClock {
	private int interval;
	private boolean beep;
	
	class TimerPrinter implements java.awt.event.ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
}
