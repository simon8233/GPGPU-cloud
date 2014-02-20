package com.net.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
/**
 * Servlet implementation class Login_Controller
 */
public class Login_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_Controller() {
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
		
		String userName = request.getParameter("username");
		userName = userName.trim();
		if(userName!=null&&userName.equals("")==false){ // 登入資訊不為空 or 或者未輸入
					HttpSession session = request.getSession();
					session.setAttribute("username", userName);
					// session.setAttribute("",password);
					session.setAttribute("login", userName); // 存放已經登入後的屬性用來判別是否使用者已經登入過
					File userWorkspace = new File("/Cloud/upload/"+userName);
					if(userWorkspace.exists()==false){
						System.out.println("無資料夾");
						userWorkspace.mkdir(); // 建立
					}										
					session.setAttribute("userworkspace", userWorkspace);
					request.getRequestDispatcher("/portal_left.jsp").forward(request, response);
		}
		else{
			
		}
		
		
	}

}
