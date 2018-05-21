package volumn2.ch3;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by HP-PC on 2018/5/21.
 */
public class URLConnectionTest {
    public static void main(String[] args) {
        try {
            String urlName = "http://www.yinwang.org/blog-cn/2018/04/13/csbook-chapter1";
            URL url = new URL(urlName);
            URLConnection connection = url.openConnection();

            connection.connect();

            //打印返回头
            Map<String, List<String>> headers = connection.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                for (String value : entry.getValue()) {
                    System.out.println(key + ": " + value);
                }
            }

            //打印常用的响应头
            System.out.println("-----------");
            System.out.println("getContentType: " + connection.getContentType());
            System.out.println("getContentLength: " + connection.getContentLength());
            System.out.println("getContentEncoding: " + connection.getContentEncoding());
            System.out.println("getDate: " + connection.getDate());
            System.out.println("getExpiration: " + connection.getExpiration());
            System.out.println("getLastModified: " + connection.getLastModified());
            System.out.println("-----------");

            Scanner in = new Scanner(connection.getInputStream());

            //打印返回内容的前十行
            for (int i = 0; i < 100 && in.hasNextLine(); i++) {
                System.out.println(in.nextLine());
            }
            if (in.hasNextLine()) {
                System.out.println("...");
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
