package com.net.io;
import java.io.*;
import org.apache.commons.io.FileUtils;
public class UTF8EncodingTest {

	
	public static void showBinary(String s){
				showBinary(s.getBytes());
	}
	public static void showBinary(byte[] buf){
		for(byte b:buf){
			
			System.out.printf("%x",b);
		}
		System.out.println();
	}
	
	
	
	public static boolean isUTF8(File file){
		try{
			byte[] buf = FileUtils.readFileToByteArray(file);
			//System.out.println("\t<<>>");
			//showBinary(buf);
			String UTF8content = FileUtils.readFileToString(file,"UTF-8");
			//showBinary(UTF8content);
	//		String big5content = new String(buf , "Big5");
	//		String defcontent = new String(buf);
/*			System.out.println("\t<<<>>\n"+UTF8content);
			showBinary(UTF8content);
			System.out.println("\t<<<>>\n"+big5content);
			showBinary(big5content);
			System.out.println("\t<<<>>\n"+defcontent);
			showBinary(defcontent);
*/			
			// 因為其中文與英文的編碼不同 Big5的讀取方法與UTF-8不同
			
			if(buf.length == UTF8content.getBytes().length){
				byte[] buf_utf8 = UTF8content.getBytes();
				for(int i = 0;i<buf_utf8.length;i++)
					if(buf_utf8[i]!=buf[i]){
						//System.out.println("進了後被buf幹掉");
						return false;
					}
				return true;
			}
			
		} catch (IOException e){
			e.printStackTrace();
		}
		//System.out.println("沒進if");
		return false;
		
	}
}
