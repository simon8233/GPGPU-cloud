package com.proc.compile;
import com.proc.model.*;
import com.proc.base_type.Task.*;
import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Execute_Thread_Ver extends Thread{
	private Execute_Service execute_Service = null;
	private Task task = null;
	private List<String> correct_info;
	private List<String> error_info;
	private Execute execute;
	private List<String> cmd;
	private ProcessBuilder pb;
	private Process p1;
	private BufferedReader processCorrectExecuteInfo = null;
	//private BufferedReader processErrorExecuteInfo = null;
	
	public Execute_Thread_Ver(List<String> cmd ,Execute exe){
		this.cmd = cmd;
		this.execute = exe;
		//this.start();
	}
	public Execute_Thread_Ver(List<String> cmd, Execute_Service execute_Service , Task task){
		this.cmd = cmd;
		this.execute_Service = execute_Service;
		this.task = task;
		pb = new ProcessBuilder(cmd);
		pb.redirectErrorStream(true);
		//this.run_Executeprocess(cmd);
		this.start();
	}
	public List<String> getExecuteInformation(){				
		return correct_info;
	}
	public List<String> getExecuteErrInformation(){
		return error_info;
	}
	public void run(){
		run_Executeprocess(cmd);
	}
		
	public Process getP1() {
		return p1;
	}
	public void setP1(Process p1) {
		this.p1 = p1;
	}
	public BufferedReader getprocessCorrectExecuteInfo(){
		while(processCorrectExecuteInfo == null){
			System.out.println("processCorrectExecuteInfo == null");
		}		
		return processCorrectExecuteInfo;
	}
/*	public BufferedReader getprocessErrorExecuteInfo(){
		while(processErrorExecuteInfo == null){
			System.out.println("processErrorExecuteInfo == null");
		}		
		return processErrorExecuteInfo;
		
	}*/
	
	public void run_Executeprocess(List<String> cmd){
		try{
			p1 = pb.start();
			this.processCorrectExecuteInfo = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			//p1.waitFor();
			
			while(true){
				try{
				//	System.out.println("execute process sleep 10 sec");
					Thread.sleep(10000);					
					if(p1.exitValue()==0){
						//System.out.println("is over");
						System.out.print(task.getTask_ID());
						task.getServer().getParent().printAllVmInfo();
						execute_Service.ShutDowm_VM(task);
						
						break;
					}
					else{
						//2cdlpqvSystem.out.println("is not normal over");
						execute_Service.ShutDowm_VM(task);
						break;
					}				
				}			
				catch(Exception e){
				//	System.out.println("Program is not over");
				}
			}
			
			
			// error and correct 訊息已被processBuilder的redirectErrorStream 合併\
		//	this.processErrorExecuteInfo = new BufferedReader(new InputStreamReader(p1.getErrorStream()));
			//this.processHybridCorrectErrorInfo = new BfferedReader(new InputStreamReader(pb.))
			//String[] cmd={"/usr/lib64/ccache/gcc","-o","/home/simon/java/network/server/hello_world_server","/home/simon/java/network/server/hello_world_server.c",};
			//String[] cmd={"/home/simon/java/network/server/gcc.sh"};
			//String line;
						/*
			while((line = processCorrectExecuteInfo.readLine()) != null){
				System.out.println(line);
			}
		//	correct_info = new ArrayList<String>(); //可用來放置Log資訊 供使用者察看
			//error_info = new ArrayList<String>(); //可用來放置Log資訊 供使用者察看
			
			while((line = processCorrectExecuteInfo.readLine()) != null){
			//System.out.println(line);
			// out.println(line+"</br>");						
			// correct_info.add(line); //saving to List maybe write to File 
			}
			while((line = processErrorExecuteInfo.readLine()) != null){				
			//	out.voidprintln(line+"</br>");
			//	error_info.add(line); //saving to List maybe write to File 
			}*/
			
		//	execute.destory();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
}
