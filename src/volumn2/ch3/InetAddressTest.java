package volumn2.ch3;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by HP-PC on 2018/5/21.
 */
public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        String host = "www.baidu.com";
        InetAddress[] addresses = InetAddress.getAllByName(host);
        for (InetAddress a : addresses) {
            System.out.println(a);
        }
    }
}
