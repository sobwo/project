package domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PageMaker {
	//페이지 네비게이션을 사용하기 위한 기능이 담긴 클래스
	private int displayPageNum = 5;
	private int startPage;
	private int endPage;
	private int totalCount;
	
	private boolean prev;
	private boolean next;
	private SearchCriteria scri;
	
	public SearchCriteria getScri() {
		return scri;
	}

	public void setScri(SearchCriteria scri) {
		this.scri = scri;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}
	
	public void calData() {
		//시작페이지 번호
		//끝페이지 번호
		endPage = (int)(Math.ceil(scri.getPage()/(double)displayPageNum)*displayPageNum);
		startPage = (endPage - displayPageNum)+1;
		
		//실제페이지
		int tempEndPage = (int)(Math.ceil(totalCount/(double)scri.getPagePerNum()));
		
		if(endPage>tempEndPage) endPage = tempEndPage;
		
		prev = (startPage == 1 ? false : true);
		next = (endPage*scri.getPagePerNum()>= totalCount ? false : true);	
	}
	
	public String encoding(String keyword) {
		String str= keyword;
		try {
			URLEncoder.encode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
}
