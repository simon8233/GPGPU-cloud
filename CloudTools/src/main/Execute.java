package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Execute {
	private ArrayList<String> correct_info;
	private ArrayList<String> error_info;
	public ArrayList<String> getExecuteInformation(){				
		return correct_info;
	}
	public ArrayList<String> getExecuteErrInformation(){
		
		return error_info;
	}
	
	public void run_Executeprocess(String[] cmd){
		try{
					
			Process p1 = Runtime.getRuntime().exec(cmd); 
			String line = "";
			
			
			BufferedReader p_in = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			BufferedReader p_err = new BufferedReader(new InputStreamReader(p1.getErrorStream()));
			
			correct_info = new ArrayList<String>();
			error_info = new ArrayList<String>();
			while((line = p_in.readLine()) != null){
				correct_info.add(line);
			}
			while((line = p_err.readLine()) != null){
				error_info.add(line);
			
			}
			p_in.close();
			p_err.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	 

}
