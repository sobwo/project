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
    	
    	else if(str.equals("/reservation/reservAction.do")) {
    		System.out.println("reservAction.do 들어옴");
    		
    		ArrayList<RoomVo> rlist = new ArrayList<>();
    		ReservVo rv = new ReservVo();
    		ReservDao rd = new ReservDao();
    		RoomDao roomd = new RoomDao();
    		
    		String checkIn = request.getParameter("checkIn");
    		String checkOut = request.getParameter("checkOut");
    		int adult = Integer.parseInt(request.getParameter("adult"));
    		int child = Integer.parseInt(request.getParameter("child"));
    		int baby = Integer.parseInt(request.getParameter("baby"));
    		String roomName = request.getParameter("roomName");
    		int roomNo = 0;

    		roomNo = rd.selectRoomNo(roomName);
    		
    		rv.setCheckIn(checkIn);
    		rv.setCheckOut(checkOut);
    		rv.setAdultNum(adult);
    		rv.setChildNum(child);
    		rv.setBabyNum(baby);
    		rv.setRoomNo(roomNo);
    		
    		request.setAttribute("rv", rv);
    		
    		rlist = roomd.selectAll();
    		request.setAttribute("rlist", rlist);
    	
    		checkOut = checkOut.substring(0,4)+checkOut.substring(5,7)+checkOut.substring(7,9);
    		checkIn = checkOut.substring(0,4)+checkIn.substring(5,7)+checkIn.substring(7,9);
    		int night = Integer.parseInt(checkOut) - Integer.parseInt(checkIn);
    		System.out.println(checkOut);
    		System.out.println(checkIn);
    		System.out.println(night);
    		if(night>=0) request.setAttribute("night", night);
    		
			RequestDispatcher rd1 = request.getRequestDispatcher("/reservation/reserving.jsp");
			rd1.forward(request, response);
    	}
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
