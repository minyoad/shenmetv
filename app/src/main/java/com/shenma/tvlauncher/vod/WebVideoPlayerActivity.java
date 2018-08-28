package com.shenma.tvlauncher.vod;

import android.content.Intent;
import android.os.Bundle;

import com.mybacc.xwalkvideoplayer.XwalkWebViewActivity;
import com.shenma.tvlauncher.vod.dao.VodDao;
import com.shenma.tvlauncher.vod.db.Album;
import com.shenma.tvlauncher.vod.domain.VideoInfo;
import com.shenma.tvlauncher.vod.domain.VideoList;

import java.util.ArrayList;
import java.util.List;

public class WebVideoPlayerActivity extends XwalkWebViewActivity {

    private ArrayList<VideoInfo> videoInfoList;
    private String vodname;
    private String albumPic;
    private String vodtype;
    private String videoId;
    private String sourceId;
    private int playIndex;
    private int mLastPos;

    private VodDao dao;
    private Album album;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao=new VodDao(this);

        initData();

        VideoInfo videoInfo=videoInfoList.get(playIndex);


        String url= VideoList.getProxiedUrl(videoInfo.url);
        play(url,vodname, videoInfo.title);

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

        List<Album> albums=dao.queryAlbumById(videoId,2);
        if (albums.size()>0){
            album=albums.get(0);
            mLastPos=album.getCollectionTime();
        }
        else {

            album = new Album();
            album.setAlbumId(videoId);
            album.setAlbumSourceType(sourceId);
//        album.setCollectionTime(pos);
            album.setPlayIndex(playIndex);
            album.setAlbumPic(albumPic);
            album.setAlbumType(vodtype);
            album.setAlbumTitle(vodname);
            album.setAlbumState("");
            album.setNextLink("");
            album.setTypeId(2);//记录

        }


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
                if(mLastPos>0){
                    seekTo(mLastPos);
                }

            }
        }

    }


    private void updateHistory(int pos){

//        album = new Album();
//        album.setAlbumId(videoId);
//        album.setAlbumSourceType(sourceId);
        album.setCollectionTime(pos);
//        album.setPlayIndex(playIndex);
//        album.setAlbumPic(albumPic);
//        album.setAlbumType(vodtype);
//        album.setAlbumTitle(vodname);
//        album.setAlbumState("");
//        album.setNextLink("");
        album.setTypeId(2);//记录
        dao.addAlbums(album);
    }


    @Override
    public void onProgressUpdated(double position){

        mLastPos=(int)position;
        if (mLastPos % 5 ==0){

            updateHistory(mLastPos);

        }

    }

    @Override
    public void onPlayingProperties(String propertiesJson){

    }

}
