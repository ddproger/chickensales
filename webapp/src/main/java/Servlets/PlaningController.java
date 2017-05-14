package Servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.goryainov.hibernate.model.Action;
import ua.goryainov.hibernate.model.ActionType;
import ua.goryainov.hibernate.model.Administration;
import ua.goryainov.hibernate.model.Commission;
import ua.goryainov.hibernate.model.User;
import ua.goryainov.service.ActionService;
import ua.goryainov.service.ActionTypeService;
import ua.goryainov.service.AdministrationService;
import ua.goryainov.service.UserService;

@WebServlet("/planing")
public class PlaningController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SHOW_ALL = "/planing.jsp";
	private static String SHOW = "/planing.jsp";
	private AdministrationService adminService;
	private UserService userService;
	private ActionTypeService  actionTypeService;
	private ActionService  actionService;

	public PlaningController(){
		super();
		adminService = new AdministrationService();
		userService = new UserService();
		actionTypeService = new ActionTypeService();
		actionService = new ActionService();
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
		List<Action> actions = new ArrayList<Action>();	
		actions = actionService.findAll();
		request.setAttribute("actions", actions);
		List<ActionType> actionTypes = new ArrayList<ActionType>();

		actionTypes = actionTypeService.findAll();
		request.setAttribute("actionTypes", actionTypes);
		forward = SHOW_ALL;
		
		request.setAttribute("currentPage", "planing");
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		List<Action> actions =  actionService.findAll();
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd"); 
		Date date;
		for(Action action:actions){			
			if(request.getParameter("date["+action.getActionId()+"]")!=null)
				{
					action.setEndDate(request.getParameter("date["+action.getActionId()+"]"));
					if(request.getParameter("complite["+action.getActionId()+"]")!=null)
						action.setComplete(true);
					else action.setComplete(false);
					action.setName(request.getParameter("context["+action.getActionId()+"]"));
					action.setActionType(actionTypeService.findById(Integer.parseInt(request.getParameter("actionType["+action.getActionId()+"]"))));
					actionService.update(action);

				
				}
		}
		response.sendRedirect("planing");	
		
	}
}