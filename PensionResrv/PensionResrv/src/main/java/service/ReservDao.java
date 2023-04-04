package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		String sql = "insert into reservation(reservNo,checkIn,checkOut,adultNum,childNum,babyNum,optionNum,totalPay,extraPhone,arriveTime,pickup,request,roomNo,memberNo) values(reservNo_seqNum.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			pstmt.setString(10, rv.getPickup());
			pstmt.setString(11, rv.getRequest());
			pstmt.setInt(12, rv.getRoomNo());
			pstmt.setInt(13, rv.getMemberNo());
			
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
	
	public ArrayList<ReservVo> selectReserv(int memberNo) {
		ArrayList<ReservVo> rlist = new ArrayList<>();
		ReservVo rv = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM reservation a, member b, room c WHERE a.memberNo = b.memberNo AND a.roomNo = c.ROOMNO AND b.memberNo = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,memberNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rv = new ReservVo();
				rv.setReservNo(rs.getInt("reservNo"));
				rv.setCheckIn(rs.getString("checkIn"));
				rv.setCheckOut(rs.getString("checkOut"));
				rv.setAdultNum(rs.getInt("adultNum"));
				rv.setChildNum(rs.getInt("childNum"));
				rv.setBabyNum(rs.getInt("babyNum"));
				rv.setOptionNum(rs.getInt("optionNum"));
				rv.setTotalPay(rs.getInt("totalPay"));
				rv.setRoomName(rs.getString("roomName"));
				
				rlist.add(rv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rlist;
	}
	
	public int reservCancel(int reservNo) {
		int value=0;
		PreparedStatement pstmt=null;
		String sql = "delete from reservation where reservNo = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservNo);
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
