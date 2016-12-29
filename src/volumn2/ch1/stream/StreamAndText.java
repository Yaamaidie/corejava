package volumn2.ch1.stream;

import java.io.*;
	
public class StreamAndText {
	
	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("line.separator"));
		OutputStreamWriter writer = new java.io.FileWriter("AD.TXT", true);
		writer.write("你好啊");
		writer.close();
		
		OutputStream out = new java.io.FileOutputStream("ds.txt", true);
		byte[] data = "你好啊".getBytes();
		out.write(data);
		out.close();
		
		//字符流使用指定的编码方式将字符流转换为字节流
		OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream("adf.txt", true), "GBK");
		w.write("你好啊");
		w.close();
		
		FileWriter fileWriter = new FileWriter("po.txt");
		fileWriter.write("hello");
		fileWriter.close();
		
		PrintWriter pWriter = new java.io.PrintWriter("pw.txt", "GBK");
		pWriter.println("123");
		pWriter.println("321");
		pWriter.close();
	}
	
}
