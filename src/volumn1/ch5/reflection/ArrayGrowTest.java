package volumn1.ch5.reflection;

import java.lang.reflect.*;
import java.util.*;
/**
 * 利用反射编写动态数组代码
 * @author lee
 *
 */
public class ArrayGrowTest {
	public static void main(String[] args) {
		int[] a = new int[10];
		a = (int[])goodArrayGrow(a);
		System.out.println(Arrays.toString(a));
	}
	
	/**
	 * 扩充后的数组类型是Object[]，无法转为原始数组类型
	 * @param a
	 * @return
	 */
	public static Object[] badArrayGrow(Object[] a) {
		int newLenth = a.length * 11 / 10 + 10;
		Object[] newArray = new Object[newLenth];
		System.arraycopy(a, 0, newArray, 0, a.length);
		return newArray;
	} 
	
	/**
	 * 使用反射：Array.newInstance(componentType, newLength)来构造数组
	 * @param a
	 * @return
	 */
	public static Object goodArrayGrow(Object a) {
		Class<? extends Object> cs = a.getClass();
		if (!cs.isArray()) {
			return null;
		}

		Class<?> componentType = cs.getComponentType();
		int length = Array.getLength(a);
		int newLength = length * 11 /10 + 10;
		
		Object newArray = Array.newInstance(componentType, newLength);
		System.arraycopy(a, 0, newArray, 0, length);
		
		return newArray;
	}
}
