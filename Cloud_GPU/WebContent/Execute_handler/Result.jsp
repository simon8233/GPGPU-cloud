<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" autoFlush="false"%>
<%@ page import="java.io.*,java.util.*,com.proc.compile.*, com.proc.base_type.Task.*,com.proc.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result</title>
</head>
<body>		
		<%  		
			String line;
			Execute_Service executeService = (Execute_Service)request.getAttribute("executeservice");
			Task task = (Task)request.getAttribute("task");
			Execute_Thread_Ver execute = executeService.getExecuteProcess(task);
			BufferedReader correctExecuteInfo = execute.getprocessCorrectExecuteInfo();
			BufferedReader moniterExecuteInfo = executeService.getMoniterInfo(task,execute);
			//session.setAttribute("moniterexecuteinfo",moniterExecuteInfo);
			//System.out.println(correctExecuteInfo == null);
			out.println("執行環境與指令細節"+"<br/>");
			out.println(task.getTaskInfo()+"<br/>");
			out.flush();
		%>
		<textarea rows="30" cols="100"><%
			while((line = moniterExecuteInfo.readLine())!= null){	
				//System.out.println(line.trim());//測試code				
				if(line.trim().indexOf("simon")!=-1){
					//System.out.println(line); //測試code
					out.println(line);
					out.flush();
				}
			}
		%>
		</textarea>
		<br/>
		<%
			while((line = correctExecuteInfo.readLine()) != null){												
			  //System.out.println("資料進來了");
			  out.println(line+"<br/>");	
			  out.println("");
			}
		%>		
		
		<%
			correctExecuteInfo.close(); //執行結束 close program 串流
			moniterExecuteInfo.close(); // 執行結束 close 監測串流
			task.getServer().setVirtual_Available(true);
			task.getServer().getParent().setPhysical_Available(true);			
		%>
</body>
</html>