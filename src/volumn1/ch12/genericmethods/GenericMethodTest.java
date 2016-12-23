package volumn1.ch12.genericmethods;

/**
 * 泛型方法
 * @author lee
 *
 */
public class GenericMethodTest {
	
	public static void main(String[] args) {
		Double arr = ArrayAlg.getMiddle(new Double[]{11.0, 12.0, 11.5});
		System.out.println(arr);
		
	}
	
}

class ArrayAlg {
	
	public static <T> T getMiddle(T[] a) {
		return a[a.length / 2];
	}
	
}
