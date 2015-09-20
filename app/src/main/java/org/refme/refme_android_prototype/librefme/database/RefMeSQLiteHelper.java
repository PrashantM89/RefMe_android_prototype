package org.refme.refme_android_prototype.librefme.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by prashant on 15/9/15.
 */
public class RefMeSQLiteHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME="refmeuser.db";

    private final static int DATABASE_VERSION=1;

    public RefMeSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
