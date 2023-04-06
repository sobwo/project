package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.Dbconn;
import domain.RoomPriceVo;

public class RoomDao {
private Connection conn;
	
	public RoomDao(){
		Dbconn dbconn = new Dbconn();
		this.conn = dbconn.getConnection();
	}
	public ArrayList<RoomPriceVo> selectAll(String checkIn,String checkOut){
		ArrayList<RoomPriceVo> rlist = new ArrayList<>();
		RoomPriceVo rmv = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql = "select a.roomNo, a.roomName, a.price, a.capacity, a.sqft, a.numOfRoom, b.reservYn "
				+ "from room a, roomPrice b where a.roomno=b.roomno AND b.reservYn='Y' and b.date_ between ? and ? "
				+ "GROUP BY a.roomNo, a.roomName, a.price, a.capacity, a.sqft, a.numOfRoom, b.reservYn";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, checkIn);
			pstmt.setString(2, checkOut);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rmv = new RoomPriceVo();
				rmv.setRoomNo(rs.getInt("roomNo"));
				rmv.setRoomName(rs.getString("roomName"));
				rmv.setPrice(rs.getInt("price"));
				rmv.setCapacity(rs.getString("capacity"));
				rmv.setSqft(rs.getInt("sqft"));
				rmv.setNumOfRoom(rs.getString("numOfRoom"));
				rmv.setReservYn(rs.getString("reservYn"));
				
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
		
		String sql = "select a.roomNo,a.roomName,b.ReservYn,to_char(b.date_,'yyyymmdd') as date_, b.pricePerDay from room a, roomPrice b where a.roomno = b.roomno order by a.roomName asc";
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
	
	public int roomChkInsert(String checkIn,String checkOut) {
		int value = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE roomPrice SET reservYn = 'I' WHERE roomNo IN (SELECT r.roomNo FROM reservation r WHERE r.checkIn = ?) AND DATE_ >= TO_DATE(?, 'YYYY-MM-DD') AND DATE_ < TO_DATE(?, 'YYYY-MM-DD')";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, checkIn);
			pstmt.setString(2, checkIn);
			pstmt.setString(3, checkOut);
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

}
