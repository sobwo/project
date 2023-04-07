package domain;

public class RoomVo {
	private int roomNo,sqft;
	private String roomName,capacity,numOfRoom;
	
	public String getNumOfRoom() {
		return numOfRoom;
	}
	public void setNumOfRoom(String numOfRoom) {
		this.numOfRoom = numOfRoom;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public int getSqft() {
		return sqft;
	}
	public void setSqft(int sqft) {
		this.sqft = sqft;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
}
