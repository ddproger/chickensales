package Servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import ua.goryainov.hibernate.model.Action;
import ua.goryainov.hibernate.model.Administration;
import ua.goryainov.hibernate.model.User;
import ua.goryainov.service.AdministrationService;
import ua.goryainov.service.UserService;

@WebServlet("/home")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SHOW = "/home.jsp";
	private AdministrationService adminService;
	private UserService userService;
	public IndexController(){
		super();
		adminService = new AdministrationService();
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		Object userId = session.getAttribute("userId");
		if(userId!=null)
		{
			Integer id = Integer.parseInt(userId.toString());
			User user = userService.findById(id);
			if(user!=null){
				request.setAttribute("user", user);
				Set<Action> actions = user.getActions();
				request.setAttribute("actionList", actions);
				for(Action value:actions)
				{
					request.setAttribute("actionList", value);
				}
			}
		}
		Object adminId = request.getSession().getAttribute("admin");
		if(adminId!=null){
			Administration admin = adminService.findById(adminId.toString());
			if(admin!=null){
				request.setAttribute("admin", admin);
			}
		}
		
		request.setAttribute("currentPage", "index");
		String forward = "";
			forward = SHOW;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		RequestDispatcher view = request.getRequestDispatcher(SHOW);
		view.forward(request, response);
	}
}