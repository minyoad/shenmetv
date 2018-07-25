package com.mybacc.xwalkvideoplayer;

public interface VideoPlayerInterface {

    void onPlayingEventChanged(String event);

    void onProgressUpdated(double position);

    void onPlayingProperties(String propertiesJson);

    void play(String url);

    void play();

    void pause();

    void mute();

    void unmute();

    void stop();

    void seekTo(double position);

    void forward();

    void backward();

}
