package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ReservVo;
import domain.RoomVo;
import service.ReservDao;
import service.RoomDao;

@WebServlet("/ReservController")
public class ReservController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String str;   

    public ReservController(String str) {
    	this.str = str;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(str.equals("/reservation/reserv_main.do")) {
    		System.out.println("reserv_main.do 들어옴");
    		ArrayList<RoomVo> rlist = new ArrayList<>();
    		RoomDao roomd = new RoomDao();
    		rlist = roomd.selectAll();
    		
    		request.setAttribute("rlist", rlist);
			RequestDispatcher rd1 = request.getRequestDispatcher("/reservation/reserv_main.jsp");
			rd1.forward(request, response);
    	}
    	
    	else if(str.equals("/reservation/reserv_status.do")) {
    		System.out.println("reserv_status.do 들어옴");
    		
			RequestDispatcher rd1 = request.getRequestDispatcher("/reservation/reserv_status.jsp");
			rd1.forward(request, response);
    	}
    	
    	else if(str.equals("/reservation/reserveAction.do")) {
    		System.out.println("reserveAction.do 들어옴");
    		
    		ArrayList<RoomVo> rlist = new ArrayList<>();
    		ReservVo rv = new ReservVo();
    		ReservDao rd = new ReservDao();
    		RoomDao roomd = new RoomDao();
    		String checkIn,checkOut,roomName;
    		int adult,child,baby;

    		if(request.getParameter("checkIn")==null) checkIn="0";
    		else checkIn = request.getParameter("checkIn");
    		if(request.getParameter("checkOut")==null) checkOut="0";
    		else checkOut = request.getParameter("checkOut");
    		if(request.getParameter("adult")==null) adult=0;
    		else adult = Integer.parseInt(request.getParameter("adult"));
    		if(request.getParameter("child")==null) child=0;
    		else child = Integer.parseInt(request.getParameter("child"));
    		if(request.getParameter("baby")==null) baby=0;
    		else baby = Integer.parseInt(request.getParameter("baby"));
    		if(request.getParameter("roomName")==null) roomName = "0호";
    		else roomName = request.getParameter("roomName");
    		
    		int roomNo = rd.selectRoomNo(roomName);
    		
    		rv.setCheckIn(checkIn);
    		rv.setCheckOut(checkOut);
    		rv.setAdultNum(adult);
    		rv.setChildNum(child);
    		rv.setBabyNum(baby);
    		rv.setRoomNo(roomNo);
    		
    		request.setAttribute("rv", rv);
    		
    		rlist = roomd.selectAll();
    		request.setAttribute("rlist", rlist);
    		
			RequestDispatcher rd1 = request.getRequestDispatcher("/reservation/reserving.jsp");
			rd1.forward(request, response);
    	}
    	
    	else if(str.equals("/reservation/reserving_next.do")) {
    		System.out.println("reserving_next.do 들어옴");
    		
			RequestDispatcher rd1 = request.getRequestDispatcher("/reservation/reserving_next.jsp");
			rd1.forward(request, response);
    	}
    	
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
