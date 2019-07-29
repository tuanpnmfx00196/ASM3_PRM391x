package com.tuanpnmfxx00196.videoplayer;

public class VideoYoutube {
    private String TitleVideo;
    private String Description;
    private String Thumbnail;
    private String IdVideo;
    private String User;

    public VideoYoutube(String user, String titleVideo, String description, String thumbnail, String idVideo) {
        User = user;
        TitleVideo = titleVideo;
        Description = description;
        Thumbnail = thumbnail;
        IdVideo = idVideo;
    }

    public String getTitleVideo() {
        return TitleVideo;
    }

    public void setTitleVideo(String titleVideo) {
        TitleVideo = titleVideo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }
}
