package volumn2.ch3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by HP-PC on 2018/5/21.
 */
public class EchoServer {
    public static void main(String[] args) {
        try {
            //建立服务器
            ServerSocket s = new ServerSocket(8189);

            //等待客户端连接
            Socket incoming = s.accept();
            try {
                InputStream inputStream = incoming.getInputStream();
                OutputStream outputStream = incoming.getOutputStream();

                Scanner in = new Scanner(inputStream);
                PrintWriter out = new PrintWriter(outputStream, true);

                out.println("Hello, Enter BYE to exit.");

                //回显客户端输入
                boolean done = false;
                while (!done && in.hasNextLine()) {
                    String line = in.nextLine();
                    out.println("Echo: " + line);
                    if (line.trim().equals("BYE")) {
                        done = true;
                    }
                }
            } finally {
                incoming.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
