package com.shenma.tvlauncher.vod.domain;

import java.io.Serializable;
import java.util.List;

public class VodDetailTypeInfo implements Serializable{

	public String status;

	public Page page;
	public List<CategoryData> list;
	public List<VideoDetailInfo> data;

	public static class Page{
		public int pageindex;
		public int pagecount;
		public int pagesize;
		public int recordcount;
	}

	public static class CategoryData{
		public String list_id;
		public String list_name;
	}

//	private int pageindex;
//	private int videonum;
//	private int totalpage;
//	private List<VodDataInfo> data;
//
	public int getPageindex() {
		return page.pageindex;
	}

//
//
//	public void setPageindex(int pageindex) {
//		this.pageindex = pageindex;
//	}
//
//
//
	public int getVideonum() {
		return page.recordcount;
	}
//
//
//
//	public void setVideonum(int videonum) {
//		this.videonum = videonum;
//	}
//
//
//
	public int getTotalpage() {
		return page.pagecount;
	}
//
//
//
//	public void setTotalpage(int totalpage) {
//		this.totalpage = totalpage;
//	}
//
//
//
	public List<VideoDetailInfo> getData() {
		return data;
	}
//
//
//
//	public void setData(List<VodDataInfo> data) {
//		this.data = data;
//	}
//
//
//
//	/* (non-Javadoc)
//	 * @see java.lang.Object#toString()
//	 */
//	@Override
//	public String toString() {
//		return "VodTypeInfo [pageindex=" + pageindex + ", videonum=" + videonum
//				+ ", totalpage=" + totalpage + ", data=" + data + "]";
//	}
	
}

