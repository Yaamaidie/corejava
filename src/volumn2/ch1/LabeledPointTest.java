package StreamAndFile;

import java.awt.geom.Point2D;
import java.io.*;

/**
 * 测试“1.5.2修改默认的序列化机制”
 */
public class LabeledPointTest implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        LabeledPoint p = new LabeledPoint(1.1, 2.1);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("LabeledPoint.dat"));
        out.writeObject(p);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("LabeledPoint.dat"));
        LabeledPoint newP = (LabeledPoint) in.readObject();
        System.out.println(newP);
    }
}

class LabeledPoint implements Serializable {
    private transient Point2D.Double point;//不可序列化

    LabeledPoint(double x, double y) {
        point = new Point2D.Double(x, y);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeDouble(point.getX());
        out.writeDouble(point.getY());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        double x = in.readDouble();
        double y = in.readDouble();
        point = new Point2D.Double(x, y);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[x=" + point.getX() + ", y=" + point.getY() + "]";
    }
}
