package ch5;

public class Employee {
	private String name;
	private int age;
	//工资
	private double salary;
	
	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public double getSalary() {
		return this.salary;
	}
	
	//一个类的方法可以访问所属类的所有私有特性，而不仅仅限于访问隐式参数的私有特性
//	boolean equal(Employee e) {
//		return name.equals(e.name);
//	}
	public boolean equals(Object otherObject) {
		if (this == otherObject) {
			return true;
		}
		if (otherObject == null) {
			return false;
		}
		if (getClass() != otherObject.getClass()) {
			return false;
		}
		Employee other = (Employee) otherObject;
		return name.equals(other.name) 
				&& salary == other.salary
				&& age == other.age;
	}
	public static void main(String[] args) {
		Employee staff = new Employee("scott", 12);
		System.out.println(staff.getClass());
		System.out.println(staff.getClass().getName());
		System.out.println(staff.getName());
	}
}
