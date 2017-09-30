package volumn1.ch14.blockingqueue;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * 阻塞队列演示，程序在一个目录及其所有子目录下搜索所有文件，打印出包含指定关键字的行
 * 
 * @author lee
 *
 */
public class BlockingQueueTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory (e.g. /user/local/jdk1.6.0/src): ");
		String directory = in.nextLine();
		System.out.print("Enter keyword (e.g. volatile): ");
		String keyword = in.nextLine();
		
		final int FILE_QUEUE_SIZE = 100;
		final int SEARCH_THREADS = 100;
		
		BlockingQueue<File> queue = new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);
		FileEnumerationTask enumrator = new FileEnumerationTask(queue, new File(directory));
		new Thread(enumrator).start();
		for (int i = 1; i <= SEARCH_THREADS; i++) {
			new Thread(new SearchTask(queue, keyword)).start();
		}
	}
	
}

/**
 * 这个类枚举某个目录下的所有文件和子目录
 * @author lee
 *
 */
class FileEnumerationTask implements Runnable {

	public static File DUMMY = new File("");

	private BlockingQueue<File> queue;
	private File startingDirectory;

	/**
	 * 构造器
	 * 
	 * @param q 用来放枚举完成的文件的阻塞队列
	 * @param d 枚举文件开始的目录
	 */
	public FileEnumerationTask(BlockingQueue<File> q, File d) {
		queue = q;
		startingDirectory = d;
	}

	public void run() {
		try {
			enumerate(startingDirectory);
			queue.put(DUMMY);
		} catch (InterruptedException e) {

		}
	}
	
	/**
	 * 递归的遍历所有子目录和文件
	 * @param directory
	 * @throws InterruptedException
	 */
	public void enumerate(File directory) throws InterruptedException {
		File[] files = directory.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				enumerate(f);
			} else {
				queue.put(f);
			}
		}
	}

}

class SearchTask implements Runnable {

	private BlockingQueue<File> queue;
	private String keyword;

	public SearchTask(BlockingQueue<File> q, String k) {
		queue = q;
		keyword = k;
	}

	public void run() {
		try {
			boolean done = false;
			while (!done) {
				File file = queue.take();
				if (file == FileEnumerationTask.DUMMY) {//取到最后一个了，不再
					queue.put(file);//放回去是让别的线程也知道取到最后一个，否则while循环无法结束
					done = true;
				} else {
					search(file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {

		}
	}

	/**
	 * 搜索文件，打印所有匹配关键字的行
	 * @param file 要搜索的文件
	 * @throws FileNotFoundException
	 */
	public void search(File file) throws FileNotFoundException {
		Scanner in = new Scanner(new FileInputStream(file));
		int lineNumber = 0;
		while (in.hasNextLine()) {
			lineNumber++;
			String line = in.nextLine();
			if (line.contains(keyword)) {
				System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber,
						line);
			}
		}
		in.close();
	}
}
