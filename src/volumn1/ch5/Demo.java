package volumn1.ch5;

import org.junit.Test;

public class Demo {
	@Test
	public void 数组协变() throws Exception {
		Manager[] managers = new Manager[3];
		Employee[] employees = managers;
		employees[0] = new Employee("scott", 13);
	}
	@Test
	public void 覆写() throws Exception {
		Employee manager = new Manager("tom", 12);
	}
	@Test
	public void equals() throws Exception {
		Employee employee = new Employee("scott", 11);
		employee.setSalary(1000);
		Manager staff = new Manager("scott", 11);
		staff.setSalary(1000);
		staff.setBonus(500);
		System.out.println(staff.equals(employee));
	}
	@Test
	public void 散列码() throws Exception {
		String s = new String("string");
		int h = s.hashCode();
		System.out.println(h);
		
	}
	@Test
	public void 参数数量可变() throws Exception {
		double max = findMax(1, 2, 3, 4.6, 12.3, 89.2, 13.2);
		System.out.println(max);
	}
	public static double findMax(double... values) {
		double max = Double.MIN_VALUE;
		for (double v : values) {
			if (v > max) {
				max = v;
			}
		}
		return max;
	}
	@Test
	public void 枚举类型() throws Exception {
		System.out.println(Size.SMALL);
		//Enum的静态valueOf方法将返回一个
		Size e = Enum.valueOf(Size.class, "SMALL");
		System.out.println(e.getClass().getName());
		System.out.println(e.getAbbreviation());
		System.out.println(Size.values());
	}
	public enum Size {
		//该枚举类型的4个instance
		SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");
		
		private Size(String abbreviation) {
			this.abbreviation = abbreviation;
		}
		
		public String getAbbreviation() {
			return this.abbreviation;
		}
		
		//缩写，构造器参数
		private String abbreviation;
	}
	
	/**
	 * 反射
	 */
	@Test
	public void Class类() throws Exception {
		//三种获得Class类实例的方法
		//1.
		Class<Employee> c1 = Employee.class;
		//2.
		Class<? extends Employee> c2 = new Employee("scott", 12).getClass();
		//3.
		String className = "ch5.Employee";
		Class<?> c3 = Class.forName(className);
		System.out.println(c1.getName() + " " + c2.getName() + " " + c3.getName());
		//创建与某个对象具有相同类型的实例
		Employee staff = new Employee("staff", 12);
		Employee copy = staff.getClass().newInstance();//此方法只能调用无参构造器，若没有找到，则爆出一个异常。
		System.out.println(copy);
	}
	
	@Test
	public void 利用反射分析类的结构() throws Exception {
		//java.lang.reflect包中有3个主要的类Filed, Method, Constructor分别描述类的域， 方法， 构造器。
	}
	
	
}
