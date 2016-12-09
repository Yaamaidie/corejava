package ch11.catchingexceptions;

import java.util.*;

/**
 * 分析堆栈元素的程序：演示一个递归调用的跟踪过程
 * @author lee
 *
 */
public class StackTraceTest {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter n: ");
		int n = in.nextInt();
		factorial(n);
	}
	
	public static int factorial(int n) {
		System.out.println("factorial(" + n + "):");
		Throwable t = new Throwable();
		StackTraceElement[] frames = t.getStackTrace();
		
		for (StackTraceElement f : frames) {
			System.out.println(f);
		}
		
		int r; 
		if (n <= 1) {
			r = 1;
		} else {
			r = n * factorial(n -1);
		}
		System.out.println("return " + r);
		return r;
	}
	
}
