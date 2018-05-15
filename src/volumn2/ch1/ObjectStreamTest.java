package volumn2.ch1;

import java.io.*;

/**
 * Created by HP-PC on 2018/5/14.
 */
public class ObjectStreamTest {
    public static void main(String[] args) {

        Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        Manager carl = new Manager("Carl Cracker", 75000, 1987, 12, 15);
        carl.setSecretary(harry);
        Manager tony = new Manager("Tony Tester", 4000, 1990, 3, 15);
        tony.setSecretary(harry);

        Employee[] staff = new Employee[3];
        staff[0] = carl;
        staff[1] = harry;
        staff[2] = tony;

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.dat"));
            out.writeObject(staff);
            out.close();

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.dat"));
            Employee[] newStaff = (Employee[]) in.readObject();
            in.close();
            for (Employee e : newStaff) {
                System.out.println(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
