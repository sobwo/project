package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dbconn.Dbconn;
import domain.MemberVo;
import domain.SearchVo;

public class SearchDao {
	private Connection conn;
	
	public SearchDao() {
		Dbconn dbconn = new Dbconn();
		this.conn = dbconn.getConnection();
	}
	
	public ArrayList<MemberVo> searchId(SearchVo sv){
		ArrayList<MemberVo> value = new ArrayList<>();
		MemberVo mv = null;
		String str = null;
		String sql_val=null;
		
		if(sv.getSearchMeasure().equals("phone")) {
			str = "memberPhone = ?";
			sql_val = sv.getMemberPhone();
		}
		
		else if(sv.getSearchMeasure().equals("email")) {
			str = "memberEmail = ?";
			sql_val = sv.getMemberEmail();
		}
		
		String sql = "select memberId, TO_CHAR(joinDate,'yyyy-mm-dd') AS joinDate from member where memberName = ? and "+str;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sv.getMemberName());
			pstmt.setString(2, sql_val);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mv = new MemberVo();
				mv.setMemberId(rs.getString("memberId"));
				mv.setJoinDate(rs.getString("joinDate"));
				value.add(mv);
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
	
	public int searchPwCheck(SearchVo sv){
		int value=0;
		String str = null;
		String sql_val=null;
		
		if(sv.getSearchMeasure().equals("phone")) {
			str = "memberPhone = ?";
			sql_val = sv.getMemberPhone();
		}
		
		else if(sv.getSearchMeasure().equals("email")) {
			str = "memberEmail = ?";
			sql_val = sv.getMemberEmail();
		}
		
		String sql = "select count(*) as cnt from member where memberId=? and memberName = ? and "+str;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sv.getMemberId());
			pstmt.setString(2, sv.getMemberName());
			
			
			pstmt.setString(3, sql_val);
			
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
	
	public int searchPwReset(String memberId, String memberPw) {
		int value = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "update member set memberPw = ? where memberId = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberPw);
			pstmt.setString(2, memberId);
			
			
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	
}
