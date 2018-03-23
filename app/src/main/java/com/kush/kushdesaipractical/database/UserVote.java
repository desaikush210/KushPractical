package com.kush.kushdesaipractical.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Administrator on 3/23/2018.
 */
@Dao
public interface UserVote {

    @Insert
    void insertUpvote(Upvote... upvotes);

    @Insert
    void insertDownvote(Downvote... downvotes);

    @Query("SELECT COUNT(*) FROM Upvote  WHERE url LIKE :videourl ")
    int getNumberofUpvoteForurl(String videourl);

    @Query("SELECT COUNT(*) FROM Downvote  WHERE url LIKE :videourl ")
    int getNumberofDownvoteForurl(String videourl);

    @Query("SELECT COUNT(*) FROM Upvote")
    int getNumberRowofUpvote();

    @Query("SELECT COUNT(*) FROM Downvote")
    int getNumberRowofDownvote();

}
