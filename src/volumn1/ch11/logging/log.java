package volumn1.ch11.logging;

import java.util.logging.*;

public class log {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("com.lee");
		logger.setLevel(Level.ALL);
		logger.warning("**************8");
	}
}
