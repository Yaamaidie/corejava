package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test1 {
	@Test
	public void reflection() throws Exception {
		ch5.reflection.ReflectionTest.printAll("ch6.innerclass.TalkingClock2$1TimePrinter");
	}
	
	@Test
	public void innerclass() throws Exception {
		final int counter[] = new int[1];
		java.util.Date[] dates = new java.util.Date[100];
		for (int i = 0; i < dates.length; i++) {
			dates[i] = new java.util.Date() {
				public int compareTo(java.util.Date other) {
					counter[0]++;
					return super.compareTo(other);
				}
			};
		}
		java.util.Arrays.sort(dates);
		System.out.println(counter[0]);
	}
}
