package com.qiheng.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class uploadServlet extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			String path = request.getRealPath("/fileupload");
			
			factory.setRepository(new File(path));
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			try {
				List<FileItem> list=fileUpload.parseRequest(request);
				for(FileItem item:list){
					
					if(item.isFormField()){
						System.out.println(item.getFieldName());
						System.out.println(item.getString());
						request.setAttribute(item.getFieldName(), item.getString());
					}
					else{
						System.out.println(item.getFieldName());
						System.out.println(item.getName());
						request.setAttribute(item.getFieldName(), item.getName());
						item.write(new File(path,item.getName()));
					}
				}
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("result.jsp").forward(request, response);
			
	}

}
