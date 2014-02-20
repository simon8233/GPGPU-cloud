package com.net.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA_2_3.portable.OutputStream;

import com.proc.base_type.Task.Task;
import com.proc.compile.Moniter;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class Moniter_GraphicView
 */
public class Moniter_GraphicView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Moniter_GraphicView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException{
		HttpSession session = request.getSession();
		BufferedReader  moniterExecuteInfo = (BufferedReader)session.getAttribute("moniterexecuteinfo");
		
		ServletOutputStream out = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(getMoniterExecuteInfoGraphic(moniterExecuteInfo));
		out.close();
		
	}
	private BufferedImage getMoniterExecuteInfoGraphic(BufferedReader moniter) throws IOException{
		BufferedImage bufferedImage = new BufferedImage(600,600,BufferedImage.TYPE_INT_RGB);
		Graphics g =  bufferedImage.getGraphics();
		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif",Font.BOLD,10));
		String line;
		while((line = moniter.readLine()) != null){
		g.drawString(line, 10, 15);
		}
		return bufferedImage;
	}
}
