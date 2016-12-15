package ch12.boundsfortypevariables;

import java.io.Serializable;

public class BoundsTest {

}

class ArrayAlg {
	
	//逗号用来分割类型变量如<k,v>，&用来分割限定类型如T extends Comparable & Serializable
	public static <T extends Comparable<? super T> & Serializable> T min(T[] a) {
		if (a == null || a.length == 0) {
			return null;
		} 
		
		T smallest = a[0];
		
		for (int i = 0; i < a.length; i++) {
			if (smallest.compareTo(a[i]) > 0) {
				smallest = a[i];
			}
		}
		
		return smallest;
	}
	
}
