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

import domain.BoardVo;
import domain.MemberVo;
import domain.ReservVo;
import domain.RoomPriceVo;
import domain.RoomVo;
import service.BoardDao;
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
    		ArrayList<BoardVo> blist = new ArrayList<>();
    		
    		RoomDao roomd = new RoomDao();
    		BoardDao bd = new BoardDao();
    		HttpSession session = request.getSession();
    		
    		rlist = roomd.selectAll();
    		blist = bd.reserv_main_board();
    		
    		request.setAttribute("blist", blist);
    		request.setAttribute("rlist", rlist);
    		
    		String url=request.getContextPath()+"/reservation/reserv_main.do";
    		session.setAttribute("url", url);
 
			RequestDispatcher rd1 = request.getRequestDispatcher("/reservation/reserv_main.jsp");
			rd1.forward(request, response);
    	}
    	
    	else if(str.equals("/reservation/reserv_status.do")) {
    		System.out.println("reserv_status.do 들어옴");
    		
    		RoomDao rd = new RoomDao();
    		
    		ArrayList<RoomPriceVo> rpv = rd.roomCalendar();
    		
    		
    		HttpSession session = request.getSession();
    		String url=request.getContextPath()+"/reservation/reserv_status.do";
    		session.setAttribute("url", url);
    		
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
    		
    		rv.setRoomNo(roomNo);
    		rv.setCheckIn(checkIn);
    		rv.setCheckOut(checkOut);
    		rv.setAdultNum(adult);
    		rv.setChildNum(child);
    		rv.setBabyNum(baby);

    		request.setAttribute("rv", rv);
    		
    		rlist = roomd.selectAll();
    		request.setAttribute("rlist", rlist);
    		
    		HttpSession session = request.getSession();
    		String url=request.getContextPath()+"/reservation/reserveAction.do";
    		session.setAttribute("url", url);
    		
			RequestDispatcher rd1 = request.getRequestDispatcher("/reservation/reserving.jsp");
			rd1.forward(request, response);
    	}
    	
    	else if(str.equals("/reservation/reserving_next.do")) {
    		System.out.println("reserving_next.do 들어옴");
    		ReservVo rv = null;
    		String[] roomNo = request.getParameterValues("rlist_roomNo");
    		String[] roomName_list = request.getParameterValues("rlist_roomName");
    		String[] adultNum = request.getParameterValues("adultNum");
    		String[] childNum = request.getParameterValues("childNum");
    		String[] babyNum = request.getParameterValues("babyNum");    		
    		String checkIn = request.getParameter("checkIn");
    		String checkOut = request.getParameter("checkOut");
    		int option_value = Integer.parseInt(request.getParameter("option_value"));
    		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
    		String[] room_check = request.getParameterValues("room_check");
    		
    		String roomName = null;
    		
    		for(int i=0;i<room_check.length;i++) {
    			if(room_check[i].equals("2")) {
    				rv = new ReservVo();
    				rv.setRoomNo(Integer.parseInt(roomNo[i]));
    				rv.setCheckIn(checkIn);
    				rv.setCheckOut(checkOut);
    				rv.setAdultNum(Integer.parseInt(adultNum[i]));
    				rv.setChildNum(Integer.parseInt(childNum[i]));
    				rv.setBabyNum(Integer.parseInt(babyNum[i]));
    				rv.setOptionNum(option_value);
    				rv.setTotalPay(totalPrice);
    				
    				roomName = roomName_list[i];
    			}
    		}
    		
    		request.setAttribute("roomName", roomName);
    		request.setAttribute("rv", rv);
    		
       		//체크시 사용자 정보 불러오기 위해 전송
    		int memberNo=0;
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
    		ReservDao rd = new ReservDao();
    		MemberDao md = new MemberDao();
    		MemberVo mv = new MemberVo();
    		ReservVo rv = new ReservVo();;

    		int roomNo = Integer.parseInt(request.getParameter("roomNo"));
    		String checkIn = request.getParameter("checkIn");
    		String checkOut = request.getParameter("checkOut");
    		int adultNum = Integer.parseInt(request.getParameter("adultNum"));
    		int childNum = Integer.parseInt(request.getParameter("childNum"));
    		int babyNum = Integer.parseInt(request.getParameter("babyNum"));    			
    		int optionNum = Integer.parseInt(request.getParameter("optionNum"));
    		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
    		
    		String memberName = request.getParameter("memberName");
    		String memberBirth = request.getParameter("memberBirth");
    		String memberPhone = request.getParameter("memberPhone");
    		String memberEmail = request.getParameter("memberEmail");
		
    		String extraPhone = request.getParameter("extraPhone");
    		String pickup = request.getParameter("pickup");
    		String arriveTime = request.getParameter("arriveTime");
    		String request_ = request.getParameter("request");

    		mv.setMemberName(memberName);
    		mv.setMemberBirth(memberBirth);
    		mv.setMemberPhone(memberPhone);
    		mv.setMemberEmail(memberEmail);
    		
    		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    		if(memberNo==0) {
    			md.OffrineMemberInsert(mv);
    		}
    		
    		MemberVo value_member = md.memberLoginOff(mv);
    		
			rv.setRoomNo(roomNo);
			rv.setCheckIn(checkIn);
			rv.setCheckOut(checkOut);
			rv.setAdultNum(adultNum);
			rv.setChildNum(childNum);
			rv.setBabyNum(babyNum);
			rv.setOptionNum(optionNum);
			rv.setTotalPay(totalPrice);
    		rv.setExtraPhone(extraPhone);
    		rv.setPickup(pickup);
    		rv.setArriveTime(arriveTime);
    		rv.setRequest(request_);
    		rv.setMemberNo(value_member.getMemberNo());
    		
    		int value = rd.reservInsert(rv);

    		if(value==1) {
				response.setContentType("text/html; charset=UTF-8");
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('예약 완료');location.href='"+request.getContextPath()+"/reservation/reserv_check.do'</script>");
			    out.flush();
			    return;
    		}
    		
    	}
    	else if(str.equals("/reservation/reserv_check.do")) {
    		System.out.println("reserv_check.do 들어옴");
    		
    		ReservDao rd = new ReservDao();
 
    		HttpSession session = request.getSession();
    		int memberNo = 0;
    		if(session.getAttribute("memberNo")==null) memberNo=0;
    		else memberNo=(int)session.getAttribute("memberNo");
    		
    		ArrayList<ReservVo> rlist = rd.selectReserv(memberNo);
    		
    		request.setAttribute("rlist", rlist);
    		
    		String url=request.getContextPath()+"/reservation/reserv_check.do";
    		session.setAttribute("url", url);
    		
    		RequestDispatcher rd1 = request.getRequestDispatcher("/reservation/reserv_check.jsp");
			rd1.forward(request, response);
    	}
    	
    	else if(str.equals("/reservation/reserv_cancel.do")) {
    		System.out.println("reserv_cancel.do 들어옴");
    		
    		ReservDao rd = new ReservDao();
    		
    		int reservNo = Integer.parseInt(request.getParameter("reservNo"));
    		
    		int value = rd.reservCancel(reservNo);
    		
    		if(value==1) {
				response.setContentType("text/html; charset=UTF-8");
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('예약 취소 완료');location.href='"+request.getContextPath()+"/reservation/reserv_check.do'</script>");
			    out.flush();
			    return;
    		}
    		
    	}
    	
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
