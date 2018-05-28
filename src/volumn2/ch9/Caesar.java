package volumn2.ch9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by HP-PC on 2018/5/28.
 */
public class Caesar {
    public static void main(String[] args) {
        System.out.println("输入要加密的class文件路径：");
        Scanner s = new Scanner(System.in);
        String source = s.nextLine();
        System.out.println("输入生成的加密文件路径：");
        String target = s.nextLine();
        System.out.println("输入加密密钥：");
        int key = s.nextInt();
        try {
            FileInputStream in = new FileInputStream(source);
            FileOutputStream out = new FileOutputStream(target);
            int ch;
            while ((ch = in.read()) != -1) {
                byte c = (byte) (ch + key);
                out.write(c);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
