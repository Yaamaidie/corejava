package ch14.synchronization.racecondition;

/**
 * 演示竞争条件，未加锁
 * 
 * @author lee
 *
 */
public class UnsynchBankTest {
	
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
