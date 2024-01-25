package com.jspider.servlet.bookregistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/editScreen")
public class EditServlet extends HttpServlet {
	final static String query= "select bookName, bookEdition,bookPrice from bookeddata where bookId=?";
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter writer=res.getWriter();
	     res.setContentType("text/html");
	     //get the id from the user
	     int id=Integer.parseInt(req.getParameter("id"));
	     try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book?user=root&password=root");
	    	PreparedStatement ps=con.prepareStatement(query);
	        ps.setInt(1,id);
	        ResultSet rs=ps.executeQuery();
	        rs.next();
	        writer.println("<form action='editurl?id="+id+"' method='post'>");
	        writer.println("<table align='center' border='1px black'>");
	        writer.println("<tr>");
	        writer.println("<td>BookName</td>");
	        writer.println("<td><input type='text' name='bookName' value='"+rs.getString(1)+"'></td>");
	        writer.println("</tr>");
	        writer.println("<tr>");
	        writer.println("<td>BookEdition</td>");
	        writer.println("<td><input type='text' name='bookEdition' value='"+rs.getString(2)+"'></td>");
	        writer.println("</tr>");
	        writer.println("<tr>");
	        writer.println("<td>BookPrice</td>");
	        writer.println("<td><input type='text' name='bookPrice' value='"+rs.getFloat(3)+"'></td>");
	        writer.println("</tr>");
	        writer.println("<tr>");
	        writer.println("<td><input type='submit' value='Edit'></td>");
	        writer.println("<td><input type='reset' value='Clear'></td>");
	        writer.println("</tr>");
	        writer.println("</table>");
	        writer.println("</form>");
	       	
			
		} catch (NumberFormatException| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
