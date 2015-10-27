package org.refme.refme_android_prototype.librefme.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.refme.refme_android_prototype.librefme.model.ProfileItems;
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

    public void storeProfile(final ProfileItems profileItems){
        new Thread(new Runnable(){

            @Override
            public void run() {
                synchronized (this){
                    ContentValues values = new ContentValues();
                    values.put(RefMeSQLiteHelper.PROFILE_COLUMN_COMPANY,profileItems.getCompany());
                    values.put(RefMeSQLiteHelper.PROFILE_COLUMN_DESIGNATION,profileItems.getDesignation());
                    values.put(RefMeSQLiteHelper.PROFILE_COLUMN_EMAIL,profileItems.getEmail());
                    values.put(RefMeSQLiteHelper.PROFILE_COLUMN_EXPERIENCE,profileItems.getExperience());
                    values.put(RefMeSQLiteHelper.PROFILE_COLUMN_LOCATION,"Mumbai");
                    values.put(RefMeSQLiteHelper.PROFILE_COLUMN_IMAGE,profileItems.getImageUrl());
                    mDatabase.beginTransaction();
                    mDatabase.insert(RefMeSQLiteHelper.TABLE_PROFILE,null,values);
                    mDatabase.setTransactionSuccessful();
                    mDatabase.endTransaction();
                    Log.d(TAG,"Profile saved in DB");
                }
            }
        }).start();
    }

}
