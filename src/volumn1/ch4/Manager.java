package volumn1.ch4;

public class Manager extends Employee{
	//奖金
	private double bonus;
	
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	public Manager(String name, int age) {
		super(name, age);
	}
	
	//覆写
	public double getSalary() {
		return super.getSalay() + bonus;
	} 
}
