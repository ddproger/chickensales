package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.If;
import ua.goryainov.hibernate.model.ActionType;
import ua.goryainov.hibernate.model.Administration;
import ua.goryainov.hibernate.model.User;
import ua.goryainov.service.*;

@WebServlet("/customers")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SHOW_ALL = "/users.jsp";
	private static String SHOW = "/user.jsp";
	private AdministrationService adminService;
	private UserService userService;
	private ActionTypeService  actionTypeService;

	public UserController(){
		super();
		adminService = new AdministrationService();
		userService = new UserService();
		actionTypeService = new ActionTypeService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");		
		Object adminId = request.getSession().getAttribute("admin");
		if(adminId!=null){
			Administration admin = adminService.findById(adminId.toString());
			if(admin!=null){
				request.setAttribute("admin", admin);
			}
		}
		String forward = "";

		String customerId = request.getParameter("id");
		if(customerId!=null){
			ActionService actionService = new ActionService();
			OrderService orderService = new OrderService();
			StatusService statusService = new StatusService();
			User user = userService.findById(Integer.parseInt(customerId));
			request.setAttribute("user", user);
			request.setAttribute("actions", actionService.findByUser(user.getUserId()));
			request.setAttribute("orders", orderService.findByUser(user.getUserId()));
			request.setAttribute("statuses", statusService.findAll());
			forward = SHOW;
		}else{
			String group = request.getParameter("group");
			List<User> users = new LinkedList<>();


			if(group != null && !group.equals("")){

				if(group.equals("individium")){
					request.setAttribute("group","individium");
					users = userService.findIndividum();
				}else if(group.equals("legal")){
					request.setAttribute("group","legal");
					users = userService.findLegal();
				}
			}else {
				users = userService.findAll();
			}

		request.setAttribute("users", users);
		List<ActionType> actionTypes = new LinkedList<>();
		actionTypes = actionTypeService.findAll();
		request.setAttribute("actionTypes", actionTypes);
		forward = SHOW_ALL;
		}
		request.setAttribute("currentPage", "customers");
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
	}
}