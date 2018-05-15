package volumn2.ch1;

import java.io.*;

/**
 * Created by HP-PC on 2018/5/14.
 */
public class RandomFileTest {
    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 4000, 1990, 3, 15);

        //保存所有雇员到employee.dat中
        DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat"));
        for (Employee e : staff) {
            e.writeData(out);
        }
        out.close();

        //将所有记录提取出来放到一个数组
        RandomAccessFile in = new RandomAccessFile("employee.dat", "r");
        //计算记录数
        int recordCount = (int) (in.length() / Employee.RECORD_SIZE);
        Employee[] newStaff = new Employee[recordCount];

        //开始倒序读
        for (int i = recordCount - 1; i >= 0; i--) {
            newStaff[i] = new Employee();
            in.seek(i * Employee.RECORD_SIZE);
            newStaff[i].readData(in);
        }
        in.close();

        //打印读回的数据
        for (Employee e : newStaff) {
            System.out.println(e);
        }
    }

}

class DataIO {
    public static void writeFixedString(String s, int size, DataOutput out) throws IOException {
        for (int i = 0; i < size; i++) {
            char ch = 0;
            if (i < s.length()) {
                ch = s.charAt(i);
            }
            out.writeChar(ch);
        }
    }

    public static String readFixedString(int size, DataInput in) throws IOException {
        StringBuilder b = new StringBuilder(size);
        int i = 0;
        boolean more = true;
        while (more && i < size) {
            char c = in.readChar();
            i++;
            if (c == 0) {
                more = false;
            } else {
                b.append(c);
            }
        }
        in.skipBytes(2 * (size - i));
        return b.toString();
    }
}