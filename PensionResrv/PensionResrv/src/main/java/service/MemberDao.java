package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbconn.Dbconn;
import domain.MemberVo;

public class MemberDao {
	private Connection conn;
	
	public MemberDao(){
		Dbconn dbconn = new Dbconn();
		this.conn = dbconn.getConnection();
	}
	
	public int memberInsert(MemberVo mv) {
		PreparedStatement pstmt = null;
		int value=0;
		
		String sql = "insert into member(memberNo,memberId,memberPw,memberName,memberBirth,memberPhone,memberEmail,joinYn) values(memberNo_seqNum.nextval,?,?,?,?,?,?,'Y')";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemberId());
			pstmt.setString(2, mv.getMemberPw());
			pstmt.setString(3, mv.getMemberName());
			pstmt.setString(4, mv.getMemberBirth());
			pstmt.setString(5, mv.getMemberPhone());
			pstmt.setString(6, mv.getMemberEmail());
			
			value = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
 	public int memberIdCheck(String memberId){
		int value=0;
		String sql="select count(*) as cnt from member where memberid=? and delYn='N'";
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {   //커서가 이동을해서 다음값이 존재하면 참->진행할수 있다
				value = rs.getInt("cnt");
			}			
		} catch (SQLException e) {		
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}				
		return value;
	}

	public MemberVo memberLogin(String memberId, String memberPw) {
		MemberVo mv = null;
		String sql = "select * from member where memberId =? and memberPw = ? and delYn='N' and joinYn='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mv = new MemberVo();
				mv.setMemberNo(rs.getInt("memberNo"));
				mv.setMemberId(rs.getString("memberId"));
				mv.setMemberPw(rs.getString("memberPw"));
				mv.setMemberName(rs.getString("memberName"));
				mv.setMemberBirth(rs.getString("memberBirth"));
				mv.setMemberPhone(rs.getString("memberPhone"));
				mv.setMemberEmail(rs.getString("memberEmail"));
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
		return mv;
	}
	
	public MemberVo selectInfo(int memberNo){
		MemberVo mv = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member where memberNo=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mv = new MemberVo();
				mv.setMemberNo(rs.getInt("memberNo"));
				mv.setMemberId(rs.getString("memberId"));
				mv.setMemberPw(rs.getString("memberPw"));
				mv.setMemberName(rs.getString("memberName"));
				mv.setMemberBirth(rs.getString("memberBirth"));
				mv.setMemberPhone(rs.getString("memberPhone"));
				mv.setMemberEmail(rs.getString("memberEmail"));
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
		return mv;
	}
	
	public int modifyInfo(MemberVo mv) {
		int value = 0;
		PreparedStatement pstmt = null;
		
		String sql = "update member set memberPw=?,membername=?,memberBirth=?,memberPhone=?,memberEmail=? where memberNo=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemberPw());
			pstmt.setString(2, mv.getMemberName());
			pstmt.setString(3, mv.getMemberBirth());
			pstmt.setString(4, mv.getMemberPhone());
			pstmt.setString(5, mv.getMemberEmail());
			pstmt.setInt(6, mv.getMemberNo());
			
			value = pstmt.executeUpdate();
			
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
	
	public int quitMember(int memberNo) {
		int value = 0;
		
		PreparedStatement pstmt = null;
		String sql = "update member set delYn='Y' where memberNo=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
	}
	
	public int OffrineMemberInsert(MemberVo mv) {
		int value = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into member(memberNo,memberName,memberBirth,memberPhone,memberEmail) values(memberNo_seqNum.nextval,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemberName());
			pstmt.setString(2, mv.getMemberBirth());
			pstmt.setString(3, mv.getMemberPhone());
			pstmt.setString(4, mv.getMemberEmail());
			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}
	
	public int selectMemberNo(String memberName) {
		int value = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select memberNo from member where memberName=? and joinYn='N'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				value = rs.getInt("memberNo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
}
