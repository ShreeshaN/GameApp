package com.rzt.game;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StartServlet
 */
public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Map<String,Integer> map = new HashMap<String,Integer>();
		String email = request.getParameter("emailId");
		PrintWriter out = response.getWriter();
		int numOfTries = 0;
		int genVal = (int)(Math.random()*10);
		System.out.println(genVal);
		if(email == null || email.trim().equals(""))
		{
			out.write("<b>Enter a email instead of entering nothing <a href='homePage.html'>Click to go to homepage</a><b>");
		}
		else
		{
			HttpSession s = request.getSession(true);
			s.setAttribute("user", email);
			s.setAttribute("generated Value", genVal);
			s.setAttribute("numOfTries", numOfTries);
			RequestDispatcher rd = request.getRequestDispatcher("/guessPage.html");
		    rd.forward(request, response);
		}
	    
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
