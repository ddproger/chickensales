package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.goryainov.hibernate.model.Administration;
import ua.goryainov.hibernate.model.Commission;
import ua.goryainov.hibernate.model.Status;
import ua.goryainov.service.AdministrationService;
import ua.goryainov.service.OrderService;
import ua.goryainov.service.StatusService;

@WebServlet("/orders")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SHOW = "/order.jsp";
	private OrderService orderService;
	private AdministrationService adminService;
	private StatusService statusService;
	public OrderController(){
		super();
		orderService = new OrderService();
		adminService = new AdministrationService();
		statusService = new StatusService();
		
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
		List<Commission> orders =  orderService.findAll();
		request.setAttribute("orders", orders);		
		List<Status> statuses =  statusService.findAll();
		request.setAttribute("statuses", statuses);		
		
		request.setAttribute("currentPage", "orders");
		String forward = "";
			forward = SHOW;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		List<Commission> orders =  orderService.findAll();
		String statusId="";
		for(Commission order:orders){
			statusId=request.getParameter("status["+order.getOrderId()+"]");
			if(statusId!=null)
				{
					order.setStatus(statusService.findById(Integer.parseInt(statusId)));
					orderService.update(order);
				}
		}
		response.sendRedirect("orders");		
	}	
}
