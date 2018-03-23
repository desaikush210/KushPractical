package com.kush.kushdesaipractical.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Administrator on 3/23/2018.
 */
@Database(entities = {Upvote.class, Downvote.class}, version = 1)
public abstract class AppDatabase  extends RoomDatabase {
    public abstract UserVote userVote();

    private static AppDatabase db;

    public static AppDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "GiphyDatabase").allowMainThreadQueries().build();

            //Room.inMemoryDatabaseBuilder(context.getApplicationContext(),AppDatabase.class).allowMainThreadQueries().build();
        }
        return db;
    }

    public static void destroyInstance() {
        db = null;
    }
}
