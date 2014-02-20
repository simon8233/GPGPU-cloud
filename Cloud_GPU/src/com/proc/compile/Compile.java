package com.proc.compile;
import java.io.*;
import java.util.*;



public class Compile implements Serializable{
	
	private List<String> correct_info;
	private List<String> error_info;
	
	public List<String> getCompileInformation(){				
		return correct_info;
	}
	public List<String> getCompileErrInformation(){
		
		return error_info;
	}
	public void run_Compileprocess(String[] cmd,File userWorkspace){

		try{
			
			//String compiler = "/usr/bin/gcc"; // 改至Task內修改
			//cmd[4] = compiler;
			
			/*for(int i = 0;i<cmd.length;i++){
				System.out.println(cmd[i]);	
			
			}*/
			//String[] cmd={"/usr/lib64/ccache/gcc","-o","/home/simon/java/network/server/hello_world_server","/home/simon/java/network/server/hello_world_server.c",};
			//String[] cmd={"/home/simon/java/network/server/gcc.sh"};					
			ProcessBuilder pb= new ProcessBuilder(cmd);

			pb.directory(userWorkspace); // 設定工作目錄
			
			Process p1 = pb.start(); //執行compile
			
						 //Runtime.getRuntime().exec(cmd,,userWorkspace);
			String line = "";
			//System.out.println("commond over"); 
			BufferedReader p_in = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			BufferedReader p_err = new BufferedReader(new InputStreamReader(p1.getErrorStream()));
			//p_sen_cli = new BufferedOutputStream(new DataOutputStream(p1.getOutputStream()));
			correct_info = new ArrayList<String>();
			error_info = new ArrayList<String>();
			//p_sen_cli.wr
			while((line = p_in.readLine()) != null){
				//System.out.println(line+"inner");				
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
	
		
	public void run_Compileprocess(String[] cmd){

		try{
			
			//String compiler = "/usr/bin/gcc"; // 改至Task內修改
			//cmd[4] = compiler;
			
			/*for(int i = 0;i<cmd.length;i++){
				System.out.println(cmd[i]);	
			
			}*/
			//String[] cmd={"/usr/lib64/ccache/gcc","-o","/home/simon/java/network/server/hello_world_server","/home/simon/java/network/server/hello_world_server.c",};
			//String[] cmd={"/home/simon/java/network/server/gcc.sh"};
			System.out.println("run_program");
			Process p1 = Runtime.getRuntime().exec(cmd); 
			String line = "";
			//System.out.println("commond over"); 
			BufferedReader p_in = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			BufferedReader p_err = new BufferedReader(new InputStreamReader(p1.getErrorStream()));
			//p_sen_cli = new BufferedOutputStream(new DataOutputStream(p1.getOutputStream()));
			correct_info = new ArrayList<String>();
			error_info = new ArrayList<String>();
			//p_sen_cli.wr
			while((line = p_in.readLine()) != null){
				//System.out.println(line+"inner");				
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