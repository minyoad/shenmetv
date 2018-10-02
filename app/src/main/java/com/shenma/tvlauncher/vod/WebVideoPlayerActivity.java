package com.shenma.tvlauncher.vod;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mybacc.xwalkvideoplayer.XwalkWebViewActivity;
import com.shenma.tvlauncher.vod.dao.VodDao;
import com.shenma.tvlauncher.vod.db.Album;
import com.shenma.tvlauncher.vod.domain.VideoInfo;
import com.shenma.tvlauncher.vod.domain.VideoList;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WebVideoPlayerActivity extends XwalkWebViewActivity {
    private static final String TAG = "WebVideoPlayerActivity";

    private static final int ENDING_LENGTH = 120; //120 seconds default
    private static final int STARTING_LENGTH = 120; //120 seconds default

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

    private int videoLength;
    private boolean isStopping;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao=new VodDao(this);

        initData();

        playByIndex(playIndex);

    }

    private void playByIndex(int index){
        if (index>=videoInfoList.size()){
            Log.e(TAG, "playByIndex: index out of size");
            return;
        }

        VideoInfo videoInfo=videoInfoList.get(index);
//        vodname=videoInfo.title;

        videoLength=0;
        mLastPos=0;

        List<Album> albums=dao.queryAlbumById(videoId,2);
        if (albums.size()>0){
            album=albums.get(0);

            if(album.getPlayIndex()==playIndex){
                mLastPos=album.getCollectionTime();
            }
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
            album.setNextLink(videoInfo.url);
            album.setTypeId(2);//记录
        }

        isStopping=false;

        if(mLastPos==0 && vodtype.equalsIgnoreCase("电视剧")){
            mLastPos=STARTING_LENGTH;
        }

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

        if(videoLength>0 && videoLength-mLastPos<=ENDING_LENGTH){
            isStopping=true;
            playNext();
        }

    }

    private void playNext() {
        playByIndex(playIndex++);
    }

    @Override
    public void onPlayingProperties(String propertiesJson){

        if(videoLength<=0 &&!isStopping) {
            try {
                JSONObject videoProps = new JSONObject(propertiesJson);

               videoLength=videoProps.getInt("duration");
                Log.d(TAG, "onPlayingProperties: videoLength="+videoLength);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

}
