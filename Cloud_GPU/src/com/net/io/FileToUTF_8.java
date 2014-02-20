package com.net.io;
import java.util.*;
import java.io.*;

import org.apache.commons.fileupload.util.Streams;


public class FileToUTF_8 {

	public static File getFileToUtf_8(File file){
		
			try {
				
				BufferedReader in =  new BufferedReader(new InputStreamReader(new FileInputStream(file),"Big5"));
				File file_tmp = new File(file.getPath()+"_tmp");
				//System.out.println(file.getPath()+"_tmp"); // create a file to temporary store data.
				PrintWriter out =  new PrintWriter(new OutputStreamWriter(new FileOutputStream(file_tmp),"UTF8"));
				
				String line = "";
				
					while((line = in.readLine()) != null){
						out.write(line);
						out.append('\n');
					}
					out.flush();
					in.close();
					out.close();
				
					byte[] buff = new byte[1]; 
					BufferedInputStream in_tmp =  new BufferedInputStream(new FileInputStream(file_tmp));
					BufferedOutputStream out_tmp =  new BufferedOutputStream(new FileOutputStream(file));
					Streams.copy(in_tmp, out_tmp, true);
					out_tmp.flush();
					in_tmp.close();
					out_tmp.close();
					
				return file;
			} 						
			  catch (FileNotFoundException e) {
				System.out.println("File Exception encoding on transform");
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				System.out.println("File encoding false because have not this encoding");
				e.printStackTrace();
			}
			  catch (IOException e){
				System.out.println("error");
				
			}
			return file;	
	}
	
}
