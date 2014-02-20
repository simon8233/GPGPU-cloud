<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SelectFiletoEdit</title>
</head>
<body>
	<hr>	
	<%			
		File workspace = (File)session.getAttribute("userworkspace");		
		List<String> workspace_File = new ArrayList<String>(); 
		for(File file:workspace.listFiles()){
			if(file.canExecute()==false){
				if(file.getName().endsWith(".cu")||file.getName().endsWith(".c")||file.getName().endsWith(".txt")){
					workspace_File.add(file.getName());
				}
			}				
		}		
		Collections.sort(workspace_File);
		String[] user_Workspace_File = (String[])workspace_File.toArray(new String[workspace_File.size()]);
		//Arrays.sort(user_Workspace_File);
		pageContext.setAttribute("user_workspace_File",user_Workspace_File);	
	%>
	<h1>選擇檔案做編輯,目前只能選擇<br>
	.c 與 .cu 與 .txt
	副檔名文字檔
	</h1>
					   
	<form action="/Cloud_GPU/edit.do" method="post" >
		<table>
			<c:forEach var="file" items="${user_workspace_File}">		
	             <tr><td>     
			     <input type="radio" name="selectfile" value="${file}">
			      ${file}</td></tr>		              
			</c:forEach>
			
		</table>
	<hr>
			<input type="radio" name="edit_view" value="edit"/> 
			Edit  <br/>
			<input type="radio" name="edit_view" value="view"/>
			View  <br/>
			  		<input type="submit" name="submit",value="發送">
	</form> 
	
</body>
</html>