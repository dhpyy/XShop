package cn.swust.xshop.utils;

import java.util.List;

/**
 * 分页类的封装
 *
 */
public class PageBean<T> {
	private int page;			// 当前页码
	private int limit;			// 每页的记录个数
	private int totalCount; 	// 记录的总数
	private int totalPage; 		// 记录的页数
	private List<T> list; 		// 当前页显示记录的集合

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
