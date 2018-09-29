package com.mybacc.xwalkvideoplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkSettings;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;
import org.xwalk.core.XWalkWebResourceRequest;
import org.xwalk.core.XWalkWebResourceResponse;

import java.io.IOException;
import java.io.InputStream;

import timber.log.Timber;

public abstract class XwalkWebViewActivity extends Activity implements VideoPlayerInterface{
    XWalkView mXwalkView;

    ImageView mImageView;

    View mOverlayView;

    LinearLayout mLinearLayout;

    ProgressBar mProgressCard;

    TextView mMovieName;

    TextView mEpisodeName;

    View mEpisodeListView;

    View mEpisodeSourceView;

    boolean paused;

    private boolean mLoading;

    @Override
    public void play(String url) {
        play(url,"","");
    }

    @Override
    public void play(){
        String js = "javascript:getVideo().play();";

        mXwalkView.loadUrl(js);
    }

    @Override
    public void pause() {
        String js = "javascript:getVideo().pause();";

        mXwalkView.loadUrl(js);
    }

    @Override
    public void mute() {
        String js = "javascript:getVideo().muted=true;";
        mXwalkView.loadUrl(js);
    }

    @Override
    public void unmute() {
        String js = "javascript:getVideo().muted=false;";
        mXwalkView.loadUrl(js);
    }

    @Override
    public void stop() {
        String js = "javascript:getVideo().stop();";

        mXwalkView.loadUrl(js);
    }

    @Override
    public void seekTo(double position) {
        Timber.d("seekTo="+position);
        String js = "javascript:getVideo().currentTime="+position+";";
        mXwalkView.loadUrl(js);
    }

    @Override
    public void forward() {
        String js = "javascript:getVideo().pause();getVideo().currentTime+=10;getVideo().play()";
        mXwalkView.loadUrl(js);
    }

    @Override
    public void backward() {
        String js = "javascript:getVideo().pause();getVideo().currentTime-=10;getVideo().play()";
        mXwalkView.loadUrl(js);
    }


    class MyResourceClient extends XWalkResourceClient {
        MyResourceClient(XWalkView view) {
            super(view);
        }

        @Override
        public XWalkWebResourceResponse shouldInterceptLoadRequest(XWalkView view, XWalkWebResourceRequest request) {
            Timber.d("request:" + request.getUrl());

            if (request.getUrl().toString().contains("js.zyrfanli.com")) {
                return createXWalkWebResourceResponse("", "", null);
            } else if (request.getUrl().toString().contains("cnzz.com/")) {
                return createXWalkWebResourceResponse("", "", null);
            } else if (request.getUrl().toString().contains("img2.xjoot.com")) {
                return createXWalkWebResourceResponse("", "", null);
            } else if (request.getUrl().toString().contains("km.jianduankm.com")) {
                return createXWalkWebResourceResponse("", "", null);
            } else if (request.getUrl().toString().contains("e.nirentang.com")) {
                return createXWalkWebResourceResponse("", "", null);
            }
//            else if (request.getUrl().toString().contains("boba.52kuyun.com/html/js/share.js")) {
//                return createXWalkWebResourceResponse("", "", null);
//            }

            return super.shouldInterceptLoadRequest(view, request);

        }
    }

    class MyUIClient extends XWalkUIClient {

        XWalkView mWalkView;

        MyUIClient(XWalkView view) {
            super(view);
            mWalkView = view;
        }


