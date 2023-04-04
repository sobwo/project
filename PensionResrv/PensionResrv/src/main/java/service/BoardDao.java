package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.Dbconn;
import domain.BoardVo;
import domain.SearchCriteria;

public class BoardDao {
	private Connection conn;
	
	public BoardDao(){
		Dbconn dbconn = new Dbconn();
		this.conn = dbconn.getConnection();
	}
	
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri) {
 		ArrayList<BoardVo> blist = new ArrayList<BoardVo>();
 		String str = null;
 		String str2 = null;
 		if(scri.getSearchOption().equals("제목만")) str = " and subject like ?";
 		else if(scri.getSearchOption().equals("제목과내용")) str = " and subject like ? or contents like ?";
 		else if(scri.getSearchOption().equals("글작성자")) str = " and writer like ?";
 		
 		if(scri.getBoardCategory().equals("noti")) str2 = "boardCheck='N'";
 		else if(scri.getBoardCategory().equals("faq")) str2 = "boardCheck='F'";
 		else if(scri.getBoardCategory().equals("question")) str2="boardCheck='Q'";
 		
 		String sql = "SELECT * FROM(SELECT ROWNUM AS rnum, A.* FROM (select bidx,subject,writer,TO_CHAR(writeday,'yyyy.mm.dd') AS writeday,NVL(viewcnt,0) AS viewcnt,memberNo "
 				+ "from board WHERE "+str2+str+")A)B WHERE B.rnum BETWEEN ? AND ?";
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		try {
			if(countChar(str, '?')==1) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+scri.getSearchContext()+"%");
				pstmt.setInt(2, (scri.getPage()-1)*scri.getPagePerNum()+1);
				pstmt.setInt(3, scri.getPage()*scri.getPagePerNum());
				}
			else if(countChar(str, '?')==2) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+scri.getSearchContext()+"%");
				pstmt.setString(2, "%"+scri.getSearchContext()+"%");
				pstmt.setInt(3, (scri.getPage()-1)*scri.getPagePerNum()+1);
				pstmt.setInt(4, scri.getPage()*scri.getPagePerNum());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo bv = new BoardVo();	
				bv.setBidx(rs.getInt("bidx"));
//				bv.setDepth(rs.getInt("depth"));
//				bv.setLevel_(rs.getInt("level_"));
				bv.setSubject(rs.getString("subject"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setViewCnt(rs.getString("viewcnt"));
				bv.setMemberNo(rs.getInt("memberNo"));
				
				
				blist.add(bv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					rs.close();
					pstmt.close();
//					conn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

 		return blist;
 	}
	
	public int boardTotal(SearchCriteria scri) {
		int value = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String str = null;
		String str2 = null;
		if(scri.getSearchOption().equals("제목만")) str = " and subject like ?";
 		else if(scri.getSearchOption().equals("제목과내용")) str = " and subject like ? or contents like ?";
 		else if(scri.getSearchOption().equals("글작성자")) str = " and writer like ?";
		
		if(scri.getBoardCategory().equals("noti")) str2 = "boardCheck='N'";
 		else if(scri.getBoardCategory().equals("faq")) str2 = "boardCheck='F'";
 		else if(scri.getBoardCategory().equals("question")) str2="boardCheck='Q'";
		
		String sql = "SELECT count(*) as cnt from board where "+str2+str;

		try {
			pstmt = conn.prepareStatement(sql);
			if(countChar(str, '?')==1) 
				pstmt.setString(1, "%"+scri.getSearchContext()+"%");
			else if(countChar(str, '?')==2) {
				pstmt.setString(1, "%"+scri.getSearchContext()+"%");
				pstmt.setString(2, "%"+scri.getSearchContext()+"%");
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				value = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return value;
	}
	
	public ArrayList<BoardVo> reserv_main_board(){
		ArrayList<BoardVo> blist = new ArrayList<>();
		BoardVo bv = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM(SELECT bidx,subject,contents,writer,TO_CHAR(writeday,'yyyy.mm.dd') AS writeday,NVL(viewcnt,0) AS viewcnt FROM board ORDER BY Bidx desc) WHERE ROWNUM <=7";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bv = new BoardVo();
				bv.setBidx(rs.getInt("bidx"));
				bv.setSubject(rs.getString("subject"));
				bv.setContents(rs.getString("contents"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setViewCnt(rs.getString("viewCnt"));
				
				blist.add(bv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return blist;
	}
	
	public int countChar(String str, char c) {
		return str.length() - str.replace(String.valueOf(c), "").length();
	}
}
