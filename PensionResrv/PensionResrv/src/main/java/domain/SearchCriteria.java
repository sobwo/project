package domain;

public class SearchCriteria extends Criteria{
	private String searchOption;
	private String searchContext;
	private String boardCategory;
	
	public String getBoardCategory() {
		return boardCategory;
	}
	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}
	public String getSearchOption() {
		return searchOption;
	}
	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}
	public String getSearchContext() {
		return searchContext;
	}
	public void setSearchContext(String searchContext) {
		this.searchContext = searchContext;
	}
}
