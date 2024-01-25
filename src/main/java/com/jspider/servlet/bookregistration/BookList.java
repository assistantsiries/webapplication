package com.jspider.servlet.bookregistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookList")
public class BookList extends HttpServlet {
	private static String query;
	private static Connection con;
	private static PreparedStatement ps; 
	private static ResultSet set;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer=resp.getWriter();
		resp.setContentType("text/html");
		//value from application
		
		//java database connectivity logic
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book?user=root&password=root");
			query="select * from bookeddata";
			ps=con.prepareStatement(query);
			set=ps.executeQuery();
			writer.println("<table align='center' border='1px'>");
			writer.println("<tr>");	
			writer.println("<th>Book Id</th>");
			writer.println("<th>Book Name</th>");
			writer.println("<th>Book Edition</th>");
			writer.println("<th>Book Price</th>");
			writer.println("<th>Edit</th>");
			writer.println("<th>Delete</th>");
		    writer.println("</tr>");
			while(set.next()) {
				writer.println("<tr>");
				writer.println("<td>"+set.getInt(1)+"</td>");
				writer.println("<td>"+set.getString(2)+"</td>");
				writer.println("<td>"+set.getString(3)+"</td>");
				writer.println("<td>"+set.getFloat(4)+"</td>");
				writer.println("<td><a href='editScreen?id="+set.getInt("bookId")+" '>Edit</a></td>");
				writer.println("<td><a href='deleteUrl?id="+set.getInt("bookId")+"'>Delete</a></td>");
				writer.println("</tr>");
				
			}
			writer.println("</table>");	
		} catch (Exception e) {
			e.printStackTrace();
		}
	     writer.println("<br>");
		writer.println("<a href='home.html' style='align:center'>Home</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
