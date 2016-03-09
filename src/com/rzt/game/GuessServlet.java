package com.rzt.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Guess
 */
public class GuessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void init() throws ServletException {
			ServletContext sc = getServletContext();
		Map<String,Integer> points = new HashMap<String,Integer>();
		sc.setAttribute("PointsMap",points);
		
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String g = request.getParameter("number");
		PrintWriter out = response.getWriter();
		//RequestDispatcher rd = request.getRequestDispatcher("/hurray.html");
		
		try{
			int guess = Integer.parseInt(g);
			//System.out.println(guess);
			HttpSession session = request.getSession(false);
			if(session == null)
			{
				out.println("<b>Session timed out !! <a href='homePage.html'>Click here to play the game again</a></b>");
				return;
			}
			else
			{
				String email = (String)session.getAttribute("user");
				int genVal = (Integer)session.getAttribute("generated Value");
				int numTries = (Integer) session.getAttribute("numOfTries");
				
				
				if(guess == genVal)
				{
					out.println("<h1>HURRAY !! You get 5 points<h1>");
					out.println("<h3><a href='homePage.html'>Click to go to homepage</a></h3>");
					
					ServletContext sc = getServletContext();
					Map<String,Integer> map = (Map<String,Integer>) sc.getAttribute("PointsMap");
					if(map.get(email) != null)
					{
						int pts = (Integer) map.get(email);
						pts = pts + 5;
						map.put(email, pts);
					}
					else
						map.put(email, 5);
					
					session.removeAttribute("email");
					session.removeAttribute("generated Value");
					session.removeAttribute("numOfTries");
					session.invalidate();
					
				}
				else
				{
					numTries++;
					
					session.setAttribute("numOfTries", numTries);
					out.println("<b>No of tries left : "+(3-numTries)+"<b>");
					if(guess > genVal)
					{
						out.println("<h3>HINT : The guessed number is greater than the generated number</h3>");
					}
					else
						out.println("<h3>HINT : The guessed number is lesser than the generated number</h3>");
					if((3-numTries) !=0)
						out.println("<h2><a href = 'guessPage.html'>Press here to go back and try again</a><h2>");
					else
					{
						session.removeAttribute("email");
						session.removeAttribute("generated Value");
						session.removeAttribute("numOfTries");
						session.invalidate();
						out.println("<h2>You Failed to guess<a href = 'homePage.html'>Give it another chance !!</a></h2>");
						return;
					
					}
						
				}
			}
			
		}
		catch(NumberFormatException e)
		{
			out.println("<b>Enter an integer boss</b>");
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
