package com.net.io;
/*
 * 
 * 上傳負責function 並負責將資訊轉跳給File_Upload.jsp
 * 將其上傳資料log回傳給使用者 
 *  
 * 
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.*;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;
/**
 * Servlet implementation class MyUpload
 */
public class MyUpload extends HttpServlet {
	private int buffersize  = 4096;
	private int Sizemax = 1024 * 1024;
	private File tempfile = null;
	
	//private Task task;
	
	
	private static final long serialVersionUID = 1L;
       

    public MyUpload() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		File localFile =null;
		String task_Class = new String();
		HttpSession session = request.getSession();
		//String username = (String)session.getAttribute("username"); // 　用來判別username來判定放置位置
		File userWorkspace = (File)session.getAttribute("userworkspace");
	
		
		if(ServletFileUpload.isMultipartContent(request)){
			request.setCharacterEncoding("UTF-8");
		
			DiskFileItemFactory  factory = new DiskFileItemFactory();
			
			factory.setSizeThreshold(buffersize);
			factory.setRepository(tempfile);
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			
			try {
				List items /*�ɮת���*/ =  upload.parseRequest(request);
				Iterator iter = items.iterator();
				while(iter.hasNext()){ // 處理表單內容中的數值(form 表單)
					FileItem item = (FileItem) iter.next();
					
					if(item.isFormField()){		
						/*
						String name  = item.getFieldName();
						String value = item.getString();
						if(name.equals("Program_class")){
							task_Class = value;
						}
						System.out.print("form field");
						System.out.print(name+" ");
						System.out.print(value);
						*/ // 用來取得表單資訊 但目前只須做到上傳即可// 剩下交給compile_funcation處理
					}
					else{
						if(!item.isFormField()){ // 處理檔案上傳資料.
							System.out.println("client name"+ item.getName());
							
							String fileName =item.getName();
																					
								//localfile = new File("/Cloud/upload/"+username+"/"+fileName);	
								localFile = new File(userWorkspace.getAbsolutePath()+System.getProperty("file.separator")+fileName);
								
							BufferedInputStream in = new BufferedInputStream(item.getInputStream());														
							BufferedOutputStream out =new BufferedOutputStream(new FileOutputStream(localFile));
							Streams.copy(in,out,true);
						}												
					}
					
				}
			} catch (FileUploadException e) {		
				e.printStackTrace();
				System.out.println("upload err");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Exception err");
				e.printStackTrace();
			}
		
		}
		if(UTF8EncodingTest.isUTF8(localFile)==false){
			FileToUTF_8.getFileToUtf_8(localFile);
		}
		//System.out.println(localfile.getPath());
		request.setAttribute("file_receive", localFile); // 送至file_upload 處理 給定使用者上傳成功資訊
		//request.setAttribute("Program_class",task_Class); // Task 分類資訊送給下一個處理網頁		
		request.getRequestDispatcher("/File_handler/File_Upload.jsp").forward(request, response); // 給定上傳成功資訊
		
		//request.getRequestDispatcher("AddJobToQueue").forward(request,response);
		
		
	}

}
