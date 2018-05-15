package volumn2.ch1;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;

/**
 * Created by HP-PC on 2018/5/14.
 */
public class NIOTest {
    public static long checksumInputStream(String filename) throws IOException {
        InputStream in = new FileInputStream(filename);
        CRC32 crc = new CRC32();

        int c;
        while ((c = in.read()) != -1) {
            crc.update(c);
        }
        return crc.getValue();
    }

    public static long checksumBufferedInputStream(String filename) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(filename));
        CRC32 crc = new CRC32();

        int c;
        while ((c = in.read()) != -1) {
            crc.update(c);
        }
        return crc.getValue();
    }

    public static long checksumRandomAccessFile(String filename) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filename, "r");
        long legth = file.length();
        CRC32 crc = new CRC32();

        for (long p = 0; p < legth; p++) {
            file.seek(p);
            int c = file.readByte();
            crc.update(c);
        }
        return crc.getValue();
    }

    public static long checksumMappedFile(String filename) throws IOException {
        FileInputStream in=new FileInputStream(filename);
        FileChannel channel=in.getChannel();

        CRC32 crc = new CRC32();
        int length= (int) channel.size();
        MappedByteBuffer buffer=channel.map(FileChannel.MapMode.READ_ONLY, 0, length);

        for (int p = 0; p < length; p++) {
            int c=buffer.get(p);
            crc.update(c);
        }
        return crc.getValue();
    }

    public static void main(String[] args) throws IOException {
        String filename="D:\\Users\\Administrator\\home\\个人资料\\软件\\jdk-8u51-windows-x64.exe";

        System.out.println("Bufferd Input Stream:");
        long start=System.currentTimeMillis();
        long crcValue=checksumBufferedInputStream(filename);
        long end=System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end-start)+" milliseconds");

        System.out.println("Mapped File:");
        start=System.currentTimeMillis();
        crcValue=checksumMappedFile(filename);
        end=System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end-start)+" milliseconds");

    }
}
