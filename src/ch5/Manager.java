package ch5;

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
		return super.getSalary() + bonus;
	} 
	
	@Override
	public boolean equals(java.lang.Object otherObject) {
		if (!super.equals(otherObject)) {
			return false;
		}
		Manager other = (Manager) otherObject;
		return bonus == other.bonus;
	}
}
