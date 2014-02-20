package com.proc.compile;

import java.io.BufferedReader;
import java.util.*;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Execute{
	private List<String> correct_info;
	private List<String> error_info;
	Process p1;
	public Execute(){
		
	}
	public List<String> getExecuteInformation(){				
		return correct_info;
	}
	public List<String> getExecuteErrInformation(){
		
		return error_info;
	}
	public void destory(){
		p1.destroy();
	}
	public void run_Executeprocess(String[] cmd , PrintWriter out){
		try{
			/*for(int i = 0;i<cmd.length;i++){
				System.out.println(cmd[i]);				
			}*/
			//String[] cmd={"/usr/lib64/ccache/gcc","-o","/home/simon/java/network/server/hello_world_server","/home/simon/java/network/server/hello_world_server.c",};
			//String[] cmd={"/home/simon/java/network/server/gcc.sh"};
			p1 = Runtime.getRuntime().exec(cmd); 
			/*
			String tmp = cmd[4];
			cmd[4] = "/Cloud/shell_script/moniter.sh";
			List<String> moniter_cmd = new LinkedList<String>();
			for(String str : cmd){
				moniter_cmd.add(str);
			}
			moniter_cmd.add(tmp);
			String[] test_moniter_cmd = new String[moniter_cmd.size()];
			Iterator<String> moniter_cmd_iter = moniter_cmd.iterator();
			for(int i = 0; moniter_cmd_iter.hasNext();i++){
				test_moniter_cmd[i] = moniter_cmd_iter.next();
			}
			System.out.println(" moniter _ command===="+moniter_cmd);
			*/
		/*	
			Process p2 = Runtime.getRuntime().exec(test_moniter_cmd);
			*/
			String line = "";
			
			//System.out.println("commond over");
			BufferedReader p_in = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			BufferedReader p_err = new BufferedReader(new InputStreamReader(p1.getErrorStream()));
			//BufferedReader moniter_process_in = new BufferedReader(new InputStreamReader(p2.getInputStream()));
			//BufferedReader moniter_process_error = new BufferedReader(new InputStreamReader(p2.getErrorStream()));
			// = new BufferedOutputStream(new DataOutputStream(p1.getOutputStream()));
			correct_info = new ArrayList<String>();
			error_info = new ArrayList<String>();
			while((line = p_in.readLine()) != null){
			//System.out.println(line);
				out.println(line+"</br>");	
				out.flush();
			// correct_info.add(line); //saving to List maybe write to File 
			}
			while((line = p_err.readLine()) != null){				
				out.println(line+"</br>");
				out.flush();
			//	error_info.add(line); //saving to List maybe write to File 
			}
			p_in.close();
			p_err.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
}
