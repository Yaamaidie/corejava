package volumn2.ch3;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by HP-PC on 2018/5/21.
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("time-A.timefreq.bldrdoc.gov", 13);
        s.setSoTimeout(1000);
        try {
            InputStream inputStream = s.getInputStream();
            Scanner in = new Scanner(inputStream);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            s.close();
        }

    }
}
