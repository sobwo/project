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
 		
 		if(scri.getBoardCategory().equals("noti")) str2 = " boardCheck='N'";
 		else if(scri.getBoardCategory().equals("faq")) str2 = "boardCheck='F'";
 		else if(scri.getBoardCategory().equals("question")) str2=" boardCheck='Q'";
 		
 		String sql = "SELECT * FROM(SELECT ROWNUM AS rnum, A.* FROM (select bidx,depth,level_,subject,writer,TO_CHAR(writeday,'yyyy.mm.dd') AS writeday,NVL(viewcnt,0) AS viewcnt,memberNo "
 				+ "from board WHERE "+str2+str+" order by originbidx DESC,depth asc,level_ ASC)A)B WHERE B.rnum BETWEEN ? AND ?";
 		
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
				bv.setDepth(rs.getInt("depth"));
				bv.setLevel_(rs.getInt("level_"));
				bv.setSubject(rs.getString("subject"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setViewCnt(rs.getInt("viewcnt"));
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
		
		String sql = "SELECT * FROM(SELECT bidx,subject,contents,writer,TO_CHAR(writeday,'yyyy.mm.dd') AS writeday,NVL(viewcnt,0) AS viewcnt FROM board where boardCheck='N' ORDER BY Bidx desc) WHERE ROWNUM <=7 ";

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
				bv.setViewCnt(rs.getInt("viewCnt"));
				
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
	
	public void boardViewCnt(int bidx) {
		String sql = "update board set viewcnt = NVL(viewcnt,0)+1 where bidx = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public BoardVo boardSelectOne(int bidx){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo bv = new BoardVo();
		String sql = "select * from board where bidx = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();

			while(rs.next()){
				bv.setBidx(rs.getInt("bidx"));
				bv.setOriginbidx(rs.getInt("originbidx"));
				bv.setDepth(rs.getInt("depth"));
				bv.setLevel_(rs.getInt("level_"));
				bv.setBoardCheck(rs.getString("boardCheck"));
				bv.setSubject(rs.getString("subject"));
				bv.setContents(rs.getString("contents"));
				bv.setWriter(rs.getString("writer"));
				bv.setViewCnt(rs.getInt("viewcnt"));
				bv.setWriteday(rs.getString("writeday"));			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return bv;
	}
	
	public int boardInsert(BoardVo bv) {
		int value = 0;
		PreparedStatement pstmt = null;
		
		String sql = "insert into board(bidx,originbidx,boardcheck,subject,contents,writer,memberno) values(bidx_seqNum.nextval,bidx_seqNum.nextval,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoardCheck());
			pstmt.setString(2, bv.getSubject());
			pstmt.setString(3, bv.getContents());
			pstmt.setString(4, bv.getWriter());
			pstmt.setInt(5, bv.getMemberNo());
			
			value=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	public int boardModify(String subject, String contents, int bidx){
		int value = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE board SET subject = ?,contents=?,viewcnt=viewcnt-1 where bidx=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, contents);
			pstmt.setInt(3, bidx);
			
			value =pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	public int boardDelete(int bidx, int memberNo) {
		int value = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "delete from board where bidx=? and memberNo = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bidx);
			pstmt.setInt(2, memberNo);
			
			value =pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	public int boardReply(BoardVo bv) {
		int value = 0;
		
		String sql = "UPDATE board SET depth = depth+1 WHERE originbidx = ? AND depth>0";
		String sql2 = "INSERT INTO board(bidx, originbidx, depth, level_,boardCheck, subject, contents, writer, memberNo)"
				+ "VALUES(bidx_seqNum.nextval,?,?,?,'Q',?,?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.setInt(2, bv.getDepth()+1);
			pstmt.setInt(3, bv.getLevel_()+1);
			pstmt.setString(4,bv.getSubject());
			pstmt.setString(5, bv.getContents());
			pstmt.setString(6, bv.getWriter());
			pstmt.setInt(7, bv.getMemberNo());
			value = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	
	public int countChar(String str, char c) {
		return str.length() - str.replace(String.valueOf(c), "").length();
	}
}
