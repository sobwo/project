package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String projectName = request.getContextPath();
		String str = uri.substring(projectName.length());
		
		String[] strArray = str.split("/");
		String strHub = strArray[1];
		
		if(strHub.equals("member")) {
			MemberController mc = new MemberController(str);
			mc.doGet(request, response);
		}
		else if(strHub.equals("board")) {
			BoardController bc = new BoardController(str);
			bc.doGet(request, response);
		}
		else if(strHub.equals("reserv")) {
			ReservController bc = new ReservController(str);
			bc.doGet(request, response);
		}
		else if(strHub.equals("search")) {
			SearchController sc = new SearchController(str);
			sc.doGet(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
