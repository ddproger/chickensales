package Servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import ua.goryainov.hibernate.model.Administration;
import ua.goryainov.hibernate.model.Product;
import ua.goryainov.hibernate.model.User;
import ua.goryainov.service.AdministrationService;
import ua.goryainov.service.ProductService;
import ua.goryainov.service.UserService;

@WebServlet("/product")
@MultipartConfig
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SHOW = "/product.jsp";
	private static String EDIT = "/editProduct.jsp";
	private AdministrationService adminService;
	private UserService userService;
	private ProductService productService;

	   
	public ProductController(){
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
				
			}
		}

		Object adminId = request.getSession().getAttribute("admin");
		if(adminId!=null){
			Administration admin = adminService.findById(adminId.toString());
			if(admin!=null){
				request.setAttribute("admin", admin);
			}
		}
		String productId = request.getParameter("id");
		if(productId!=null){
			Integer id = Integer.parseInt(productId.toString());
			Product product = productService.findById(id);
			request.setAttribute("product", product);
		}
		String forward = "";
		forward = SHOW;
		if(request.getParameter("action")!=null){
		if(request.getParameter("action").equals("add"))
		{
			forward=EDIT;
			RequestDispatcher view;

			view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}else if(request.getParameter("action").equals("edit")){
			forward=EDIT;
			RequestDispatcher view;

			view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}else if(request.getParameter("action")!=null&&request.getParameter("action").equals("delete")){
			productService.delete(Integer.parseInt(productId));
			response.sendRedirect("catalogue");
		}}else{
			RequestDispatcher view;

			view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		final String path = "E:\\Programming\\Projects\\Java\\studyWorkspace\\webapp\\app-presentation\\src\\main\\webapp\\img\\product";
	    final Part filePart = request.getPart("file");
	    final String fileName = getFileName(filePart);

		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		
		if(id!=null&&!id.equals("")){
			Product prod=productService.findById(Integer.parseInt(id));
			prod.setName(name);
			prod.setPrice(Float.parseFloat(price));
			prod.setDescription(description);
			if(!fileName.equals(""))
			{
				String photo = prod.getPhoto();
				if(photo.equals("")==false)
					{
					File oldFile = new File(path + File.separator
			                + prod.getPhoto());
					oldFile.delete();
					}
					prod.setPhoto(fileName);
			}
			productService.update(prod);			
		}else{
			Product prod= new Product(name,Float.parseFloat(price),description,fileName);
			productService.persist(prod);
		}
		 
		    OutputStream out = null;
		    InputStream filecontent = null;
		    
		    try {
		        out = new FileOutputStream(new File(path + File.separator
		                + fileName));
		        filecontent = filePart.getInputStream();

		        int read = 0;
		        final byte[] bytes = new byte[1024];

		        while ((read = filecontent.read(bytes)) != -1) {
		            out.write(bytes, 0, read);
		        }
		        
		        
		    } catch (FileNotFoundException fne) {
		        

		        
		    } finally {
		        if (out != null) {
		            out.close();
		        }
		        if (filecontent != null) {
		            filecontent.close();
		        }
		       
		    }		 
		 response.sendRedirect("catalogue");

	}
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}