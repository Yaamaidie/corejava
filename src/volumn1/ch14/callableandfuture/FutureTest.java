package volumn1.ch14.callableandfuture;

import java.util.concurrent.*;
import java.io.*;
import java.util.*;

/**
 * 
 * @author lee
 *
 */
public class FutureTest {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory (e.g. /user/local/jdk1.6.0/src): ");
		String directory = in.nextLine();
		System.out.print("Enter keyword (e.g. volatile): ");
		String keyword = in.nextLine();
		
		MatchCounter counter = new MatchCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<>(counter);
		Thread t = new Thread(task);
		t.start();
		
		try {
			System.out.println(task.get() + " matching files.");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
}

/**
 * 任务：统计指定目录下的含有指定关键字的文件的数量
 * 实现流程：对于每个子目录创建一个线程，执行用FutureTask包装的Callable<Integer>任务，调用任务的get()方法将返回该子目录下的文件数量；
 * 对于每一个文件，直接找到匹配的文件数量；
 * 最后将每个子目录的匹配的文件数量和每个文件的匹配文件数量相加后返回。
 * @author lee
 *
 */
class MatchCounter implements Callable<Integer> {
	
	private int count;
	private File directory;
	private String keyword;
	
	public MatchCounter(File dir, String k) {
		directory = dir;
		keyword = k;
	}
	
	public Integer call() throws Exception {
		File[] files = directory.listFiles();
		//对于每个目录，新建一个FutureTask用来存放匹配的文件数量
		ArrayList<FutureTask<Integer>> results = new ArrayList<>();
		
		for (File f : files) {
			if (f.isDirectory()) {
				MatchCounter counter = new MatchCounter(f, keyword);
				FutureTask<Integer> task = new FutureTask<>(counter);
				results.add(task);
				Thread t = new Thread(task);
				t.start();
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
