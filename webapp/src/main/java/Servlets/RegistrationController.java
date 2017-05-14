package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.goryainov.hibernate.model.User;
import ua.goryainov.service.UserService;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SHOW = "/registration.jsp";
	private static String ERROR = "/error.jsp";
	private UserService userService;
	
	public RegistrationController(){
		super();
		userService = new UserService();
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
		String name = request.getParameter("name");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String adress = request.getParameter("adress");
		String edrpou = request.getParameter("edrpou");
		String mail = request.getParameter("mail");
		User user = new User();
		try{
		if(login!=null)user.setLogin(login);
		if(password!=null)user.setPassword(password);
		if(name!=null)user.setName(name);
		if(tel1!=null)user.setTel1(tel1);
		if(tel2!=null)user.setTel2(tel2);
		if(adress!=null)user.setDeliveryAdress(adress);
		if(edrpou!=null)user.setEDRPOU(edrpou);
		if(mail!=null)user.setMail(mail);
		userService.persist(user);		
		}catch(Exception e){
		 RequestDispatcher view = request.getRequestDispatcher(ERROR);
		 view.forward(request, response);		 
		}
	 //RequestDispatcher view = request.getRequestDispatcher(HOME);
	 //request.setAttribute("billOfEntry", billOfEntryService.findAll());
	 //view.forward(request, response);	
	
	 response.sendRedirect("home");
	}
	
}
