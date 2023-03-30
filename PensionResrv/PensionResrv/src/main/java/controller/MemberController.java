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
import service.MemberDao;

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
				session.setAttribute("memberName", memberName);
				
				response.sendRedirect(request.getContextPath()+"/");
			}
			
		}
		
		else if(str.equals("/member/memberLogoutAction.do")) {
			System.out.println("memberLogoutAction.do 들어옴");
			HttpSession session = request.getSession();
			session.removeAttribute("memberNo");
			session.removeAttribute("memberName");
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
		
		else if (str.equals("/member/memberInfo.do")) {
			System.out.println("memberInfo.do 들어옴");
			HttpSession session = request.getSession();
			int memberNo = (int) session.getAttribute("memberNo");
			MemberVo mv = new MemberVo();
			MemberDao md = new MemberDao();
			
			mv = md.selectInfo(memberNo);
			
			request.setAttribute("mv", mv);
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberInfo.jsp");
			rd.forward(request, response);
		}
		
		else if(str.equals("/member/memberInfoModifyAction.do")) {
			System.out.println("memberInfoModifyAction.do 들어옴");
			int value = 0;
			HttpSession session = request.getSession();
			int memberNo = (int) session.getAttribute("memberNo");
			
			String memberPw = request.getParameter("memberPw");
			String memberName = request.getParameter("memberName");
			String memberBirth = request.getParameter("memberBirth");
			String memberPhone = request.getParameter("memberPhone");
			String memberEmail = request.getParameter("memberEmail");
			
			MemberVo mv = new MemberVo();
			mv.setMemberNo(memberNo);
			mv.setMemberPw(memberPw);
			mv.setMemberName(memberName);
			mv.setMemberBirth(memberBirth);
			mv.setMemberPhone(memberPhone);
			mv.setMemberEmail(memberEmail);
			
			MemberDao md = new MemberDao();
			
			value = md.modifyInfo(mv);
			
			if(value == 1) {
				response.setContentType("text/html; charset=UTF-8");
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('비밀번호가 변경되었습니다.'); location.href='"+request.getContextPath()+"/member/memberInfo.do';</script>");
			    out.flush();
			    return ;
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
			    out.println("<script>alert('비밀번호가 변경이 불가합니다.'); history.go(-1);</script>");
			    out.flush();
			    return ;
			}
		}
		
		else if(str.equals("/member/memberQuitAction.do")) {
			int value = 0;
			HttpSession session = request.getSession();
			int memberNo = (int) session.getAttribute("memberNo");
			
			MemberDao md = new MemberDao();
			value=md.quitMember(memberNo);
			
			if(value==1) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
			    out.println("<script>alert('회원 탈퇴 되었습니다.');location.href='"+request.getContextPath()+"/member/memberLogoutAction.do'</script>");
			    out.flush();
			    return ;
			}
			
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
			    out.println("<script>alert('회원탈퇴가 불가합니다.'); history.go(-1);</script>");
			    out.flush();
			    return ;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
