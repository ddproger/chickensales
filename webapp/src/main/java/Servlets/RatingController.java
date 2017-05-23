package Servlets;

import ua.goryainov.hibernate.model.ActionType;
import ua.goryainov.hibernate.model.Administration;
import ua.goryainov.hibernate.model.TopUser;
import ua.goryainov.hibernate.model.User;
import ua.goryainov.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/rating")
public class RatingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SHOW_ALL = "/rating.jsp";
	private AdministrationService adminService;
	private UserService userService;
	//private ActionTypeService  actionTypeService;

	public RatingController(){
		super();
		adminService = new AdministrationService();
		userService = new UserService();
		//actionTypeService = new ActionTypeService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String action = request.getParameter("action");
		Object adminId = request.getSession().getAttribute("admin");
		if(adminId!=null){
			Administration admin = adminService.findById(adminId.toString());
			if(admin!=null){
				request.setAttribute("admin", admin);
			}
		}
		request.setAttribute("from",from);
		request.setAttribute("to",to);

		String forward = "";

		List<User> users;
		if(from!=null&&to!=null){
			users = userService.findTop(from.toString(),to.toString());
		}else{
			users = userService.findTop();
		}
		if(action!=null&&action.equals("import")) {
			ExcelImportService.saveToFile(users,"webapp/src/main/webapp/tmp/newBook.xls");

		}
		request.setAttribute("users", users);
		forward = SHOW_ALL;
		request.setAttribute("currentPage", "rating");
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

	}
}