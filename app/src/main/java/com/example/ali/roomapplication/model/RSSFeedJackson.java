package com.example.ali.roomapplication.model;

import android.databinding.BaseObservable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

/**
 * Created by satyabrata on 28/12/17.
 */


@JsonIgnoreProperties(ignoreUnknown=true)
@JacksonXmlRootElement(localName="rss")
public class RSSFeedJackson implements Serializable{

    @JacksonXmlProperty(localName = "version", isAttribute = true)
    private String version ;

    @JacksonXmlProperty(localName = "channel")
    private Channel channel;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
