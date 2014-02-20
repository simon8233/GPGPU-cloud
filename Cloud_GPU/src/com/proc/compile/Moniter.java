package com.proc.compile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.proc.base_type.Task.Task;
import com.proc.model.Execute_Service;

public class Moniter extends Thread {
	private Execute_Service execute_Service = null;
	private Task task = null;
	private List<String> correct_info;
	private List<String> error_info;
	private Execute_Thread_Ver execute;
	private List<String> cmd;
	private ProcessBuilder pb;
	private Process p1;
	private BufferedReader processCorrectExecuteInfo = null;
	
	public Moniter(List<String> cmd,Execute_Thread_Ver execute){
		this.cmd = cmd;		
		this.execute = execute;
		pb = new ProcessBuilder(cmd);
		pb.redirectErrorStream(true);
		this.start();
	}
	public BufferedReader getProcessCorrectExecuteInfo() {
		while(processCorrectExecuteInfo == null){
			try{
				Thread.sleep(1000);
			}
			catch(Exception e){
				System.out.println("sleep error");
			}			
		}		
		return processCorrectExecuteInfo;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.run_Executeprocess();
	}
	public void run_Executeprocess(){
		try{
			p1 = pb.start();
			this.processCorrectExecuteInfo = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			Process executeProcess = execute.getP1();
			while(true){
				//System.out.println("Moniter sleep 10 sec");
				Thread.sleep(10000);
				try{
					if(executeProcess.exitValue()==0){
						//System.out.println("Process is normal over");
						p1.destroy();
						break;
					}
					else{ // 不正常結束
	//					System.out.println("Process is not normal over");
						p1.destroy();
						break;
					}
				}
				catch(IllegalThreadStateException threadStateException){
	//				System.out.println("Process is not over");
				}
			}
		}
		catch(Exception e){
			System.out.println("moniter error");
		}
	}
}
