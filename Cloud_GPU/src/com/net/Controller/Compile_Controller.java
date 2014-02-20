package com.net.Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.proc.compile.Compile;
import com.proc.compile.Execute_Thread_Ver;
/**
 * Servlet implementation class Compile_Controller
 */
public class Compile_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Compile_Controller() {
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
		HttpSession session = request.getSession(); /* 為了取得user workspace */
		String fileExtension = null; //紀錄副檔名
		List<String> fileErrInfo = new ArrayList<String>();
		String compileFile = (String)request.getParameter("compilefile"); /*取得compile file name*/		
		File userWorkspace = (File)session.getAttribute("userworkspace");
		Compile compile = null;
		String compiler = (String)request.getParameter("compiler_class"); // 用來判定compiler類型 
		String outputFile = null;
		
				
		/* String compiler = 用來判定compiler類型
		  * 目前只有
		  * value="C"/> Gcc version 4.4.3
			value="NVCC"/> Cuda compilation tools, release 3.1, V0.2.1221
			替換compiler version			      
		  */	
		if(compiler.equals("C")){
			fileExtension=".c";
			compiler = new String("/usr/bin/gcc");
			outputFile = compileFile.substring(0,compileFile.lastIndexOf("."))+".out"; //對輸出執行檔名作修正
		} 
		
		else if (compiler.equals("NVCC")){
			fileExtension=".cu";
			compiler = new String ("/opt/cuda/bin/nvcc");
			outputFile = compileFile.substring(0,compileFile.lastIndexOf("."))+".out"; //對輸出執行檔名作修正
		}
		
		if(compileFile.endsWith(fileExtension) == false){
			fileErrInfo.add("無副檔名或是副檔名錯誤");			
		}
		/*
		 * 針對沒副檔名的檔案對輸出檔做重新命名
		 */
			
		if(fileErrInfo.isEmpty()){
			/*
			 * 編譯錯誤問題
			 */
			compile = new Compile();
			String[] command_Compile = {"ssh","140.128.18.169",compiler,"-O2","-o",userWorkspace+"/"+outputFile,userWorkspace+"/"+compileFile};
			System.out.println("");
			compile.run_Compileprocess(command_Compile);
			if(compile.getCompileErrInformation().isEmpty()){ //無錯誤資訊則轉向至成功compile網頁
				request.setAttribute("correctinfo", compile.getCompileInformation());
				request.getRequestDispatcher("Compile_handler/Compile.jsp").forward(request, response);
			}
			else{//錯誤則輸出compile error
				request.setAttribute("errorinfo", compile.getCompileErrInformation());
				request.getRequestDispatcher("Compile_handler/Compile.jsp").forward(request, response);
			}
		}
		else{
			/*
			 * 檔案錯誤問題
			 */
			System.out.println("檔案錯誤");
			request.setAttribute("fileerrinfo", fileErrInfo);
			request.getRequestDispatcher("Compile_handler/Compile.jsp").forward(request, response);
		}
		
		
		
		
	}

}
