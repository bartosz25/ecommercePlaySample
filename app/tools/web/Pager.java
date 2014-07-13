package tools.web;

public class Pager {

	private int perPage;
	private int page;
	
	public Pager(int page, int perPage) {
		if (page == 0) {
			page = 1;
		}
		this.page = page;
		this.perPage = perPage;
	}
	
	public int getSqlStart() {
		return (page-1)*getSqlLimit();
	}
	
	public int getSqlLimit() {
		return this.perPage;
	}
	
	public boolean applyLimit() {
		return this.page > 0 && this.perPage > 0;
	}
	
	
}
