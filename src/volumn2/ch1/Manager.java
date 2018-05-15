package volumn2.ch1;

/**
 * Created by HP-PC on 2018/5/14.
 */
public class Manager extends Employee {
    private Employee secretary;

    public Manager(String n, double s, int year, int month, int day) {
        super(n, s, year, month, day);
        secretary = null;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }

    @Override
    public String toString() {
        return super.toString() + "[secrectary=" + secretary + "]";
    }

}
