<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${requestScope.file_receive!=null}">
		
		<h2>上傳${requestScope.file_receive.name}檔案成功！</h2>
		
	</c:if>
	<h1>${sessionScope.username}您好 這是檔案上傳頁面</h1> 
	上傳程式碼務必加入副檔名<br/>
	若為C語言程式碼 .c <br/>
	若為NVCC語言程式碼副檔名為 .cu <br/> 
	若無正確命名 則會無法正常編譯	 <br/>
<b>File Upload</b>
  <br>
<form name="myform" action="/Cloud_GPU/upload.do"
 method="post" enctype="multipart/form-data"> 
選擇你的檔案:<br/> 
	<input type="file" name="myfile"><br/>		
	<input type="submit" name="Submit" value="上傳"> 
  </form>
</body>
</html>