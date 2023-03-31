package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.Dbconn;
import domain.RoomVo;

public class RoomDao {
private Connection conn;
	
	public RoomDao(){
		Dbconn dbconn = new Dbconn();
		this.conn = dbconn.getConnection();
	}
	public ArrayList<RoomVo> selectAll(){
		ArrayList<RoomVo> rlist = new ArrayList<>();
		RoomVo rmv = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql = "select * from room";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rmv = new RoomVo();
				rmv.setRoomNo(rs.getInt("roomNo"));
				rmv.setRoomName(rs.getString("roomName"));
				rmv.setPrice(rs.getInt("price"));
				rmv.setCapacity(rs.getString("capacity"));
				rmv.setSqft(rs.getInt("sqft"));
				rmv.setReservYn(rs.getString("reservYn").charAt(0));
				rmv.setNumOfRoom(rs.getString("numOfRoom"));
				
				rlist.add(rmv);
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
		return rlist;
	}

}