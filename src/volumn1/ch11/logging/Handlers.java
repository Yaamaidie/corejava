package volumn1.ch11.logging;

import java.io.IOException;
import java.util.logging.*;
/**
 * 处理器
 * @author lee
 *
 */
public class Handlers {
	
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("com.lee");
		logger.setLevel(Level.FINE);
		logger.setUseParentHandlers(false);
		//控制台处理器：ConsoleHandler
		Handler handler = new ConsoleHandler();
		handler.setLevel(Level.FINE);
		logger.addHandler(handler);
		logger.log(Level.FINE, "hello");
		
		//文件处理器：FileHandler，生成位于用户主目录下java0.log的xml格式文件
		try {
			FileHandler fileHandler = new FileHandler();
			logger.addHandler(fileHandler);
			logger.log(Level.FINE, "yaa");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
