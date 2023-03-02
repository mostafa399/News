package com.mostafahelal.news.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

public class OurYtModel {
    @SerializedName("youtubeData")
    @Expose
    private List<YoutubeDatum> youtubeData;

    public List<YoutubeDatum> getYoutubeData() {
        return youtubeData;
    }

    public void setYoutubeData(List<YoutubeDatum> youtubeData) {
        this.youtubeData = youtubeData;
    }
@Generated("jsonschema2pojo")
public class YoutubeDatum {

    @SerializedName("pid")
    @Expose
    private Integer pid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("channel_id")
    @Expose
    private String channelId;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

}
}