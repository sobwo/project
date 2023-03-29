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
			String memberPhone = null;
			String memberEmail = null;
			String memberId=null;
			String searchMeasure = request.getParameter("searchMeasure");
			String memberName = request.getParameter("memberName");
			
			if(request.getParameter("memberPhone") == "") memberPhone = "010";
			else memberPhone = request.getParameter("memberPhone");
			
			if(request.getParameter("memberEmail") == "") memberEmail = "a";
			else memberEmail = request.getParameter("memberEmail");
			
			SearchVo sv = new SearchVo();
			SearchDao sd = new SearchDao();
			
			sv.setSearchMeasure(searchMeasure);
			sv.setMemberName(memberName);
			sv.setMemberPhone(memberPhone);
			sv.setMemberEmail(memberEmail);

			if(var==1) { // ID찾기
				System.out.println("아이디 찾기 들어옴");
				valueId = sd.searchId(sv); 
				request.setAttribute("memberId", valueId);
				RequestDispatcher rd = request.getRequestDispatcher("/search/searchIdVal.jsp");
				rd.forward(request, response);
			}
			
			if(var==2) { //비밀번호 찾기
				System.out.println("비밀번호 찾기 들어옴");
				memberId = request.getParameter("memberId");
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
				String path = request.getContextPath()+"/member/memberLogin.do";
			 	response.sendRedirect(path);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
