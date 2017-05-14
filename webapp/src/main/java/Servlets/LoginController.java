package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.goryainov.hibernate.model.User;
import ua.goryainov.service.UserService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	
	public LoginController(){
		super();
		userService = new UserService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String pass = request.getParameter("password");
		if(login!=null&&pass!=null)
		{
			User user = userService.verifyUser(login.toString(), pass.toString());
			if(user!=null){
				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getUserId());
			}
		}
		response.sendRedirect("home");		
	}
}

