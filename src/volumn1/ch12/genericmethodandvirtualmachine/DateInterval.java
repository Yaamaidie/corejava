package volumn1.ch12.genericmethodandvirtualmachine;

import java.util.*;

public class DateInterval extends Pair<Date> {
	
	public static void main(String[] args) {
		DateInterval interval = new DateInterval();
		Pair<Date> pair = interval;
		pair.setSeconde(new Date());
		volumn1.ch5.reflection.ReflectionTest.printAll("ch12.genericmethodandvirtualmachine.DateInterval");
	}
	
	public void setSecond(Date second) {
		if (second.compareTo(getFirst()) >= 0) {
			super.setSeconde(second);
		}
	}
	
}

class Pair<T> {
	
	private T first;
	private T second;
	
	public Pair() {
		first = null;
		second = null;
	}
	
	public Pair(T f, T s) {
		first = f;
		second = s;
	}
	
	public T getFirst() {
		return first;
	}
	
	public void setFirst(T f) {
		first = f;
	}
	
	public T getSecond() {
		return second;
	}
	
	public void setSeconde(T s) {
		second = s;
	}
	
}
