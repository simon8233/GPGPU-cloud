package com.proc.model;
import java.util.*;
import java.io.*;
import com.FutureAndCallable.*;
import com.proc.compile.*;

import java.util.concurrent.*;
import com.proc.resource.*;
import com.proc.scheduling.FirstInFirstOut;
import com.proc.base_type.Virtual_Server;
import com.proc.base_type.Task.*;
public class Execute_Service {
	ConcurrentLinkedQueue<Task> queue;
	Machine_List serv_list;
	ConcurrentLinkedQueue<Task> global_TaskQueueLog;
	Integer Save_ShutDownFlag_Num = 0 ;
	Virtual_Server serv = null;
	boolean Tag = false;
	public Execute_Service(ConcurrentLinkedQueue<Task> queue , Machine_List serv_list , ConcurrentLinkedQueue<Task> global_TaskQueueLog) {
		this.global_TaskQueueLog = global_TaskQueueLog;
		this.queue = queue;
		this.serv_list = serv_list;
	}
	public void ShutDowm_VM(Task task){
		while(queue.isEmpty() && (Save_ShutDownFlag_Num = serv.getParent().ServerNum_MoreEqualPreOpenVm(Integer.toString(task.getTask_ID()))) != null){
			try{
				System.out.println("ShutDown loop"+serv.getParent().getServ_info());
				Thread.sleep(30000);//設置關閉需要多少時間 // 可變動
				
				if(queue.isEmpty()&&(serv.getParent().checkFlagisNotbeChange(Integer.toString(task.getTask_ID()),Save_ShutDownFlag_Num))){
					task.getServer().getParent().SearchShutdownVM(Integer.toString(task.getTask_ID()));
					break;
				}
				break;
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	public Execute_Thread_Ver getExecuteProcess(Task task){
		System.out.println("Execute_Thread_ver 中 task 資料"+task.getExecutecmd());
		Execute_Thread_Ver execute = new Execute_Thread_Ver(task.getExecutecmd(),this,task);		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//BufferedReader br = execute.getprocessCorrectExecuteInfo();
		//System.out.println("BufferedReader Data"+br);
		//System.out.println(task.getMoniterCmd());

		
		return execute;
	}
	public BufferedReader getMoniterInfo(Task task,Execute_Thread_Ver execute){		
		Moniter moniter = new Moniter(task.getMoniterCmd(),execute);		
		return moniter.getProcessCorrectExecuteInfo();
	}
	public boolean Task_AddWorkingQueue(Task task){
		
		
		System.out.println(queue); //測試queue中工作內容
			
			if((queue.peek().equals(task))&&(serv = FirstInFirstOut.IsAvailableMachine(serv_list)) != null){
				task = FirstInFirstOut.Scheduling(serv,queue,task); // task 得到server 可以準備執行
				/* print  data*/
				System.out.println("**********第"+task.getTask_ID()+"份工作"+"Print Virtual Server info ");				
				serv.getParent().printAllVmInfo(); // 全部的
				System.out.println("這次工作選用的Virtual_Server與Task ID");
				System.out.println("TaskID"+task.getTask_ID()+" : "+serv.getServ_info());
				System.out.println(task.getTaskInfo());
				return true;
							
			}// end if
			else{ //得不到Virtual Server 則 回傳新增task 失敗
				return false;
			}
											
	}
	
}