        private void injectScriptFile(XWalkView view, String scriptFile) {
            InputStream input;
            try {
                input = getAssets().open(scriptFile);
                byte[] buffer = new byte[input.available()];
                input.read(buffer);
                input.close();

                // String-ify the script byte-array using BASE64 encoding !!!
                String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
                view.loadUrl("javascript:(function() {" +
                        "var parent = document.getElementsByTagName('head').item(0);" +
                        "var script = document.createElement('script');" +
                        "script.type = 'text/javascript';" +
                        // Tell the browser to BASE64-decode the string into your script !!!
                        "script.innerHTML = window.atob('" + encoded + "');" +
                        "parent.appendChild(script)" +
                        "})()");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        @Override
        public void onPageLoadStopped(XWalkView view, String url, LoadStatus status) {
            super.onPageLoadStopped(view, url, status);

            injectScriptFile(view, "script.js");
            view.loadUrl("javascript:init();");

            mute();

            play();


        }

        @Override
        public boolean shouldOverrideKeyEvent(XWalkView view, KeyEvent event) {

            Log.d("XwalkWebView", "KEYEVENT:=" + event.getKeyCode());

            boolean overrided = true;
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_UP: {//向上
                }
                break;
                case KeyEvent.KEYCODE_DPAD_DOWN: {//向下
//                    showEpisodeList();
                }
                break;
                case KeyEvent.KEYCODE_DPAD_LEFT: {//向左
                    backward();
                }
                break;
                case KeyEvent.KEYCODE_DPAD_RIGHT: {//向右
                    forward();
                }
                break;
                case KeyEvent.KEYCODE_ENTER:
                case KeyEvent.KEYCODE_DPAD_CENTER: {//确定

                    if (paused)
                        play();
                    else
                        pause();
                }
                break;
                case KeyEvent.KEYCODE_BACK: {//返回

                }
                break;
                case KeyEvent.KEYCODE_HOME: {//home

                }
                break;
                case KeyEvent.KEYCODE_MENU: {//菜单
//                    showSourceList();

                }
                break;

                default: {
                    overrided = super.shouldOverrideKeyEvent(view, event);
                }

            }

            return overrided;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalkwebview);

        mXwalkView = (org.xwalk.core.XWalkView)findViewById(R.id.xwalkView);
        mXwalkView.setResourceClient(new MyResourceClient(mXwalkView));
        mXwalkView.setUIClient(new MyUIClient(mXwalkView));

        mImageView=(ImageView) findViewById(R.id.main_image);
        mOverlayView=findViewById(R.id.view_overlay);
        mLinearLayout=(LinearLayout) findViewById(R.id.loadingLayout);
        mProgressCard=(ProgressBar)findViewById(R.id.progress_card);
        mMovieName=(TextView) findViewById(R.id.movieName);
        mEpisodeName=(TextView) findViewById(R.id.episodeName);

        mEpisodeListView=findViewById(R.id.episode_list_containter);
        mEpisodeSourceView=findViewById(R.id.episode_source_containter);


        XWalkSettings settings = mXwalkView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAllowFileAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setJavaScriptEnabled(true);


        mXwalkView.addJavascriptInterface(new JSVideoObj(), "videoObj");

    }

    public void play(final String url,final String title,final String subtitle){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                mXwalkView.loadUrl(url);
                setTitle(title);
                setSubtitle(subtitle);
                showLoadingView();
            }
        });


    }

    public void setTitle(String title){
        mMovieName.setText(title);
    }

    public void setSubtitle(String subtitle){
        mEpisodeName.setText(subtitle);
    }


    public void showLoadingView() {

//        mMovieName.setText(mMovie.getTitle());
//        mEpisodeName.setText(mMovie.getPlayUrl(mMovie.currentSource, mMovie.currentIndex).title);

        mImageView.setVisibility(View.VISIBLE);
        mLinearLayout.setVisibility(View.VISIBLE);
        mOverlayView.setVisibility(View.VISIBLE);
        mProgressCard.setVisibility(View.VISIBLE);

        mLoading = true;

    }


    public void hideLoadingView() {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI

                mImageView.setVisibility(View.INVISIBLE);
                mLinearLayout.setVisibility(View.INVISIBLE);
                mOverlayView.setVisibility(View.INVISIBLE);
                mProgressCard.setVisibility(View.INVISIBLE);

                unmute();
            }
        });

        mLoading = false;


    }


