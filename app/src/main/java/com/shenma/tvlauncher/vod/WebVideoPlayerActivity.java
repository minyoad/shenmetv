package com.shenma.tvlauncher.vod;

import android.content.Intent;
import android.os.Bundle;

import com.mybacc.xwalkvideoplayer.XwalkWebViewActivity;
import com.shenma.tvlauncher.vod.domain.VideoInfo;

import java.util.ArrayList;

public class WebVideoPlayerActivity extends XwalkWebViewActivity {

    private ArrayList<VideoInfo> videoInfoList;
    private String vodname;
    private String albumPic;
    private String vodtype;
    private String videoId;
    private String sourceId;
    private int playIndex;
    private int mLastPos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initData();

        VideoInfo videoInfo=videoInfoList.get(playIndex);
        play(videoInfo.url,vodname, videoInfo.title);

    }

    private void initData(){
        Intent intent = getIntent();
        videoInfoList = new ArrayList<VideoInfo>();
        videoInfoList = intent.getParcelableArrayListExtra("videoinfo");
        albumPic = intent.getStringExtra("albumPic");
        vodtype = intent.getStringExtra("vodtype");
        videoId = intent.getStringExtra("videoId");
        vodname = intent.getStringExtra("vodname");
//        domain = intent.getStringExtra("domain");
//        nextlink = intent.getStringExtra("nextlink");
//        vodstate = intent.getStringExtra("vodstate");
        sourceId = intent.getStringExtra("sourceId");
        playIndex = intent.getIntExtra("playIndex",0);
        mLastPos = intent.getIntExtra("collectionTime",0);
    }


    @Override
    public void onPlayingEventChanged(String event){
        switch (event){
//            case "ended":
//            case "progress":
//            case "canplay":
//            case "playing":
            case "loadeddata":{
                //seek to lastplayed


            }
        }

    }

    @Override
    public void onProgressUpdated(double position){


    }

    @Override
    public void onPlayingProperties(String propertiesJson){

    }

}
