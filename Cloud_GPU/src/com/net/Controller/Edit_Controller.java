package com.net.Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Edit_Controller
 */
public class Edit_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit_Controller() {
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
		HttpSession session = request.getSession();
		String code , editfilename;
		String selectFile_String = (String)request.getParameter("selectfile"); /*取得compile file name*/		
		File userWorkspace = (File)session.getAttribute("userworkspace");
		
		if((code = request.getParameter("code")) != null){
			editfilename = request.getParameter("editfilename");
			System.out.println(code);
			//BufferedWriter writer = new BufferedWriter(new FileWriter(new File(userWorkspace.getPath()+"/"+editfilename)));
			//while(write){
				
				
			//}
		}
		
		
				
		File selectFile = new File(userWorkspace.getPath()+"/"+selectFile_String);
		
		request.setAttribute("editfile", selectFile);
		request.getRequestDispatcher("Edit_handler/Edit.jsp").forward(request, response);
		
		
		
		
		//System.out.println(selectFile.getPath());
		
		//File userworkspace
		//File selectFile = new File(request.getSession().getAttribute("userworkspace")+request.getParameter("editfile"));
		
	}

}
