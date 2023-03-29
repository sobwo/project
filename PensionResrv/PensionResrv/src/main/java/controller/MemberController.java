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
import domain.SearchVo;
import service.MemberDao;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String str;
    public MemberController(String str) {
    	this.str = str;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(str.equals("/member/memberLogin.do")) {
			System.out.println("memberLogin 들어옴");
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberLogin.jsp");
			rd.forward(request, response);
		}
		
		else if(str.equals("/member/memberLoginAction.do")) {
			System.out.println("memberLoginAction 들어옴");
			String memberId = request.getParameter("memberId");
			String memberPw = request.getParameter("memberPw");

			MemberDao md = new MemberDao();
			MemberVo mv = md.memberLogin(memberId,memberPw);
					
			if(mv==null) {
				response.setContentType("text/html; charset=UTF-8");
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('패스워드가 일치하지 않습니다.');history.go(-1);</script>");
			    out.flush();
			    return;
			}
			else {
				int memberNo = mv.getMemberNo();
				String memberName = mv.getMemberName();
				String memberBirth = mv.getMemberBirth();
				String memberPhone = mv.getMemberPhone();
				String memberEmail = mv.getMemberEmail();
				
				HttpSession session = request.getSession();
				session.setAttribute("memberNo", memberNo);
				session.setAttribute("memberPw", memberPw);
				session.setAttribute("memberName", memberName);
				session.setAttribute("memberBirth", memberBirth);
				session.setAttribute("memberPhone", memberPhone);
				session.setAttribute("memberEmail", memberEmail);
				
				response.sendRedirect(request.getContextPath()+"/");
			}
			
		}
		
		else if(str.equals("/member/memberLogoutAction.do")) {
			System.out.println("memberLogoutAction.do 들어옴");
			HttpSession session = request.getSession();
			session.removeAttribute("memberNo");
			session.removeAttribute("memberId");
			session.removeAttribute("memberPw");
			session.removeAttribute("memberName");
			session.removeAttribute("memberBirth");
			session.removeAttribute("memberPhone");
			session.removeAttribute("memberEmail");
			session.invalidate();
				
			response.sendRedirect(request.getContextPath()+"/");
		}
		
		else if(str.equals("/member/memberJoin.do")) {
			System.out.println("memberJoin 들어옴");
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberJoin.jsp");
			rd.forward(request, response);
		}
		
		else if(str.equals("/member/memberJoinAction.do")) { 
			System.out.println("memberJoinAction 들어옴");
			int value = 0;
			String birth_yy = null;
			String birth_mm = null;
			String birth_dd = null;
			String memberId = request.getParameter("memberId");
			String memberPw = request.getParameter("memberPw");
			String memberName = request.getParameter("memberName");
			String memberPhone = request.getParameter("memberPhone");
			String memberEmail = request.getParameter("memberEmail");
			
			if(request.getParameter("birth_yy")=="") birth_yy = "1900";
			else birth_yy = request.getParameter("birth_yy");
			
			birth_mm = request.getParameter("birth_mm");
			
			if(request.getParameter("birth_dd")=="") birth_dd = "01";
			else birth_dd = request.getParameter("birth_dd");
				
			String memberBirth = birth_yy+birth_mm+birth_dd;
			
			MemberVo mv = new MemberVo();
			mv.setMemberId(memberId);
			mv.setMemberPw(memberPw);
			mv.setMemberName(memberName);
			mv.setMemberBirth(memberBirth);
			mv.setMemberPhone(memberPhone);
			mv.setMemberEmail(memberEmail);
			
			MemberDao md = new MemberDao();
			value=md.memberInsert(mv);
			
			if(value==1) {
				String path = request.getContextPath()+"/member/memberLogin.do";
			 	response.sendRedirect(path);
			}
		}
		
		else if (str.equals("/member/memberIdCheck.do")) {
			System.out.println("memberIdCheck 들어옴");
			String memberId = request.getParameter("memberId");

			MemberDao md = new MemberDao();
			int value = md.memberIdCheck(memberId);
			 
			PrintWriter out = response.getWriter();
			out.println("{\"value\": \""+value+"\"}");
		}
		
		else if (str.equals("/member/searchId.do")){
			RequestDispatcher rd = request.getRequestDispatcher("/member/searchId.jsp");
			rd.forward(request, response);
		}
		
		else if (str.equals("/member/searchIdAction.do")) {
			System.out.println("searchIdAction.do 들어옴");
			
			ArrayList<MemberVo> value = new ArrayList<>();
			String memberPhone = null;
			String memberEmail = null;
			String searchMeasure = request.getParameter("searchMeasure");
			String memberName = request.getParameter("memberName");
			
			if(request.getParameter("memberPhone") == "") memberPhone = "010";
			else memberPhone = request.getParameter("memberPhone");
			
			if(request.getParameter("memberEmail") == "") memberEmail = "a";
			else memberEmail = request.getParameter("memberEmail");
			SearchVo sv = new SearchVo();
			MemberDao md = new MemberDao();
			
			sv.setSearchMeasure(searchMeasure);
			sv.setMemberName(memberName);
			sv.setMemberPhone(memberPhone);
			sv.setMemberEmail(memberEmail);
			
			value = md.searchId(sv);
			
			request.setAttribute("memberValue", value);
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/searchIdVal.jsp");
			rd.forward(request, response);
		}
		
		else if (str.equals("/member/searchPwAction.do")) {
			System.out.println("searchPwAction.do 들어옴");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}