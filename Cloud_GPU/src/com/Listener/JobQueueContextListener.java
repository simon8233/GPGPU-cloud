package com.Listener;
import java.util.concurrent.ConcurrentLinkedQueue;
import com.proc.base_type.Virtual_Server;
import com.proc.base_type.Physical_Server;
import com.proc.base_type.Task.*;
import com.proc.resource.Machine_List;

//import com.proc.queue.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class JobQueueContextListener implements ServletContextListener{
	ConcurrentLinkedQueue<Task> task_queue = new ConcurrentLinkedQueue<Task>();	
	Machine_List serv_list  = new Machine_List(); //Physicl_Server[]
	ConcurrentLinkedQueue<Task> global_TaskQueueLog = new ConcurrentLinkedQueue<Task>();	

	@Override
	public void contextInitialized(ServletContextEvent event) {
			ServletContext sc = event.getServletContext();
			sc.setAttribute("queue", task_queue);
			sc.setAttribute("list", serv_list);
			sc.setAttribute("global_taskqueuelog", global_TaskQueueLog); // 可以用得參數
			
	}
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		Machine_List serv_list = (Machine_List)sc.getAttribute("list");
		//serv_list.
		Virtual_Server vs;
		Physical_Server[] phy_Servers = serv_list.getNum_serv();
		for(Physical_Server phy_serv : phy_Servers){
			while( (vs = phy_serv.getAvailableVM()) != null){					
				phy_serv.ShutdownVmonPhysicalServ(vs.getParent().getServer_ip(),vs);
			}
		}								
	}
	
}
