<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>無標題文件</title>
</head>

<body>
	
	${sessionScope.username} 
	<p>功能列</p>
	<p><a href="Edit_handler/SelectFiletoEdit.jsp" target="mainFrame">Edit_View</a></p>
	<p><a href="File_handler/File_Upload.jsp" target="mainFrame">Upload</a></p>
	<p><a href="Compile_handler/Compile.jsp" target="mainFrame">Compile</a></p>
	<p><a href="Execute_handler/Execute.jsp" target="mainFrame">Execute</a></p>
	<p>File Management</p>
	<p>Log</p>
	<p>Form</p>
	<p></p>
	
</body>
</html>
