<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.File,java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%			
		File workspace = (File)session.getAttribute("userworkspace");
		
		List<String> executeFiles = new ArrayList<String>();
		File[] user_Workspace_File = workspace.listFiles();
		for(File is_Execute:user_Workspace_File){
			if(is_Execute.canExecute()){
				executeFiles.add(is_Execute.getName());
			}
		}
		Collections.sort(executeFiles);
		pageContext.setAttribute("executefiles",executeFiles);
	%>
    
	<form action="/Cloud_GPU/execute.do" method="post" >
		<table>
			<c:forEach var="executefile" items="${executefiles}">		
	             <tr><td>     
			     <input type="radio" name="executeFile" value="${executefile}">
			      ${executefile}</td></tr>		              
			</c:forEach>
		</table>
		<input type="submit" name="submit" value="Execute">
	</form>
</body>
</html>