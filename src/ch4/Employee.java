package ch4;

import java.util.Date;

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
	
	public double getSalay() {
		return this.salary;
	}
	
	//一个类的方法可以访问所属类的所有私有特性，而不仅仅限于访问隐式参数的私有特性
	boolean equal(Employee e) {
		return name.equals(e.name);
	}
}
