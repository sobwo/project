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

import domain.MemberVo;
import domain.SearchVo;
import service.SearchDao;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    String str;
    public SearchController(String str) {
    	this.str = str;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if (str.equals("/search/searchId.do")){
			RequestDispatcher rd = request.getRequestDispatcher("/search/searchId.jsp");
			rd.forward(request, response);
		}
		
		else if (str.equals("/search/searchAction.do")) {
			System.out.println("searchAction.do 들어옴");
			
			int var = Integer.parseInt(request.getParameter("var"));
			
			ArrayList<MemberVo> valueId = new ArrayList<>();
			int valuePwCheck = 0;
			String searchMeasure = request.getParameter("searchMeasure");
			String memberPhone = null;
			String memberEmail = null;
			String memberName = null;
			String memberId=null;
			
			if(request.getParameter("searchMeasure").equals("phone")) {
				memberPhone = request.getParameter("memberPhone");
				memberName = request.getParameter("memberName_phone"); 
			}
			
			else if(request.getParameter("searchMeasure").equals("email")) {
				memberEmail = request.getParameter("memberEmail");
				memberName = request.getParameter("memberName_email"); 
			}
			
			SearchVo sv = new SearchVo();
			SearchDao sd = new SearchDao();
			
			sv.setSearchMeasure(searchMeasure);
			sv.setMemberName(memberName);
			sv.setMemberPhone(memberPhone);
			sv.setMemberEmail(memberEmail);
			
			if(var==1) { // ID찾기
				System.out.println("아이디 찾기 들어옴");
				valueId = sd.searchId(sv);
				request.setAttribute("valueId", valueId);
				RequestDispatcher rd = request.getRequestDispatcher("/search/searchIdVal.jsp");
				rd.forward(request, response);
			}
			
			if(var==2) { //비밀번호 찾기
				System.out.println("비밀번호 찾기 들어옴");
				if(request.getParameter("searchMeasure").equals("phone")) {
					memberId = request.getParameter("memberId_phone"); 
				}
				
				else if(request.getParameter("searchMeasure").equals("email")) {
					memberId = request.getParameter("memberId_email"); 
				}
				
				sv.setMemberId(memberId);
				valuePwCheck = sd.searchPwCheck(sv);
	
				if(valuePwCheck == 1) {
					request.setAttribute("memberId", memberId);
					RequestDispatcher rd = request.getRequestDispatcher("/search/searchPwVal.jsp");
					rd.forward(request, response);
				}
				else {
					response.setContentType("text/html; charset=UTF-8");
				    PrintWriter out = response.getWriter();
				    out.println("<script>alert('입력 정보가 일치하지 않습니다.');history.go(-1);</script>");
				    out.flush();
				    return;
				}	
			}
		}
		
		else if (str.equals("/search/searchPw.do")) {
			System.out.println("searchPw.do 들어옴");
			RequestDispatcher rd = request.getRequestDispatcher("/search/searchPw.jsp");
			rd.forward(request, response);
		}
		
		else if (str.equals("/search/searchPwReset.do")) {
			System.out.println("searchPwReset.do 들어옴");
			int value=0;
			
			String memberId = request.getParameter("memberId");
			String memberPw = request.getParameter("memberPw");
			
			SearchDao sd = new SearchDao();
			value = sd.searchPwReset(memberId, memberPw);
			
			if(value==1) {
				response.setContentType("text/html; charset=UTF-8");
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('비밀번호가 변경되었습니다.'); location.href='"+request.getContextPath()+"/member/memberLogin.do';</script>");
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
