package com.sit.marketplace.broker.accounts;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.sit.marketplace.broker.utils.Utils;

/**
 * Servlet implementation class SignUpComplete
 */
public class SignUpComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpComplete() {
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

		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		try{
			MongoClient client = new MongoClient(Utils.MONGODB_HOST, 27017);
			DB database = client.getDB(Utils.MONGODB_DB);
			DBCollection users = database.getCollection(Utils.MONGODB_USERS_COLLECTION);
			if(users.find(new BasicDBObject("username", username)).size()>0){
				response.sendRedirect("userRegister.jsp?true=0");
				return;
			}
			users.insert(new BasicDBObject("username", username).append("password", password).append("name", name).append("instances", new ArrayList<>()).append("signUpDate", dateFormat.format(new Date())));
			Cookie loginCookie = new Cookie(Utils.COOKIE_NAME, username);
            //setting cookie to expiry in 30 mins
            loginCookie.setMaxAge(30*60);
            response.addCookie(loginCookie);
			response.sendRedirect("userHome.jsp");
		}
		catch(Exception e){
			response.getOutputStream().print(e.getMessage());
		}
	}
}