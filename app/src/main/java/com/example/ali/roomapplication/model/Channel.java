package com.example.ali.roomapplication.model;

import android.databinding.BaseObservable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by satyabrata on 28/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
//@JacksonXmlRootElement(localName = "channel")
public class Channel implements Serializable{

    @JacksonXmlProperty(localName="title")
    private String title;

    @JacksonXmlProperty(localName="link")
    private String link;

    @JacksonXmlProperty(localName = "description")
    private String description;

    @JacksonXmlProperty(localName = "language")
    private String language;

    @JacksonXmlProperty(localName = "copyright")
    private String copyright;

    @JacksonXmlProperty(localName = "pubDate")
    private String pubDate;

    @JacksonXmlElementWrapper(localName = "item",useWrapping = false)
    private List<Item> item;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
