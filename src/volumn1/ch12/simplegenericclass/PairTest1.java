package volumn1.ch12.simplegenericclass;

public class PairTest1 {
	public static void main(String[] args) {
		String[] words = {"Marry", "had", "a", "little", "lamb"};
		try {
			Pair<String> mm = ArrayAlg.minmax(words);
			System.out.println("min = " + mm.getFirst());
			System.out.println("max = " + mm.getSecond());
		} catch (NullParameterException e) {
			e.printStackTrace();
		} catch (EmptyContainerException e) {
			e.printStackTrace();
		}
	}
}

class ArrayAlg {
	
	/**
	 * 字符串数组的最大值最小值
	 * @param a 一个字符串数组
	 * @return 一个包含最大值和最小值的pair对象
	 * @throws NullParameterException 
	 * @throws EmptyContainerException 
	 */
	public static Pair<String> minmax(String[] a) throws NullParameterException, EmptyContainerException {
		if (a == null) {
			throw new NullParameterException();
		} 
		if (a.length == 0) {
			throw new EmptyContainerException();
		}
		
		String min = a[0];
		String max = a[0];
		for (int i = 0; i < a.length; i++) {
			if (min.compareTo(a[i]) > 0) {
				min = a[i];
			}
			if (max.compareTo(a[i]) < 0) {
				max = a[i];
			}
		}
		
		return new Pair<String>(min, max);
	}
	
}

class NullParameterException extends Exception {};
class EmptyContainerException extends Exception {};