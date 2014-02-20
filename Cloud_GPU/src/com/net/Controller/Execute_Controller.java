package com.net.Controller;

import java.io.*;

import com.proc.model.*;
import com.net.io.*;
import com.proc.base_type.Virtual_Server;
import com.proc.base_type.Task.*;
import com.proc.compile.Execute_Thread_Ver;
import com.proc.*;
import com.proc.resource.*;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.servlet.ServletException;
import javax.servlet.http.*;
/**
 * Servlet implementation class Execute_Controller
 */
public class Execute_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Execute_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String executeFile_Str_Name = request.getParameter("executeFile"); //得到執行檔案名稱
		HttpSession session = request.getSession();
		File userWorkspace = (File)session.getAttribute("userworkspace"); //得到執行檔案環境位置
		
		File executeFile = new File(userWorkspace.getAbsoluteFile()+System.getProperty("file.separator")+executeFile_Str_Name); //執行檔案絕對路徑 
			
		ConcurrentLinkedQueue<Task> queue = (ConcurrentLinkedQueue<Task>)getServletContext().getAttribute("queue");
		Machine_List serv_list = (Machine_List)getServletContext().getAttribute("list");
		ConcurrentLinkedQueue<Task> global_TaskQueueLog = (ConcurrentLinkedQueue<Task>)getServletContext().getAttribute("global_taskqueuelog");
		Virtual_Server serv = null;
		
		Task task = new Task(); //確認須執行檔案後 建立Task
		task.setExecuteFile(executeFile); //task的執行程式碼
		queue.add(task); //將工作放入queue中做排隊		
		global_TaskQueueLog.add(task);
		task.setTask_ID(global_TaskQueueLog.size()); //設定此次Task 的ID 已global_TaskQueue_Log的size為紀錄
			
		Execute_Service executeService = new Execute_Service(queue, serv_list, global_TaskQueueLog); //建立executeService;
	
		if(executeService.Task_AddWorkingQueue(task)){
			request.setAttribute("executeservice", executeService);
			request.setAttribute("task", task);
			System.out.println("forward 前 physicla_Server status");
			task.getServer().getParent().printAllVmInfo();
			
			request.getRequestDispatcher("Execute_handler/Result.jsp").forward(request, response);			
		}
		else{
			request.setAttribute("executeservice", executeService);
			request.setAttribute("task", task);
			request.getRequestDispatcher("Execute_handler/Wait.jsp").forward(request, response);
		}

	}

}
