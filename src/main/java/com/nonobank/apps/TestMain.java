package com.nonobank.apps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestMain {

	private static RandomAccessFile raf;
	private static FileOutputStream fos;
	private static FileInputStream fis;
	private static long endIndex;
	private static File temp = null;

	public static void main(String[] args) {
		try {
			raf = new RandomAccessFile(new File("D:\\workspace\\testfilepakage\\4444.txt"), "rw");
			temp = new File("D:\\workspace\\testfilepakage\\temp.txt");
			String[] strs = { "aaaaaaaaaaaaaaaa", "bbbbbbbbbb", "ccccccccccccc", "ddddddddddddddddd" };
			int pos  = 200;
			for (String string : strs) {
				bbb(string, pos);
				pos=(int) endIndex;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void bbb(String content, int pos) {
		try {
			System.out.println("********************************");

			// int pos = 200;
			// String content = "\r\n此处是插入的文本内容！\r\n";
			raf.seek(200);

			fos = new FileOutputStream(temp);
			fis = new FileInputStream(temp);
			temp.deleteOnExit();
			int len = 0;
			byte[] buf = new byte[256];
			while ((len = raf.read(buf)) != -1) {
				fos.write(buf, 0, len); // 插入点以后的文件内容写入到临时文件中
			}
			sop("重新定位指针之前指针的位置：" + raf.getFilePointer());

			raf.seek(pos);
			sop("写入内容之前的指针：" + raf.getFilePointer());

			raf.write(content.getBytes());
			endIndex = raf.getFilePointer();
			sop("写入内容之后的指针：" + endIndex);

			// int len1 = 0;
			// while((len1 = fis.read(buf))!=-1)//将临时文件中内容读出来，重新写到文件中
			// {
			// raf.write(buf,0,len1);
			// }

		} catch (IOException e) {
			e.printStackTrace();
			sop(e.toString());
		}

	}

	private static void sop(String string) {
		System.out.println("********************string=" + string);

	}
}
