package volumn2.ch1;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by HP-PC on 2018/5/14.
 */
class Employee implements Serializable, Externalizable {
    public static final int NAME_SIZE = 40;
    public static final int RECORD_SIZE = 2 * NAME_SIZE + 8 + 4 + 4 + 4;

    private String name;
    private double salary;
    private Date hireDay;

    public Employee() {
    }

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        this.hireDay = calendar.getTime();
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
    }

    public void writeData(DataOutput out) throws IOException {
        DataIO.writeFixedString(name, NAME_SIZE, out);
        out.writeDouble(salary);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(hireDay);
        out.writeInt(calendar.get(Calendar.YEAR));
        out.writeInt(calendar.get(Calendar.MONTH) + 1);
        out.writeInt(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void readData(DataInput in) throws IOException {
        name = DataIO.readFixedString(NAME_SIZE, in);
        salary = in.readDouble();
        int y = in.readInt();
        int m = in.readInt();
        int d = in.readInt();
        GregorianCalendar calendar = new GregorianCalendar(y, m - 1, d);
        hireDay = calendar.getTime();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeDouble(salary);
        out.writeLong(hireDay.getTime());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        salary = in.readDouble();
        hireDay = new Date(in.readLong());
    }
}
