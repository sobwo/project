package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconn.Dbconn;
import domain.ReservVo;


public class ReservDao {
private Connection conn;
	
	public ReservDao(){
		Dbconn dbconn = new Dbconn();
		this.conn = dbconn.getConnection();
	}
	
	public int selectRoomNo(String roomName) {
		int value = 0; 
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql = "select roomNo from room where roomName=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				value = rs.getInt("roomNo");
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
	
	public int reservInsert(ReservVo rv) {
		int value =0;
		PreparedStatement pstmt = null;
		String sql = "insert into reservation(reservNo,checkIn,checkOut,adultNum,childNum,babyNum,optionNum,totalPay,extraPhone,arriveTime,request,roomNo) values(reservNo_seqNum.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rv.getCheckIn());
			pstmt.setString(2, rv.getCheckOut());
			pstmt.setInt(3, rv.getAdultNum());
			pstmt.setInt(4, rv.getChildNum());
			pstmt.setInt(5, rv.getBabyNum());
			pstmt.setInt(6, rv.getOptionNum());
			pstmt.setInt(7, rv.getTotalPay());
			pstmt.setString(8,rv.getExtraPhone());
			pstmt.setString(9, rv.getArriveTime());
			pstmt.setString(10, rv.getRequest());
			pstmt.setInt(11, rv.getRoomNo());
			
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
