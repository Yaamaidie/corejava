package ch13.collectionframwork;

import java.util.*;
/**
 * 集合大量元素操作
 * @author lee
 *
 */
public class BulkOperations {
	
	public static void main(String[] args) {
		//求两个集合的交集
		Set<String> a = new HashSet<>();
		a.add("a");
		a.add("b");
		a.add("c");
		Set<String> b = new HashSet<>();
		a.add("b");
		a.add("c");
		a.add("d");
		
		Set<String> result = new HashSet<>(a);
		result.retainAll(b);
	}
	
}
