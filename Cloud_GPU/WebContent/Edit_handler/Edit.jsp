<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%	File editFile = (File)request.getAttribute("editfile");
		BufferedReader editReader = new BufferedReader(new FileReader(editFile));
		String line;	
		pageContext.setAttribute("filename",editFile.getName());
	%>
	<form action="/Cloud_GPU/edit.do" method="post">				
		<textarea name=code rows="40" cols="100"><%
			while((line = editReader.readLine())!=null){						
				out.println(line);
			}
		%></textarea><br>
	<input type="text" name="editfilename" value="${pageScope.filename}">
	<input type="submit" name="submit" value="存檔">
	</form>
	

</body>
</html>