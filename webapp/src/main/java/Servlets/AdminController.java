package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.goryainov.hibernate.model.Administration;
import ua.goryainov.service.AdministrationService;


@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SHOW = "/admin.jsp";
	private static String ERROR = "/error.jsp";
	private AdministrationService administrationService;
	
	public AdminController(){
		super();
		administrationService = new AdministrationService();
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
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Administration administration = new Administration();
		try{
		if(login!=null&&password!=null)
		administration = administrationService.findById(login);
		HttpSession session = request.getSession();
		session.setAttribute("admin", administration.getLogin());		
		}catch(Exception e){
		 RequestDispatcher view = request.getRequestDispatcher(ERROR);
		 view.forward(request, response);		 
		}
	
	 response.sendRedirect("home");
	}
	
}
