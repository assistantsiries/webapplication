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


@WebServlet("/editurl")
public class EditedServlet extends HttpServlet {
	final static String query="update bookeddata set bookName=?,bookEdition=?,bookPrice=? where bookId=?";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter writer=res.getWriter();
		res.setContentType("text/html");
		int bookId=Integer.parseInt(req.getParameter("id"));
		String bookName=req.getParameter("bookName");
		String bookEdition=req.getParameter("bookEdition");
		float bookPrice=Float.parseFloat(req.getParameter("bookPrice"));
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book?user=root&password=root");
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			ps.setInt(4, bookId);
			int result=ps.executeUpdate();
			if(result==1) {
				writer.println("<h2>Record is edited successfully</h2>");
			}else {
				writer.println("<h2>Record is not edited successfully</h2>");
			}
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		writer.println("<a href='home.html'>Home</a>");
		writer.println("<br>");
		writer.println("<a href='bookList'>BookList</a>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
