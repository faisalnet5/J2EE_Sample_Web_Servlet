package com.java.cyanicviews;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
@WebServlet(
	urlPatterns = { "/SampleServlet" }, 
	initParams = { 
			@WebInitParam(name = "user", value = "Faisal")
	})
*/
public class SampleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	//Constructor
    public SampleServlet() {
        super();
    }

    //HttpServlet#doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() is called");
	}

	//HttpServlet#doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doPost() is called");
		
		//get request parameters for user
		String pass_user = request.getParameter("user");
		System.out.println(pass_user);
		
		//get servlet config init params
		String userID = getServletConfig().getInitParameter("user");
		
		if(userID.equals(pass_user)){
			//Redirect to detail page by passing attributes
			request.setAttribute("user_name",pass_user);
			request.getRequestDispatcher("./userdetails.jsp").forward(request, response);
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/username.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name is wrong.</font>");
			rd.include(request, response);
			
		}		
	}
}
