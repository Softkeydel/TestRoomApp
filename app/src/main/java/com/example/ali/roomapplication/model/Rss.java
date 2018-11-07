package com.example.ali.roomapplication.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by satyabrata on 8/5/18.
 */

public class Rss extends BaseObservable{

    private long id;
    private String title;
    private String link;


    public Rss(long id, String title, String link) {
        this.id = id;
        this.title = title;
        this.link = link;
    }

    public Rss(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
        notifyPropertyChanged(BR.link);
    }
}
