package domain;

public class SelectRoom extends ReservVo{
	private int select_roomNo,select_price,select_totalPrice;
	private String select_roomName;
	
	public int getSelect_totalPrice() {
		return select_totalPrice;
	}
	public void setSelect_totalPrice(int select_price, int option_value) {
		this.select_totalPrice = select_price+(option_value*20000);
	}
	public int getSelect_roomNo() {
		return select_roomNo;
	}
	public void setSelect_roomNo(int select_roomNo) {
		this.select_roomNo = select_roomNo;
	}
	public int getSelect_price() {
		return select_price;
	}
	public void setSelect_price(int select_price) {
		this.select_price = select_price;
	}
	public String getSelect_roomName() {
		return select_roomName;
	}
	public void setSelect_roomName(String select_roomName) {
		this.select_roomName = select_roomName;
	}
	

}
