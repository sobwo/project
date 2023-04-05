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
			scri.setBoardCategory("noti");
			scri.setSearchOption(searchOption);
			scri.setSearchContext(searchContext);
			scri.setPage(page);
			scri.setPagePerNum(dataPerPage);

			ArrayList<BoardVo> boardNoti = bd.boardSelectAll(scri);
			cnt = bd.boardTotal(scri);
			
			PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			pm.calData();
		
			request.setAttribute("dataPerPage", dataPerPage);
			request.setAttribute("pm", pm);
			request.setAttribute("boardNoti", boardNoti);
			
			HttpSession session = request.getSession();
			session.setAttribute("path", "boardNoti");
			
			String url = request.getContextPath()+"/board/boardNoti.do";
			session.setAttribute("url", url);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardNoti.jsp");
			rd.forward(request, response);
			
		}
		
		else if (str.equals("/board/faq.do")) {
			System.out.println("faq.do 들어옴");
			BoardDao bd = new BoardDao();
			
			int dataPerPage_faq=0,page_faq = 0, cnt_faq=0;
			
			if(request.getParameter("dataPerPage_faq")==null || request.getParameter("dataPerPage_faq")=="") dataPerPage_faq=10;
			else dataPerPage_faq = Integer.parseInt(request.getParameter("dataPerPage_faq"));
			
			if(request.getParameter("page_faq")==null) page_faq = 1;
			else page_faq = Integer.parseInt(request.getParameter("page_faq"));
			
			//faq
			SearchCriteria scri = new SearchCriteria();
			scri.setBoardCategory("faq");
			scri.setSearchOption("제목만");
			scri.setSearchContext("");
			scri.setPage(page_faq);
			scri.setPagePerNum(dataPerPage_faq);
		
			ArrayList<BoardVo> faq = bd.boardSelectAll(scri);
			cnt_faq = bd.boardTotal(scri);
			
			PageMaker pm_faq = new PageMaker();
			pm_faq.setScri(scri);
			pm_faq.setTotalCount(cnt_faq);
			pm_faq.calData();
			request.setAttribute("pm_faq", pm_faq);
			request.setAttribute("faq", faq);
			
			//문의사항
			//검색시 사용하는 값들을 추가로 등록
			int dataPerPage_que=0,page_que = 0, cnt_que=0;
			
			if(request.getParameter("dataPerPage_que")==null || request.getParameter("dataPerPage_que")=="") dataPerPage_que=10;
			else dataPerPage_que = Integer.parseInt(request.getParameter("dataPerPage_que"));
			
			if(request.getParameter("page_que")==null) page_que = 1;
			else page_que = Integer.parseInt(request.getParameter("page_que"));
			
			String searchOption = request.getParameter("searchOption");
			if(searchOption==null) searchOption = "제목만";
			String searchContext = request.getParameter("searchContext");
			if(searchContext==null) searchContext = "";
			
			SearchCriteria scri_que = new SearchCriteria();
			scri_que.setBoardCategory("question");
			scri_que.setSearchOption(searchOption);
			scri_que.setSearchContext(searchContext);
			scri_que.setPage(page_que);
			scri_que.setPagePerNum(dataPerPage_que);
			
			ArrayList<BoardVo> question = bd.boardSelectAll(scri_que);
			cnt_que = bd.boardTotal(scri_que);

			PageMaker pm_que = new PageMaker();
			pm_que.setScri(scri);
			pm_que.setTotalCount(cnt_que);
			pm_que.calData();
			
			request.setAttribute("dataPerPage_que", dataPerPage_que);
			request.setAttribute("pm_que", pm_que);
			request.setAttribute("question", question);
			
			HttpSession session = request.getSession();
			session.setAttribute("path", "faq");
			
			String url = request.getContextPath()+"/board/faq.do";
			session.setAttribute("url", url);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/faq.jsp");
			rd.forward(request, response);
		}
		
		else if(str.equals("/board/boardWriteAction.do")) {
			System.out.println("faq.do 들어옴");
			
			int memberNo=0;
			
			HttpSession session = request.getSession();
			BoardDao bd = new BoardDao();
			BoardVo bv = new BoardVo();
			
			String varCheck = request.getParameter("varCheck");
			
			if(session.getAttribute("path").equals("boardNoti"))
				bv.setBoardCheck("N");
			else if(session.getAttribute("path").equals("faq") && !varCheck.equals("question"))
				bv.setBoardCheck("F");
			else if(session.getAttribute("path").equals("faq") && varCheck.equals("question"))
				bv.setBoardCheck("Q");
			
			String subject = request.getParameter("subject");
			String contents = request.getParameter("contents");
			String writer = request.getParameter("writer");
			if(session.getAttribute("memberNo")==null) memberNo=0;
			memberNo = (int)session.getAttribute("memberNo");
			
			bv.setSubject(subject);
			bv.setContents(contents);
			bv.setWriter(writer);
			bv.setMemberNo(memberNo);
			
			int value = bd.boardInsert(bv);
			String path = null;
			if(value==1) {
				if(session.getAttribute("path").equals("boardNoti"))
					path = request.getContextPath()+"/board/boardNoti.do";
				else if(session.getAttribute("path").equals("faq") && !varCheck.equals("question"))
					path = request.getContextPath()+"/board/faq.do";
				
				response.sendRedirect(path);
			}
		}
		
		else if(str.equals("/board/boardContents.do")) {
			System.out.println("boardContents.do 들어옴");
			int var = 0;
			BoardDao bd = new BoardDao();
			
			int bidx = Integer.parseInt(request.getParameter("bidx"));
			if(request.getParameter("var")==null) var = 0;
			else var = Integer.parseInt(request.getParameter("var"));
			BoardVo bv = bd.boardSelectOne(bidx);
			bd.boardViewCnt(bidx);
			
			request.setAttribute("var",var);
			request.setAttribute("bv", bv);
			
			HttpSession session = request.getSession();
			String url = request.getContextPath()+"/board/boardContents.do";
			session.setAttribute("url", url);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContents.jsp");
			rd.forward(request, response);
		}
		
		else if(str.equals("/board/boardModify.do")) {
			System.out.println("boardModify.do 들어옴");
			
			BoardDao bd = new BoardDao();
			
			int bidx = Integer.parseInt(request.getParameter("bidx"));
			
			BoardVo bv = bd.boardSelectOne(bidx);
			
			request.setAttribute("bv", bv);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");
			rd.forward(request, response);
		}
		
		else if(str.equals("/board/boardModifyAction.do")) {
			System.out.println("boardModifyAction.do 들어옴");
			HttpSession session = request.getSession();
			String path = (String) session.getAttribute("path");
			int value=0;
			
			BoardDao bd = new BoardDao();
			int bidx = Integer.parseInt(request.getParameter("bidx"));
			String subject = request.getParameter("subject");
			String contents = request.getParameter("contents");
			value = bd.boardModify(subject, contents, bidx);

			request.setAttribute("bidx", bidx);
			if(value==1) {
				if(path.equals("boardNoti")){
					RequestDispatcher rd = request.getRequestDispatcher("/board/boardNoti.do");
					rd.forward(request, response);
				}
				else if(path.equals("faq")) {
					RequestDispatcher rd = request.getRequestDispatcher("/board/faq.do");
					rd.forward(request, response);
				}
			}
		}
		
		else if(str.equals("/board/boardDelete.do")) {
			System.out.println("boardDelete.do 들어옴");
			
			BoardDao bd = new BoardDao();
			int memberNo = 0;
			
			
			int bidx = Integer.parseInt(request.getParameter("bidx"));
			HttpSession session = request.getSession();
			if(request.getAttribute("memberNo")==null) memberNo = 0;
			memberNo = (int)session.getAttribute("memberNo");

			String path = (String)session.getAttribute("path");
			
			int value = bd.boardDelete(bidx, memberNo); 
			System.out.println("value="+value);
			if(value==1) {
				response.setContentType("text/html; charset=UTF-8");
			    PrintWriter out = response.getWriter();
				if(path.equals("boardNoti")){
					out.println("<script>alert('삭제 완료');location.href='"+request.getContextPath()+"/board/boardNoti.do'</script>");
				}
				else if(path.equals("faq")){
					out.println("<script>alert('삭제 완료');location.href='"+request.getContextPath()+"/board/faq.do'</script>");
				}
			    out.flush();
			    return;
			}
			
			else {
				response.setContentType("text/html; charset=UTF-8");
			    PrintWriter out = response.getWriter();
				out.println("<script>alert('삭제가 불가합니다.(다른 아이디)');history.go(-1);</script>");
			    out.flush();
			    return;
			}
		}
		
		else if(str.equals("/board/boardReply.do")) {
			System.out.println("boardReply.do 들어옴");
			BoardVo bv = new BoardVo();

			int bidx = Integer.parseInt(request.getParameter("bidx"));
			int originbidx = Integer.parseInt(request.getParameter("originbidx"));
			int depth = Integer.parseInt(request.getParameter("depth"));
			int level_ = Integer.parseInt(request.getParameter("level_"));
			String subject = request.getParameter("subject");
			bv.setBidx(bidx);
			bv.setOriginbidx(originbidx);
			bv.setDepth(depth);
			bv.setLevel_(level_);
			bv.setSubject(subject);
			
			request.setAttribute("bv", bv);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardReply.jsp");
			rd.forward(request, response);
		}
		
		else if(str.equals("/board/boardReplyAction.do")) {
			System.out.println("boardReplyAction.do 들어옴");
			
			HttpSession session = request.getSession();
			BoardDao bd = new BoardDao();
			BoardVo bv = new BoardVo();
			
			int originbidx = Integer.parseInt(request.getParameter("originbidx"));
			int depth = Integer.parseInt(request.getParameter("depth"));
			int level_ = Integer.parseInt(request.getParameter("level_"));
			String subject = request.getParameter("subject");
			String writer = request.getParameter("writer");
			String contents = request.getParameter("contents");
			int memberNo = (int)session.getAttribute("memberNo");
			
			bv.setOriginbidx(originbidx);
			bv.setDepth(depth);
			bv.setLevel_(level_);
			bv.setSubject(subject);
			bv.setContents(contents);
			bv.setWriter(writer);
			bv.setMemberNo(memberNo);
			
			int value = bd.boardReply(bv);
			
			if(value==1) {
				response.setContentType("text/html; charset=UTF-8");
			    PrintWriter out = response.getWriter();
				out.println("<script>alert('작성이 완료되었습니다.'); location.href='"+request.getContextPath()+"/board/faq.do';</script>");
			    out.flush();
			    return;
			}
			
		}
		
		else if(str.equals("/board/boardList.do")) {
			System.out.println("boardList.do 들어옴");
			HttpSession session = request.getSession();
			String path = (String) session.getAttribute("path");
			String str = null;
			if(path.equals("boardNoti")){
				str = request.getContextPath()+"/board/boardNoti.do";
			}
			else if(path.equals("faq")){
				str = request.getContextPath()+"/board/faq.do";
			}
			
			response.sendRedirect(str);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
