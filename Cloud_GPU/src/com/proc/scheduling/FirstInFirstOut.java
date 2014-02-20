package com.proc.scheduling;

import com.proc.base_type.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import com.proc.base_type.Task.*;
import com.proc.compile.*; 
import com.proc.base_type.*;
import com.proc.resource.*;
public class FirstInFirstOut {
	
	
	synchronized public static Virtual_Server IsAvailableMachine(Machine_List serv_list){
		int count_OfVMIsOpening = 0;
		int count_OfVMisNotOpening = 0;
		Virtual_Server serv;
		Physical_Server physical_Server = serv_list.getAvailablePhysicalMachine();
		//此已經得到物理physical_Server;
		//System.out.println("此時為再ISAvailable Machine Function 物理機器是被設定為 " + physical_Server.isPhysical_Available() +"true = 可用 false =不可用");
		
		if((physical_Server != null) && ((serv = physical_Server.getAvailableVM())!= null)){									
										/* 若physical_Server不為null 則 應該是有機器可用 則call getAvailableVM來search 其上VM
										 * 再getAvailableVm的function 中 則已先關閉VM的可用屬性  (重要)
										 */
			System.out.println(serv.getServ_info());
			//
			for(Virtual_Server vs :serv.getParent().getVirtual_Server_List()){				
				if((vs.isOpen()==true)&&(vs.isVirtual_Available()==false)){					
					count_OfVMIsOpening++; //如果有開且又是不可用的時候 此count++;
				}
				if((vs.isOpen()==false&&(vs.isVirtual_Available()==false))){
					count_OfVMisNotOpening++; //如果沒開且又不能用時則 此count++;
				}
			}
			
						
			System.out.println(" count of Available Virtual_Server"+count_OfVMIsOpening);
			System.out.println(" count of Available Virtual_Server But Not Boot"+count_OfVMisNotOpening);
			if(count_OfVMIsOpening == serv.getParent().getMax_Vm_Num()|| (count_OfVMisNotOpening+count_OfVMIsOpening) == serv.getParent().getMax_Vm_Num()){
				serv.getParent().setPhysical_Available(false);//這裡設定Physical Server 為不能用
				System.out.println("FirstIn First Out class setPhysical_Available");
				//此時這Physical Server 已經不敷使用,因count == Max_VM_Num 所以將其設定為不可用
			}
			/*
			 *  若有開機但有工作的再執行的VM為X ,而其餘是尚未開機的VM 為 Y 則 相加  X+Y = 若等於 Physical_Server的VM數量上限,
			 *  則代表目前Physical_Server沒可用機器，可能要進入開機function 或 等待機器被release   
			 *  * 
			 */			
			return serv;
		}	
		System.out.println("FIFO 中 return null");
		return null;
	}
	
	
	
	public static Task Scheduling(Virtual_Server serv,ConcurrentLinkedQueue<Task> queue,Task task){						
		task = queue.poll();
		task.setServer(serv);
		return task;
			//System.out.println("queue pop"+queue.peek().getCode().getName());			
			//System.out.println(task.getExecuteName_andPath());
			
			 // initial Command and Sever_info																																
			/*for(Server a: serv_list.getNum_serv()){
				System.out.println(a.getServer_ip());
				System.out.println(a.getPort());
				System.out.println(a.getID());
				System.out.println(a.isAvailable());				
			}*/ // test server data			
		
		
	}

	
		
}
