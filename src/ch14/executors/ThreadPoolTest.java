package ch14.executors;

import java.util.*;
import java.io.*;
import java.util.concurrent.*;

/**
 * 使用线程池的一个演示
 * @author lee
 *
 */
public class ThreadPoolTest {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory (e.g. /user/local/jdk1.6.0/src): ");
		String directory = in.nextLine();
		System.out.print("Enter keyword (e.g. volatile): ");
		String keyword = in.nextLine();
		
		ExecutorService pool = Executors.newCachedThreadPool();
		MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
		Future<Integer> result = pool.submit(counter);
		
		try {
			System.out.println(result.get() + " matching files.");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		pool.shutdown();
		
		int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
		System.out.println("large pool size = " + largestPoolSize);
	}
	
}

class MatchCounter implements Callable<Integer> {
	
	private int count;
	private File directory;
	private String keyword;
	private ExecutorService pool;
	
	public MatchCounter(File dir, String k, ExecutorService p) {
		directory = dir;
		keyword = k;
		pool = p;
	}
	
	public Integer call() throws Exception {
		File[] files = directory.listFiles();
		ArrayList<Future<Integer>> results = new ArrayList<>();
		
		for (File f : files) {
			if (f.isDirectory()) {
				MatchCounter counter = new MatchCounter(f, keyword, pool);
				Future<Integer> result = pool.submit(counter);
				results.add(result);
				
			} else {
				if (search(f)) {
					count++;
				}
			}
		}
		for (Future<Integer> result : results) {
			try {
				count += result.get();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public boolean search(File file) {
		try {
			Scanner in = new Scanner(new FileInputStream(file));
			boolean found = false;
			while (!found && in.hasNextLine()) {
				String line = in.nextLine();
				if (line.contains(keyword)) {
					found = true;
				}
			}
			in.close();
			return found;
		} catch (IOException e) {
			return false;
		}
	}
	
}
