package domain;

public class RoomPriceVo extends RoomVo{
	private String date_,reservYn;
	private int pricePerDay;
	public String getReservYn() {
		return reservYn;
	}
	public void setReservYn(String reservYn) {
		this.reservYn = reservYn;
	}
	public String getDate_() {
		return date_;
	}
	public void setDate_(String date_) {
		this.date_ = date_;
	}
	public int getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	
}
