package com.shenma.tvlauncher.utils;

import java.io.File;

import android.os.Environment;

/**
 * @Description 配置信息
 * @author joychang
 *
 */
public class Constant {

	public static String BASE_URL="http://ssvip.mybacc.com/";

	public static String VIP_PROXY_URL="https://beaacc.com/api.php?url=";
	public static String UPDATE_URL = BASE_URL+"index.php/version/chkupdate.json?";
	public static String STARTINFO_URL = BASE_URL+"index.php/user/addstartinfo.json?";
	public static String RECOMMEND_URL = BASE_URL+"/index.php?s=plus-api-json-tv-1-limit-6";
//	public static String RECOMMEND_URL = BASE_URL+"index.php/tjinfo/gettjinfo.json?type=推荐";
	public static String HEARD_URL = BASE_URL;
	public static String RECAPPS = BASE_URL+"index.php/tjinfo/gettjapp.json?";
	public static String MOVIE_URL = BASE_URL+"index.php/tjinfo/getclassify.json?";
	public static String UPLOAD_URL = BASE_URL+"index.php/user/downinfo.json?";
	public static String USERLOGIN = BASE_URL+"index.php/user/login.json";
	public static String USERREG = BASE_URL+"index.php/user/reg.json";
	
	//当前程序公用的文件路径
	public static String PUBLIC_DIR = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"ShenMa"+File.separator;
	
	//当前程序的SharedPreferences统一名称
	public final static String SHENMASP = "shenma_sp";
	public final static int TYPE_ZJ = 0;
	public final static int TYPE_SC = 1;
	public final static int TYPE_LS = 2;
	
	//回看
	public final static String WEPOWER_URL = "http://huibo.lsott.com";
	
	//直播
	public static final String ENTER_URL = "http://wlhd.lsott.com/api/index.php?mac=";
	public static final String ENTER_URL_MAC = "http://wlhd.lsott.com/api/index.php?mac=555666";
	
	//影视
//	public final static String TVPLAY = "http://ssvip.mybacc.com/index.php?s=plus-api-json-cid-2";
//	public final static String COMIC = "http://ssvip.mybacc.com/index.php?s=plus-api-json-cid-3";
//	public final static String TVSHOW = "http://ssvip.mybacc.com/index.php?s=plus-api-json-cid-4";
//	public final static String MOVIE = "http://ssvip.mybacc.com/index.php?s=plus-api-json-cid-1";
//	public final static String TEACH = "http://aps.lsott.com/app/app.php?nozzle=list&class=teach";
//	public final static String DOCUMENTARY = "http://aps.lsott.com/app/app.php?nozzle=list&class=documentary";
	public final static String VODFILTER = BASE_URL+"/index.php?s=plus-api-category";
//	public final static String VODFILTER_H123 = "http://aps.lsott.com/app/";
	public final static String VOD_DETAIL=BASE_URL+"/index.php?s=plus-api-json-vodids-";
	public final static String VOD_LIST=BASE_URL+"/index.php?s=plus-api-json-cid-";
	public final static String SEARCH_URL=BASE_URL+"/index.php?s=plus-api-json-wd-";



	//专题
	public final static String TOPIC_URL = BASE_URL+"/index.php?s=plus-api-special-limit-6";
	public final static String TOPIC_HEAD_URL = BASE_URL+"/index.php?s=plus-api-json-special-";
	//搜索
	public final static String VOD_TYPE_ALL = BASE_URL+"index.php/seachinfo/seachsp.json?zm=";
	public final static String VOD_TYPE = "http://aps.lsott.com/app/app.php?nozzle=character&zm=";
	public final static String VOD_TYPE_HAO123 = "http://aps.lsott.com/app/?nozzle=character&zm=";
	
	public final static String URL_HEAD = "http://aps.lsott.com/app/parse.php?";
	public final static String POST_PHP = "http://aps.lsott.com/app/post.php?";
	
	public final static String AK = "HuRTrmYh7fieGyeoAumGj28F";//ak
	public final static String SK = "KNNU53DTetf7RNMlI9TNSbI2K1trS6fL";//前16位
	
	public final static String TVLIVE = "TVLIVE"; 
	public final static String TVLIVE_DIY = "TVLIVE_DIY"; 
	
	//电视频道
	public final static String TVSTATIONS = "http://smtvzm.com/index.php/channel/getsyschannel.json";
	public final static String WALLPAPER = BASE_URL+"index.php/skin/getskininfo.json";
	
//	private final static String TVPALY = "http://api.lsott.com/app/?nozzle=list&class=tvplay";
//	private final static String COMIC = "http://api.lsott.com/app/?nozzle=list&class=comic";
//	private final static String TVSHOW = "http://api.lsott.com/app/?nozzle=list&class=tvshow";
//	private final static String MOVIE = "http://api.lsott.com/app/app.php?nozzle=list&class=movie";
//	private final static String TEACH = "http://api.lsott.com/app/app.php?nozzle=list&class=teach";
//	private final static String DOCUMENTARY = "http://api.lsott.com/app/app.php?nozzle=list&class=documentary";
//	private final static String VODFILTER = "http://api.lsott.com/app/app.php";
//	private final static String VODFILTER_H123 = "http://api.lsott.com/app/";

}
