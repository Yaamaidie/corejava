package ch5.reflection;

/**
 * 运行时的Class类
 * @author lee
 *
 */
public class TheClassClass {
	public static void main(String[] args) {
		//对象的getClass()方法获取Class对象
		ch5.Employee e = new ch5.Employee("jack", 12);
		Class c1 = e.getClass();
		System.out.println(c1.getName());
		
		//Class.forName(类名)获取一个类的Class对象
		String date = "java.util.Date";
		Class c2 = null;
		try {
			c2 = Class.forName(date);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		System.out.println(c2.getName());
		
		//直接用类型名.class获取对应的对象
		Class c3 = int.class;
		Class c4 = java.util.Date.class;
		Class c5 = Double[].class;
		System.out.println(c3.getName());
		System.out.println(c4.getName());
		System.out.println(c5.getName());
		
		//只能使用==比较Class对象
		if (ch5.Employee.class == e.getClass()) {
			System.out.println("ok");
		}
		
		//使用Class.newInstance()方法快速创建一个类的实例
		ch5.Employee employee = null;
		try {
			employee = e.getClass().newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		System.out.println(employee.getAge());
		
		//使用forName()和newInstance()方法根据类名快速创建一个类的实例
		String className = "java.util.Date";
		try {
			Object o = Class.forName(className).newInstance();
			System.out.println(o.toString());
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
	}
}
