package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberVo;
import domain.ReservVo;
import domain.RoomVo;
import domain.SelectRoom;
import service.MemberDao;
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
    		String checkIn,checkOut,select_roomName;
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
    		if(request.getParameter("select_roomName")==null) select_roomName = "0호";
    		else select_roomName = request.getParameter("select_roomName");
    		int roomNo = rd.selectRoomNo(select_roomName);
    		
    		rv.setRoomNo(roomNo);
    		rv.setCheckIn(checkIn);
    		rv.setCheckOut(checkOut);
    		rv.setAdultNum(adult);
    		rv.setChildNum(child);
    		rv.setBabyNum(baby);
    		
    		request.setAttribute("rv", rv);
    		
    		rlist = roomd.selectAll();
    		request.setAttribute("rlist", rlist);
    		
			RequestDispatcher rd1 = request.getRequestDispatcher("/reservation/reserving.jsp");
			rd1.forward(request, response);
    	}
    	
    	else if(str.equals("/reservation/reserving_next.do")) {
    		System.out.println("reserving_next.do 들어옴");
    		SelectRoom sr = null;
    		ArrayList<ReservVo> rlist = new ArrayList<>();
    		String[] rlist_roomNo = request.getParameterValues("rlist_roomNo");
    		String[] adult_num_list = request.getParameterValues("adultNum");
    		String[] child_num_list = request.getParameterValues("childNum");
    		String[] baby_num_list = request.getParameterValues("babyNum");    		
    		String[] select_roomName = request.getParameterValues("rlist_roomName");
    		String[] price_list = request.getParameterValues("select_price");
    		String checkIn = request.getParameter("checkIn");
    		String checkOut = request.getParameter("checkOut");
    		int option_value = Integer.parseInt(request.getParameter("option_value"));
    		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
    		String[] room_check = request.getParameterValues("room_check");
    		
    		int[] select_roomNo = new int[room_check.length];
    		int[] adult_num = new int[room_check.length];
    		int[] child_num = new int[room_check.length];
    		int[] baby_num = new int[room_check.length];
    		int[] select_price = new int[room_check.length];
    		
    		for(int i=0;i<rlist_roomNo.length;i++) {
    			select_roomNo[i]= Integer.parseInt(rlist_roomNo[i]);
    			adult_num[i] = Integer.parseInt(adult_num_list[i]);
    			child_num[i] = Integer.parseInt(child_num_list[i]);
    			baby_num[i] = Integer.parseInt(baby_num_list[i]);
    			select_price[i] = Integer.parseInt(price_list[i]);
    		}

    		for(int i=0;i<room_check.length;i++) {
    			if(room_check[i].equals("2")) {
    				sr = new SelectRoom();
    				sr.setSelect_roomNo(select_roomNo[i]);
    				sr.setCheckIn(checkIn);
    				sr.setCheckOut(checkOut);
    				sr.setAdultNum(adult_num[i]);
    				sr.setChildNum(child_num[i]);
    				sr.setBabyNum(baby_num[i]);
    				sr.setOptionNum(option_value);
    				sr.setSelect_price(select_price[i]);
    				sr.setSelect_totalPrice(select_price[i], option_value);
    				sr.setSelect_roomName(select_roomName[i]);
    				rlist.add(sr);
    			}
    		}
    		
    		request.setAttribute("totalPrice", totalPrice);
    		request.setAttribute("rlist", rlist);
       		int memberNo = 0;
       		
       		//체크시 사용자 정보 불러오기 위해 전송
    		HttpSession session = request.getSession();
    		if(session.getAttribute("memberNo")==null) memberNo = 0;
			else memberNo = (int) session.getAttribute("memberNo");

    		
			MemberVo mv = new MemberVo();
			MemberDao md = new MemberDao();
			
			mv = md.selectInfo(memberNo);
			
			request.setAttribute("memberNo", memberNo);
			request.setAttribute("mv", mv);
			
			RequestDispatcher rd1 = request.getRequestDispatcher("/reservation/reserving_next.jsp");
			rd1.forward(request, response);
    	}
    	
    	else if(str.equals("/reservation/reserving_pay.do")) {
    		System.out.println("reserving_pay.do 들어옴");
    		ArrayList<ReservVo> rlist = new ArrayList<>();
    		ReservDao rd = new ReservDao();
    		MemberDao md = new MemberDao();
    		MemberVo mv = new MemberVo();
    		ReservVo rv = null;

    		String[] select_roomNo = request.getParameterValues("select_roomNo");
    		String[] adult_num = request.getParameterValues("adultNum");
    		String[] child_num = request.getParameterValues("childNum");
    		String[] baby_num = request.getParameterValues("babyNum");    		
    	
    		String checkIn = request.getParameter("checkIn");
    		String checkOut = request.getParameter("checkOut");
    		int optionNum = Integer.parseInt(request.getParameter("optionNum"));
    		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
    		
    		int reserv_memberNo = Integer.parseInt(request.getParameter("reserv_memberNo"));
    		String reserv_memberName = request.getParameter("reserv_memberName");
    		String reserv_memberBirth = request.getParameter("reserv_memberBirth");
    		String reserv_memberPhone = request.getParameter("reserv_memberPhone");
    		String reserv_extraPhone = request.getParameter("reserv_extraPhone");
    		String reserv_memberEmail = request.getParameter("reserv_memberEmail");
    		String reserv_pickup = request.getParameter("rserv_pickup");
    		String reserv_arriveTime = request.getParameter("reserv_arriveTime");
    		String reserv_request = request.getParameter("reserv_request");
    		
    		mv.setMemberNo(reserv_memberNo);
    		mv.setMemberName(reserv_memberName);
    		mv.setMemberBirth(reserv_memberBirth);
    		mv.setMemberPhone(reserv_memberPhone);
    		mv.setMemberEmail(reserv_memberEmail);
    		
    		if(reserv_memberNo==0) {
    			md.OffrineMemberInsert(mv);
    		}
    		
    		for(int i=0;i<select_roomNo.length;i++) {
    			rv = new ReservVo();
    			rv.setRoomNo(Integer.parseInt(select_roomNo[i]));
    			rv.setCheckIn(checkIn);
    			rv.setCheckOut(checkOut);
    			rv.setAdultNum(Integer.parseInt(adult_num[i]));
    			rv.setChildNum(Integer.parseInt(child_num[i]));
    			rv.setBabyNum(Integer.parseInt(baby_num[i]));
    			rv.setOptionNum(optionNum);
    			rv.setTotalPay(totalPrice);
        		rv.setExtraPhone(reserv_extraPhone);
        		rv.setPickup(reserv_pickup);
        		rv.setArriveTime(reserv_arriveTime);
        		rv.setRequest(reserv_request);
    			rv.setMemberNo(reserv_memberNo);
    			
    			rlist.add(rv);
    		}
    		
    		int value = rd.reservInsert(rlist);
    		
    		if(value==1) {
				response.setContentType("text/html; charset=UTF-8");
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('예약 완료');location.href='"+request.getContextPath()+"/reservation/reserv_status.do'</script>");
			    out.flush();
			    return;
    		}
    		
    	}
    	
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
