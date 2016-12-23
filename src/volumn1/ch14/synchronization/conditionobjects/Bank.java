package volumn1.ch14.synchronization.conditionobjects;

import java.util.concurrent.locks.*;

/**
 * 有一些账户的银行
 * @author lee
 *
 */
public class Bank {
	
	private final double[] accounts;
	private Lock bankLock;
	//条件对象：足够的余额条件
	private Condition sufficientFunds;
	
	/**
	 * 构造器
	 * @param n 账户的数量
	 * @param initialBalance 每个账户的初始余额
	 */
	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		for (int i = 0; i < accounts.length; i++) {
			accounts[i] = initialBalance;
		}
		bankLock = new ReentrantLock();
		sufficientFunds = bankLock.newCondition();
	}
	
	/**
	 * 从一个账户交易到另一个账户
	 * @param from 交易来源
	 * @param to 交易对象
	 * @param amount 交易数额
	 * @throws InterruptedException 
	 */
	public void transfer(int from, int to, double amount) throws InterruptedException {
		
		bankLock.lock();
		
		try {
			while (accounts[from] < amount) {
				sufficientFunds.await();
			}
			
			System.out.println(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf(" %10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
			
			//对象的状态的改变可能有利于等待线程时，调用signalAll()
			sufficientFunds.signalAll();
		} finally {
			bankLock.unlock();
		}
		
	}
	
	/**
	 * 获取所有账户的余额和
	 * @return
	 */
	public double getTotalBalance() {
		bankLock.lock();
		try {
			double sum = 0;
			for (double a : accounts) {
				sum += a;
			}
			
			return sum;
		} finally {
			bankLock.unlock();
		}
	}
	
	/**
	 * 获得该银行的账户状态
	 * @return 账户数量
	 */
	public int size() {
		return accounts.length;
	}
	
}
