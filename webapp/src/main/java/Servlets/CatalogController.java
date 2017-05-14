package Servlets;

import java.io.IOException;
import java.util.List;
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
import ua.goryainov.hibernate.model.Product;
import ua.goryainov.hibernate.model.User;
import ua.goryainov.service.AdministrationService;
import ua.goryainov.service.ProductService;
import ua.goryainov.service.UserService;

@WebServlet("/catalogue")
public class CatalogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SHOW = "/catalogue.jsp";
	private AdministrationService adminService;
	private UserService userService;
	private ProductService productService;

	public CatalogController(){
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
		
		Object userId = session.getAttribute("userId");// "1";//request.getParameter("userId");
		if(userId!=null)
		{
			Integer id = Integer.parseInt(userId.toString());
			User user = userService.findById(id);
			if(user!=null){
				request.setAttribute("user", user);
				//Hibernate.initialize(user.getActionList());

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
		List<Product> productList = productService.findAll();
		request.setAttribute("products", productList);
		request.setAttribute("currentPage", "catalogue");
		String forward = "";
			forward = SHOW;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		/*BookModel bookModel = new BookModel();
		bookModel.setTitle(request.getParameter("booktitle"));
		bookModel.setAuthor(request.getParameter("authorname"));		
		String bookId = request.getParameter("bookid");
		Book bookEntity = new Book();
		if (bookId == null || bookId.isEmpty()) {			
			Converter.BookModelToBookEntity(bookEntity, bookModel);
			bookService.persist(bookEntity);
		} else {
			bookModel.setBookId(Integer.valueOf(bookId));			
			Converter.BookModelToBookEntity(bookEntity, bookModel);
			bookService.update(bookEntity);
		}
		
		request.setAttribute("books", bookService.findAll());
		*/
		RequestDispatcher view = request.getRequestDispatcher(SHOW);
		view.forward(request, response);
	}
}