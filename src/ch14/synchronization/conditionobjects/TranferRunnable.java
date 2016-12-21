package ch14.synchronization.conditionobjects;

/**
 * 一个runnable对象，执行从一个账户向另一个账户交易的动作
 * @author lee
 *
 */
public class TranferRunnable implements Runnable{
	
	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;
	
	/**
	 * 构造器 
	 * @param b 交易账户的所属银行
	 * @param from 交易账户来源
	 * @param max 每次交易的最大数额
	 */
	public TranferRunnable(Bank b, int from, double max) {
		bank = b;
		fromAccount = from;
		maxAmount = max;
	}

	public void run() {
		while (true) {
			int toAccount = (int) (bank.size() * Math.random());
			double amount = maxAmount * Math.random();
			try {
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep((int) (DELAY * Math.random()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
	
}