//    public void showSourceList() {
//        Timber.d("movie=" + mMovie);
//
//        if (mSourceMenu != null) {
//            mSourceMenu.dismiss();
//            mSourceMenu = null;
//        }
//
//        PopupMenu menu = new PopupMenu(this);
//        menu.setHeaderTitle("视频源");
//        final List<String> srcList = mMovie.getPlaySrcList();
//
//        // Set Listener
//        menu.setOnItemSelectedListener(new PopupMenu.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(MenuItem item) {
//                int idx = item.getItemId();
//                String sourceName = srcList.get(idx);
//                mMovie.currentSource = sourceName;
//
//                Intent intent = new Intent(getBaseContext(), XwalkWebViewActivity.class);
//                intent.putExtra(DetailsActivity.MOVIE, mMovie);
//                intent.putExtra("URL", mMovie.getVideoUrlInfo(sourceName, mMovie.currentIndex).url);
//                startActivity(intent);
//
//            }
//        });
//
//        PlayerHelper playerHelper = VineyardApplication.get(this).getComponent().playerHelper();
//        int i = 0;
//        for (String src : srcList) {
//            menu.add(i, playerHelper.getPlayerName(src));
//            i++;
//        }
//
//        int idx = srcList.indexOf(mMovie.currentSource);
//
//        mSourceMenu = menu;
//
//        menu.show(mEpisodeSourceView);
//
//        menu.setSelectedPosition(idx);
//
//    }
//
//    public void showEpisodeList() {
//
//        if (mEpisodeMenu != null) {
//            mEpisodeMenu.dismiss();
//            mEpisodeMenu = null;
//        }
//
//        PopupMenu menu = new PopupMenu(this);
//        menu.setHeaderTitle("分集列表");
//        final List<Movie.PlayUrlInfo> urlInfoList = mMovie.getPlayUrlList(mMovie.currentSource);
//
//        // Set Listener
//        menu.setOnItemSelectedListener(new PopupMenu.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(MenuItem item) {
//
//                int idx = item.getItemId();
//
//                Movie.PlayUrlInfo urlInfo = urlInfoList.get(idx);
//
//                Intent intent = new Intent(getBaseContext(), XwalkWebViewActivity.class);
//                intent.putExtra(DetailsActivity.MOVIE, mMovie);
//                intent.putExtra("URL", urlInfo.url);
//                startActivity(intent);
//
//            }
//        });
//
//        int i = 0;
//        for (Movie.PlayUrlInfo urlInfo : urlInfoList) {
//
//            MenuItem menuItem = menu.add(i, urlInfo.title);
//
//            if (i == mMovie.currentIndex) {
//                menuItem.setIcon(getDrawable(R.drawable.ic_play));
//            }
//
//            i++;
//        }
//        mEpisodeMenu = menu;
////        }
//
//        menu.show(mEpisodeListView);
//        mEpisodeMenu.setSelectedPosition(mMovie.currentIndex);
//
//
//    }

    public class JSVideoObj {

        private final String TAG = JSVideoObj.class.getSimpleName();

        @org.xwalk.core.JavascriptInterface
        public void processEvents(String eventType) {
            Timber.d("event:" + eventType);

            onPlayingEventChanged(eventType);

        }

        @org.xwalk.core.JavascriptInterface
        public void processProperties(String propertiesJson) {

//            Timber.d("processsProperties:" + propertiesJson);

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(propertiesJson);

                if (jsonObject != null) {

                    double currentTime = jsonObject.getDouble("currentTime");

                    Timber.d("currentTime:" + currentTime);

                    int intTime = (int) currentTime;

                    if (intTime > 0 && mLoading) {

                        hideLoadingView();

                    }


                    onProgressUpdated(currentTime);

                    paused = jsonObject.getBoolean("paused");

                    //other properties
                    onPlayingProperties(propertiesJson);
                }

            } catch (JSONException e) {
                Log.e(TAG, "processsProperties: " + e.getMessage());
            }

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mXwalkView != null) {
            mXwalkView.pauseTimers();
            mXwalkView.onHide();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mXwalkView != null) {
            mXwalkView.resumeTimers();
            mXwalkView.onShow();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mXwalkView != null) {
            mXwalkView.onDestroy();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mXwalkView != null) {
            mXwalkView.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (mXwalkView != null) {
            mXwalkView.onNewIntent(intent);
        }
    }
}
