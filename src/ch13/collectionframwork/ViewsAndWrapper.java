package ch13.collectionframwork;

import java.util.*;

/**
 * 视图和包装器
 * @author lee
 *
 */
public class ViewsAndWrapper {
	
	public static void main(String[] args) {
		//轻量级包装器
		Integer[] arr = new Integer[50];
		List<Integer> arrList = Arrays.asList(arr);
		
		//子范围
		List<Integer> sub = arrList.subList(9, 20);
		
		//不可修改的视图
		Collections.unmodifiableList(new ArrayList<String>());
		Collections.unmodifiableMap(new HashMap<String, String>());
		
		//同步视图：常用集合类线程安全使用视图机制
		Collections.synchronizedList(new ArrayList<String>());
		Collections.synchronizedMap(new HashMap<String, String>());
		
		//被检验视图：用于调试泛型类型问题
		ArrayList<String> strings = new ArrayList<>();
		ArrayList rawlist = strings;
		rawlist.add(new Date());
		//上面的代码在我们试图对rawlist.get的结果转换为String时才会发生转换错误
		//利用被检验视图，rawlist.add(new Date())将不被允许
		List<String> safeStrings = Collections.checkedList(strings, String.class);
		//safeStrings.add(new Date());//编译无法通过
	}
	
}
