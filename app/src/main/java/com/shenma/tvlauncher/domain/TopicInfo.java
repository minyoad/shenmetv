package com.shenma.tvlauncher.domain;

import com.google.gson.annotations.SerializedName;
import com.shenma.tvlauncher.utils.Constant;

/**
 * 专题详情
 * @author joychang
 *
 */
public class TopicInfo {

	@SerializedName("special_id")
	private String id;
	@SerializedName("special_name")

	private String ztname;
	@SerializedName("special_logo")

	private String bigpic;
	@SerializedName("special_banner")

	private String smallpic;
	@SerializedName("special_content")

	private String ztdescribe;
	private String linkurl;
	private String videotype;
	@SerializedName("special_status")

	private String status;
	private String expiretime;
	private String tjwei;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZtname() {
		return ztname;
	}

	public void setZtname(String ztname) {
		this.ztname = ztname;
	}

	public String getBigpic() {
//		return bigpic;
		if(bigpic!=null &&bigpic.startsWith("http"))
			return bigpic;
		else return Constant.BASE_URL+bigpic;
	}

	public void setBigpic(String bigpic) {
		this.bigpic = bigpic;
	}

	public String getSmallpic() {
		if(smallpic!=null &&smallpic.startsWith("http"))
		return smallpic;
		else return Constant.BASE_URL+smallpic;
	}

	public void setSmallpic(String smallpic) {
		this.smallpic = smallpic;
	}

	public String getZtdescribe() {
		return ztdescribe;
	}

	public void setZtdescribe(String ztdescribe) {
		this.ztdescribe = ztdescribe;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public String getVideotype() {
		return videotype;
	}

	public void setVideotype(String videotype) {
		this.videotype = videotype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExpiretime() {
		return expiretime;
	}

	public void setExpiretime(String expiretime) {
		this.expiretime = expiretime;
	}

	public String getTjwei() {
		return tjwei;
	}

	public void setTjwei(String tjwei) {
		this.tjwei = tjwei;
	}

	@Override
	public String toString() {
		return "TopicInfo [id=" + id + ", ztname=" + ztname + ", bigpic="
				+ bigpic + ", smallpic=" + smallpic + ", ztdescribe="
				+ ztdescribe + ", linkurl=" + linkurl + ", videotype="
				+ videotype + ", status=" + status + ", expiretime="
				+ expiretime + ", tjwei=" + tjwei + "]";
	}
}