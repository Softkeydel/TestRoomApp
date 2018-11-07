package com.example.ali.roomapplication.model;

import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.android.databinding.library.baseAdapters.BR;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;


/**
 * Created by satyabrata on 28/12/17.
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Item extends BaseObservable implements Serializable {

    //private PropertyChangeRegistry registry = new PropertyChangeRegistry();


    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "title")
    @JacksonXmlProperty(localName="title")
    private String title;

    @ColumnInfo(name = "description")
    @JacksonXmlProperty(localName="description")
    private String description;

    @ColumnInfo(name = "link")
    @JacksonXmlProperty(localName="link")
    private String link;

    @Ignore
    @JacksonXmlProperty(localName="pubDate")
    private String pubDate;




    public Item() {

    }

    public Item(long id, String title, String description, String link,String pubDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate=pubDate;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
        //registry.notifyChange(this,BR.title);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
        notifyPropertyChanged(BR.link);
    }

    @Bindable
    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
        notifyPropertyChanged(BR.pubDate);
    }

}
