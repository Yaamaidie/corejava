package volumn1.ch5.reflection;

import java.util.*;
import java.lang.reflect.*;

public class ObjectAnalyzerTest {

	public static void main(String[] args) {
		ArrayList<Integer> squares = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			squares.add(i * i);
		}
		System.out.println(new ObjectAnalyzer().toString(squares));
//		ch5.Employee e = new ch5.Employee("scott", 12);
//		System.out.println(new ObjectAnalyzer().toString(e));
		int[] arr = new int[]{1, 2, 3};
		System.out.println(new ObjectAnalyzer().toString(arr));
		
		volumn1.ch5.Employee[] es = new volumn1.ch5.Employee[]{new volumn1.ch5.Employee("scott", 12), new volumn1.ch5.Employee("coin", 15)};
		System.out.println(new ObjectAnalyzer().toString(es));
	}

}

class ObjectAnalyzer {

	// 访问过的对象
	private ArrayList<Object> visited = new ArrayList<>();

	public String toString(Object obj) {
		
		if (obj == null) {
			return "null";
		} else if (visited.contains(obj)) {
			return "...";
		} else {
			;
		}
		
		visited.add(obj);
		Class cs = obj.getClass();
		
		if (cs == String.class) {//字符串
			return (String) obj;
		} else if (cs.isArray()) {//数组
			String r = cs.getComponentType() + "[]{";
			for (int i = 0; i < Array.getLength(obj); i++) {
				if (i > 0) {
					r += ",";
				} else {
					;
				}
				Object val = Array.get(obj, i);
				if (cs.getComponentType().isPrimitive()) {//基本类型
					r += val;
				} else {
					r += toString(val);
				}
			}
			return r + "}";
		} else {//其他对象
			String r = cs.getName();
			do {
				r += "[";
				Field[] fields = cs.getDeclaredFields();
				AccessibleObject.setAccessible(fields, true);
				for (Field f : fields) {
					if (!Modifier.isStatic(f.getModifiers())) {//只看非static域
						if (!r.endsWith("[")) {
							r += ",";
						} else {
							;
						}
						r += f.getName() + "=";
						
						try {
							Class t = f.getType();
							Object val = f.get(obj);
							if (t.isPrimitive()) {
								r += val;
							} else {
								r += toString(val);
							}
						} catch(Exception e) {
							e.printStackTrace();
						}
					} else {
						;
					}
				}
				r += "]";
				cs = cs.getSuperclass();
			} while (cs != null);

			return r;
		}
	}
	
}