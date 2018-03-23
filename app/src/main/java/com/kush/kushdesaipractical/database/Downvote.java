package com.kush.kushdesaipractical.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Administrator on 3/23/2018.
 */
@Entity(tableName = "Downvote")
public class Downvote {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uid")
    private int uid;

    @ColumnInfo(name = "url")
    private String url;

    public Downvote(int uid, String url) {
        this.uid = uid;
        this.url = url;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
