package com.sit.marketplace.broker.accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.sit.marketplace.broker.utils.Utils;

/**
 * Servlet implementation class CheckLogin
 */
@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLogin() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			String username = request.getParameter("username");
			MongoClient client = new MongoClient(Utils.MONGODB_HOST, 27017);
			DB db = client.getDB(Utils.MONGODB_DB);
			DBCollection collection = db.getCollection(Utils.MONGODB_USERS_COLLECTION);
			DBCursor cursor = collection.find(new BasicDBObject("username", username).append("password", request.getParameter("password")));
			if(cursor.size() == 1)
			{
				Cookie loginCookie = new Cookie(Utils.COOKIE_NAME, username);
	            //setting cookie to expiry in 30 mins
	            loginCookie.setMaxAge(30*60);
	            response.addCookie(loginCookie);
				response.sendRedirect("userHome.jsp");
			}
			else
			{
				response.getOutputStream().println("LOGIN NOT SUCCESSFUL. CHECK USERNAME AND PASSWORD.");
			}
		}
		catch(Exception e)
		{
			response.getOutputStream().println(e.getMessage());
		}
	}
}