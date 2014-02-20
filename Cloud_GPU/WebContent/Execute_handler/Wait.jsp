<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,com.proc.compile.*, com.proc.base_type.Task.*,com.proc.model.*,com.proc.base_type.Task.*"%>
<%@ page import="java.util.concurrent.*,com.proc.resource.*,com.proc.base_type.*,com.FutureAndCallable.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<%
		boolean Tag = false;
		Execute_Service executeService = (Execute_Service)request.getAttribute("executeservice");
	//	ConcurrentLinkedQueue<Task> queue = (ConcurrentLinkedQueue<Task>)getServletContext().getAttribute("queue");
		Machine_List serv_list = (Machine_List)getServletContext().getAttribute("list");
	//  ConcurrentLinkedQueue<Task> global_TaskQueueLog = (ConcurrentLinkedQueue<Task>)getServletContext().getAttribute("global_taskqueuelog");
		Virtual_Server serv;
		Physical_Server physical_Server;
		Task task = (Task)request.getAttribute("task");
		int wait_sec = 10;
	%>
	<%
		while(true){
			
			physical_Server = serv_list.getAvailablePhysical_MachineHaveVirtual_SeverNotBeOpen();
			
			if ((Tag==false)&&(physical_Server != null)&&(serv = physical_Server.SearchNotBootVM())!= null){
				/*
				如果Tag = false 代表這次可執行開機流程 , 若等於true 則此task已呼叫過開機function ,
				physical_Server == null 則代表沒可用的物理Server 則進入wait
				
				
				*/
				
				
				System.out.println("OpenVM Tag in ");
				new OpenVM_Task(serv);
				Tag = true;
		  	}
	%>
	<% 		try{
				System.out.println("TaskID = "+task.getTask_ID()+"=====進去睡覺");
				Thread.sleep(10000);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}		
	%>
		目前所有伺服器忙碌中
		已經等待了<%=wait_sec%>秒<br/>
		<%out.flush(); %>
	<% 	
			wait_sec+=10;
			if(executeService.Task_AddWorkingQueue(task)){
				request.setAttribute("executeservice", executeService);
				request.setAttribute("task", task);
				break; // 如果成功取得VM 則 break  此loop 轉跳至Result.jsp
				
			}
		} //while end
	%>
	
	<jsp:include page="Result.jsp"/> .s
</body>
</html>