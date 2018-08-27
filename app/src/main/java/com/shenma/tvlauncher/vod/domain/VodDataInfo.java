package com.shenma.tvlauncher.vod.domain;

import com.google.gson.annotations.SerializedName;
import com.shenma.tvlauncher.utils.Constant;

public class VodDataInfo {

    @SerializedName("vod_id")
    private long id;
    @SerializedName("vod_name")
    private String title;//节目名称
    @SerializedName("vod_url")
    private String nextlink;//节目详细地址
    @SerializedName("vod_pic")
    private String pic;//节目图片地址
    @SerializedName("vod_state")
    private String state;//节目状态

    public long getId() {
        return id;
    }

    @SerializedName("list_name")

    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNextlink() {
        return nextlink;
    }

    public void setNextlink(String nextlink) {
        this.nextlink = nextlink;
    }

    public String getPic() {
        if(pic.startsWith("http://img.maccms.com/pic.php")){
            return pic.replace("http://img.maccms.com/pic.php",Constant.BASE_URL +"/pic.php");
        }
        else if (pic.startsWith("http")) {
            return pic;
        } else {
            return Constant.BASE_URL + pic;
        }
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "VodDataInfo [title=" + title + ", nextlink=" + nextlink
                + ", pic=" + pic + ", state=" + state + ", type=" + type
                + "]";
    }

}
