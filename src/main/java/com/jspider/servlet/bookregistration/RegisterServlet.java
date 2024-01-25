package com.jspider.servlet.bookregistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static String query;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get print writer
		PrintWriter writer=resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		//get the book info
		
		String bookName=req.getParameter("bookName");
		String bookEdition=req.getParameter("bookEdition");
		float bookPrice=Float.parseFloat(req.getParameter("bookPrice"));
		
		
		//load java database connectivity driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/book?user=root&password=root");
			query="insert into bookeddata(bookName,bookEdition,bookPrice) values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			
			ps.setString(1, bookName);
			ps.setString(2,bookEdition);
			ps.setFloat(3, bookPrice);
			int result=ps.executeUpdate();
			if(result==1) {
				writer.println("<h2>Book Registered Successfully..!!</h2>");
			}else {
				writer.println("<h2>Book Not Registered..!!</h2>");
				}
			}catch (Exception e) {
			e.printStackTrace();
		}
	writer.println("<br>");
	writer.println("<a href='home.html'>Home</a>");
	writer.println("<br>");
	writer.println("<a href='bookList'>Book List</a>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
		
	
	}

}
