package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.Dbconn;
import domain.RoomPriceVo;
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
				rmv.setReservYn(rs.getString("reservYn"));
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return rlist;
	}
	
	public ArrayList<RoomPriceVo> roomCalendar(){
		ArrayList<RoomPriceVo> rpvlist = new ArrayList<>();
		RoomPriceVo rpv = null;
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql = "select a.roomNo,a.roomName,a.ReservYn,to_char(b.date_,'yyyymmdd') as date_, b.pricePerDay from room a, roomPrice b where a.roomno = b.roomno and b.date_ >= TRUNC(sysdate, 'MM') AND b.date_ < ADD_MONTHS(TRUNC(sysdate, 'MM'), 1)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				rpv = new RoomPriceVo();
				rpv.setRoomNo(rs.getInt("roomNo"));
				rpv.setRoomName(rs.getString("roomName"));
				rpv.setReservYn(rs.getString("reservYn"));
				rpv.setDate_(rs.getString("date_"));
				rpv.setPricePerDay(rs.getInt("pricePerDay"));
				rpvlist.add(rpv);
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
		return rpvlist;
	}

}
