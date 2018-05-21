package volumn2.ch3;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Created by HP-PC on 2018/5/21.
 */
public class InterruptibleSocketTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new InterruptibleSocketFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class InterruptibleSocketFrame extends JFrame {
    private final static int WIDTH = 300;
    private final static int HEIGHT = 300;

    private Scanner in;
    private JButton interruptibleButton;
    private JButton blockingButton;
    private JButton cancelButton;
    private JTextArea messages;
    private TestServer server;
    private Thread connectThread;

    InterruptibleSocketFrame() {
        setSize(WIDTH, HEIGHT);
        setTitle("InterruptibleSocketTest");

        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);

        messages = new JTextArea();
        add(new JScrollPane(messages));

        interruptibleButton = new JButton("Interruptible");
        blockingButton = new JButton("Blocking");

        northPanel.add(interruptibleButton);
        northPanel.add(blockingButton);

        interruptibleButton.addActionListener(e -> {
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread = new Thread(() -> {
                try {
                    connectInterruptibly();
                } catch (IOException e1) {
                    messages.append("\nInterruptibleSocketTest.connectInterruptibly: " + e1);
                }
            });
            connectThread.start();
        });

        blockingButton.addActionListener(e -> {
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread = new Thread(() -> {
                try {
                    connectBlocking();
                } catch (IOException e1) {
                    messages.append("\nInterruptibleSocketTest.connectBlocking: " + e1);
                }
            });
            connectThread.start();
        });


        cancelButton = new JButton("Cannel");
        cancelButton.setEnabled(false);
        northPanel.add(cancelButton);
        cancelButton.addActionListener(e -> {
            connectThread.interrupt();
            cancelButton.setEnabled(false);
        });

        server = new TestServer();
        new Thread(server).start();

    }

    private void connectInterruptibly() throws IOException {
        messages.append("Interruptible:\n");
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8189));
        try {
            in = new Scanner(channel);
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("Reading ");
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        } finally {
            channel.close();
            EventQueue.invokeLater(() -> {
                messages.append("Channel closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }

    private void connectBlocking() throws IOException {
        messages.append("Blocking:\n");
        Socket s = new Socket("localhost", 8189);
        try {
            in = new Scanner(s.getInputStream());
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("Reading ");
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        } finally {
            s.close();
            EventQueue.invokeLater(() -> {
                messages.append("Socket closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }

    private class TestServer implements Runnable {

        @Override
        public void run() {
            try {
                ServerSocket s = new ServerSocket(8189);
                while (true) {
                    Socket incoming = s.accept();
                    Runnable r = new TestserverHandler(incoming);
                    Thread t = new Thread(r);
                    t.start();
                }
            } catch (IOException e) {
                messages.append("\nTestServer.run: " + e);
            }
        }
    }

    private class TestserverHandler implements Runnable {

        private Socket incoming;
        private int counter;

        TestserverHandler(Socket incoming) {
            this.incoming = incoming;
        }

        @Override
        public void run() {
            try {
                OutputStream outputStream = incoming.getOutputStream();
                PrintWriter out = new PrintWriter(outputStream, true);
                while (counter < 100) {
                    counter++;
                    if (counter <= 10) {
                        out.println(counter);
                    }
                    Thread.sleep(100);
                    incoming.close();
                    messages.append("Closing server\n");
                }
            } catch (Exception e) {
                messages.append("\nTestServerHandler.run: " + e);
            }
        }
    }

}


