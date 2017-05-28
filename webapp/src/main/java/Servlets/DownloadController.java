package Servlets;

import ua.goryainov.hibernate.model.Action;
import ua.goryainov.hibernate.model.Administration;
import ua.goryainov.hibernate.model.User;
import ua.goryainov.service.AdministrationService;
import ua.goryainov.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

@WebServlet("/download")
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DownloadController(){
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setHeader("Content-disposition","attachment; filename=Customers.xls");

		// файл, который вы отправляете
		File my_file = new File("webapp/src/main/webapp/tmp/newBook.xls");

		// отправить файл в response
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(my_file);

		byte[] buffer = new byte[4096];
		int length;

		while ((length = in.read(buffer)) > 0){
			out.write(buffer, 0, length);
		}

		// освободить ресурсы
		in.close();
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}