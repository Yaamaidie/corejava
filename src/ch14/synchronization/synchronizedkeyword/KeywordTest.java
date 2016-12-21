package ch14.synchronization.synchronizedkeyword;

/**
 * 演示锁对象
 * @author lee
 *
 */
public class KeywordTest {
	public static final int NACCOUNTS = 100;
	public static final double INITIAL_BALANCE = 1000;
	
	public static void main(String[] args) {
		Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
		for (int i = 0; i < NACCOUNTS; i++) {
			TranferRunnable r = new TranferRunnable(b, i, INITIAL_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}
	
}
