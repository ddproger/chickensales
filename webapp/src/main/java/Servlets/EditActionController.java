package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.goryainov.hibernate.model.Action;
import ua.goryainov.hibernate.model.ActionType;
import ua.goryainov.hibernate.model.User;
import ua.goryainov.service.ActionService;
import ua.goryainov.service.ActionTypeService;
import ua.goryainov.service.Sender;
import ua.goryainov.service.UserService;

@WebServlet("/action")
public class EditActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SHOW = "/action.jsp";
	private UserService userService;
	private ActionService actionService;
	private ActionTypeService actionTypeService;
	private static Sender tlsSender = new Sender("fordipplom@gmail.com", "Lfcfan1977");
	public EditActionController(){
		super();
		userService = new UserService();
		actionService = new ActionService();
		actionTypeService = new ActionTypeService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String forward = SHOW;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String content = request.getParameter("content");
		List<User> users = userService.findAll();
		try{
		if(content!=null)
		{
			for(User user:users){
				if(request.getParameter(String.valueOf(user.getUserId()))!=null){
					//if(request.getParameter("type").equals("mail")){
					//tlsSender.send("Акции от интернет магазина", content, "support@chickensale.com", user.getMail());
					//} 
					String id = request.getParameter("type");
					ActionType actionType = actionTypeService.findById(Integer.parseInt(id));
					actionService.persist(new Action(content,user,actionType));					
				}
			}
			response.sendRedirect("customers");			
		}		
		}catch(Exception e){
			response.sendError(503);		 
		}	
	 
	}
	
}
