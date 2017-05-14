package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.goryainov.hibernate.model.Administration;
import ua.goryainov.hibernate.model.Commission;
import ua.goryainov.hibernate.model.OrderProduct;
import ua.goryainov.hibernate.model.Product;
import ua.goryainov.hibernate.model.User;
import ua.goryainov.service.AdministrationService;
import ua.goryainov.service.OrderProductService;
import ua.goryainov.service.OrderService;
import ua.goryainov.service.ProductService;
import ua.goryainov.service.StatusService;
import ua.goryainov.service.UserService;

@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SHOW = "/cart.jsp";
	private AdministrationService adminService;
	private UserService userService;
	private ProductService productService;

	public CartController(){
		super();
		//bookService = new BookService();
		adminService = new AdministrationService();
		userService = new UserService();
		productService = new ProductService();
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
			}
		}
		Object adminId = request.getSession().getAttribute("admin");
		if(adminId!=null){
			Administration admin = adminService.findById(adminId.toString());
			if(admin!=null){
				request.setAttribute("admin", admin);
			}
		}
		List<Product> productList = new ArrayList<Product>();	
		List<Integer> counts = new ArrayList<Integer>();
		 int index=0;
		for(Cookie cookie:request.getCookies()){			
			try{
				index=Integer.parseInt(cookie.getName().replace("product", ""));
				productList.add(productService.findById(index));
				counts.add(Integer.parseInt(cookie.getValue()));
			}
			catch(Exception e){}
		}
		request.setAttribute("productList", productList);
		request.setAttribute("counts", counts);
		request.setAttribute("currentPage", "cart");
		String forward = "";
			forward = SHOW;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		Object userId = session.getAttribute("userId");
		StatusService statusService = new StatusService();
		OrderService orderService = new OrderService();
		OrderProductService orderProductService = new OrderProductService();
		Cookie[] cookies =  request.getCookies();
		if(userId!=null)
		{
			Integer id = Integer.parseInt(userId.toString());
			User user = userService.findById(id);
			if(user!=null){
				Commission com = new Commission(userService.findById(id), 
						new Date(), 
						request.getParameter("deliveryAdress"),
						statusService.findById(1));
				orderService.persist(com);
				String[] darray=request.getParameterValues("id");
				int productIdInt = 0;
				for(String productId: darray){
					if(!productId.equals("")){
					productIdInt = Integer.parseInt(productId);
					orderProductService.persist(
							new OrderProduct(com,
											productService.findById(productIdInt),
											Float.parseFloat(request.getParameter("product"+productIdInt+"[price]")),
											Integer.parseInt(request.getParameter("product"+productIdInt+"[count]"))));
					}
					}
				if (cookies != null)
			        for (int i = 0; i < cookies.length; i++) {
			        	if(!cookies[i].getName().equals("JSESSIONID"))
			        	{
			            cookies[i].setValue("");
			            cookies[i].setPath("/");
			            cookies[i].setMaxAge(0);
			            response.addCookie(cookies[i]);
			        	}
			        }				
				
			}
		}		
	}
}