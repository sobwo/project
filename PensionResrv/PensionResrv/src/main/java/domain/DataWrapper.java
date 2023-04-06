package domain;

import java.util.ArrayList;

public class DataWrapper {
    private ArrayList<RoomPriceVo> rList;
	private ArrayList<BoardVo> bList;

    public DataWrapper(ArrayList<RoomPriceVo> rList, ArrayList<BoardVo> bList) {
        this.rList = rList;
        this.bList = bList;
    }
    public ArrayList<RoomPriceVo> getrList() {
		return rList;
	}

	public void setrList(ArrayList<RoomPriceVo> rList) {
		this.rList = rList;
	}

	public ArrayList<BoardVo> getbList() {
		return bList;
	}

	public void setbList(ArrayList<BoardVo> bList) {
		this.bList = bList;
	}
	
}
