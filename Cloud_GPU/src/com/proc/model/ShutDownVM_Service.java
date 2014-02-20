package com.proc.model;
import com.proc.base_type.Task.*;
public class ShutDownVM_Service extends Thread{
	Execute_Service execute_Service = null;
	Task task = null;
	public ShutDownVM_Service(Execute_Service execute_Service,Task task){
		this.execute_Service = execute_Service;
		this.task = task;
		this.run();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		execute_Service.ShutDowm_VM(task);
	}
	
	
}
