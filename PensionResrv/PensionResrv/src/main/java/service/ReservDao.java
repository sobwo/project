package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbconn.Dbconn;


public class ReservDao {
private Connection conn;
	
	public ReservDao(){
		Dbconn dbconn = new Dbconn();
		this.conn = dbconn.getConnection();
	}
	
	public int selectRoomNo(String select_roomName) {
		int value = 0; 
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql = "select roomNo from room where roomName=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, select_roomName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				value = rs.getInt("roomNo");
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
}
