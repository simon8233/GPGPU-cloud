package com.net.io;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class ResponseAnsterToUser {
	
	public static void getView(List<String> correct , List<String> error,PrintWriter out) {
		Iterator<String> iter , err_iter;
		
		iter = correct.iterator();
		err_iter = error.iterator();
		
		while(iter.hasNext()){
			String tmp = iter.next();
			out.println(tmp+"<br/>");
			//System.out.println("<List>"+tmp);
		}
		if(err_iter.hasNext()){
			out.write("Error  information!!!!!!!!! <br/>");
			while(err_iter.hasNext()){
				String tmp = err_iter.next();
				out.println(tmp+"<br/>");			
				//System.out.println("<List>"+tmp);
			}	
		}
	}
	
}
