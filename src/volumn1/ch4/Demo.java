package volumn1.ch4;


public class Demo {
	public static void main(String[] args) {
		String s = "java.util.LinkedList";
		try {
			Object mObject = Class.forName(s).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
