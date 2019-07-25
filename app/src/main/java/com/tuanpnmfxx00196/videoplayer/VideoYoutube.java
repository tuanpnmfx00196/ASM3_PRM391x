package com.tuanpnmfxx00196.videoplayer;

public class VideoYoutube {
    private String TitleVideo;
    private String Thumbnail;
    private String IdVideo;

    public VideoYoutube(String titleVideo, String thumbnail, String idVideo) {
        TitleVideo = titleVideo;
        Thumbnail = thumbnail;
        IdVideo = idVideo;
    }

    public String getTitleVideo() {
        return TitleVideo;
    }

    public void setTitleVideo(String titleVideo) {
        TitleVideo = titleVideo;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getIdVideo() {
        return IdVideo;
    }

    public void setIdVideo(String idVideo) {
        IdVideo = idVideo;
    }

}
