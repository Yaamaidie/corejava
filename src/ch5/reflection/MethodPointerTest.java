package ch5.reflection;

import java.lang.reflect.*;
/**
 * 使用反射调用方法
 * @author lee
 *
 */
public class MethodPointerTest {
	public static void main(String[] args) throws Exception {
		//获取square和sqrt的方法指针
		Method square = MethodPointerTest.class.getMethod("square", double.class);
		Method sqrt = Math.class.getMethod("sqrt", double.class);
		
		//打印x-value和y-value的表格
		printTable(1, 10, 10, square);
		printTable(1, 10, 10, sqrt);
	}
	
	/**
	 * 返回平方
	 * @param x
	 * @return
	 */
	public static double square(double x) {
		return x * x;
	}
	
	/**
	 * 
	 * @param from x的值的下界
	 * @param to x的值的上界
	 * @param n 打印的条数
	 * @param f 调用的方法
	 */
	public static void printTable(double from, double to, int n, Method f) {
		//在表格外的头部打印方法名
		System.out.println(f);
		
		double dx = (to - from) / (n -1);
		
		for (double x = from; x <= to; x += dx) {
			try {
				double y = (Double)f.invoke(null, x);
				System.out.printf("%10.4f | %10.4f%n", x, y);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
