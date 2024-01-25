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


@WebServlet("/deleteUrl")
public class DeleteServlet extends HttpServlet {
	static final String query="delete from bookeddata where bookId=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter writer=res.getWriter();
		int id=Integer.parseInt(req.getParameter("id"));
		
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book?user=root&password=root");
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, id);
		int result=ps.executeUpdate();
		if(result==1) {
			writer.println("<h2>Record is deleted successfully</h2>");
		}else {
		writer.println("<h2>Record is not deleted</h2>");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	writer.println("<a href='bookList'>BookList</a>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
