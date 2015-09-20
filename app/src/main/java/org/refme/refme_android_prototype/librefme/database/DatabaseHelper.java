package org.refme.refme_android_prototype.librefme.database;

import android.database.sqlite.SQLiteDatabase;

import org.refme.refme_android_prototype.refme_android.RefMeApp;

/**
 * Created by prashant on 15/9/15.
 */
public class DatabaseHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private SQLiteDatabase mDatabase;

    private static class Holder{
        private static final DatabaseHelper instance = new DatabaseHelper();
    }

    public DatabaseHelper getInstance(){
        return Holder.instance;
    }

    private DatabaseHelper(){
        RefMeSQLiteHelper mSQLiteHelper = new RefMeSQLiteHelper(RefMeApp.getContext());
        mSQLiteHelper.close();
        mDatabase = mSQLiteHelper.getWritableDatabase();
    }

}
