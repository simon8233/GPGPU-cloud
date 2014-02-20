<%@page import="java.util.Arrays"%>
<%@page import="java.io.File"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Compile</title>
<style type="text/css">
<!--
.style4 {
	font-size: 24px;
	color: #FF0000;
}
-->
</style>
</head>
<body>
	
	<% // (ne,！=) %>
	<c:if test="${requestScope.fileerrinfo ne null}">
		 <span class="style4">副檔名不正確或是選擇錯誤Compiler</span>
	</c:if>
	<c:if test="${requestScope.correctinfo ne null}">
		<span class="style4">編譯成功</span>          
		<c:forEach var="correctmessage" items="${requestScope.correctinfo}">
		${correctmessage}<br>	
		</c:forEach>	
	</c:if>
	<c:if test="${requestScope.errorinfo ne null}">   
	    <span class="style4">編譯錯誤</span> <br>
		<c:forEach var="errormessage" items="${requestScope.errorinfo}">
		${errormessage}<br/>
		</c:forEach>
	</c:if>
	<br/>	
	<hr>	
	<%			
		File workspace = (File)session.getAttribute("userworkspace");		
		List<String> workspace_File = new ArrayList<String>(); 
		for(File file:workspace.listFiles()){
			if(file.canExecute()==false){				
				workspace_File.add(file.getName());
			}
			
		}		
		Collections.sort(workspace_File);
		String[] user_Workspace_File = (String[])workspace_File.toArray(new String[workspace_File.size()]);
		//Arrays.sort(user_Workspace_File);
		pageContext.setAttribute("user_workspace_File",user_Workspace_File);	
	%>
    
		<form action="/Cloud_GPU/compile.do" method="post" >
		<table>
			<c:forEach var="file" items="${user_workspace_File}">		
	             <tr><td>     
			     <input type="radio" name="compilefile" value="${file}">
			      ${file}</td></tr>		              
			</c:forEach>
			
		</table>
			<hr>
			<p>
			Compiler Version<br/>
			  <input type="radio" name="compiler_class" value="C"/> 
			  Gcc version 4.4.3  <br/>
			  <input type="radio" name="compiler_class" value="NVCC"/> 
			  Cuda compilation tools, release 3.1, V0.2.1221 <br/>       		 
			  <input name="submit" type="submit" value="compile">
            </p>
</form>
	
</body>
</html>

