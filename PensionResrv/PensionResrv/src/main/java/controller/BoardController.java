package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVo;
import domain.PageMaker;
import domain.SearchCriteria;
import service.BoardDao;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String str;
    public BoardController(String str) {
        this.str = str;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(str.equals("/board/boardNoti.do")) {
			System.out.println("boardNoti.do로 들어옴");
			BoardDao bd = new BoardDao();
			PageMaker pm = null;
			int dataPerPage = 0,page = 0, cnt=0;
			if(request.getParameter("dataPerPage")==null || request.getParameter("dataPerPage")=="") dataPerPage=10;
			else dataPerPage = Integer.parseInt(request.getParameter("dataPerPage"));
			
			if(request.getParameter("page")==null) page = 1;
			else page = Integer.parseInt(request.getParameter("page"));
			
			//검색시 사용하는 값들을 추가로 등록
			String searchOption = request.getParameter("searchOption");
			if(searchOption==null) searchOption = "제목만";
			String searchContext = request.getParameter("searchContext");
			if(searchContext==null) searchContext = "";
			
			SearchCriteria scri = new SearchCriteria();
			scri.setSearchOption(searchOption);
			scri.setSearchContext(searchContext);
			scri.setPage(page);
			scri.setPagePerNum(dataPerPage);
			
			scri.setBoardCategory("noti");
			ArrayList<BoardVo> boardNoti = bd.boardSelectAll(scri);
			cnt = bd.boardTotal(scri);
			pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			pm.calData();
			
			request.setAttribute("dataPerPage", dataPerPage);
			request.setAttribute("pm", pm);
			request.setAttribute("boardNoti", boardNoti);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardNoti.jsp");
			rd.forward(request, response);
			
		}
		
		else if (str.equals("/board/faq.do")) {
			System.out.println("faq.do 들어옴");
			BoardDao bd = new BoardDao();
			int dataPerPage_faq=0,page = 0, cnt_faq=0;
			
			if(request.getParameter("dataPerPage_faq")==null || request.getParameter("dataPerPage_faq")=="") dataPerPage_faq=10;
			else dataPerPage_faq = Integer.parseInt(request.getParameter("dataPerPage_faq"));
			
			if(request.getParameter("page")==null) page = 1;
			else page = Integer.parseInt(request.getParameter("page"));
			
			//검색시 사용하는 값들을 추가로 등록
			String searchOption = request.getParameter("searchOption");
			if(searchOption==null) searchOption = "제목만";
			String searchContext = request.getParameter("searchContext");
			if(searchContext==null) searchContext = "";
			
			SearchCriteria scri = new SearchCriteria();
			scri.setSearchOption(searchOption);
			scri.setSearchContext(searchContext);
			scri.setPage(page);
			scri.setPagePerNum(dataPerPage_faq);
			
			scri.setBoardCategory("faq");
			ArrayList<BoardVo> faq = bd.boardSelectAll(scri);
			cnt_faq = bd.boardTotal(scri);
			PageMaker pm_faq = new PageMaker();
			pm_faq.setScri(scri);
			pm_faq.setTotalCount(cnt_faq);
			pm_faq.calData();
			request.setAttribute("pm_faq", pm_faq);
			request.setAttribute("faq", faq);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/faq.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
