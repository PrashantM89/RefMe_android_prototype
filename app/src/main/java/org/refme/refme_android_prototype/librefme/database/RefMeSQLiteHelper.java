package org.refme.refme_android_prototype.librefme.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by prashant on 15/9/15.
 */
public class RefMeSQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = RefMeSQLiteHelper.class.getSimpleName();

    private static final String DATABASE_NAME="refmeuser.db";

    private static final int DATABASE_VERSION=1;

    public static final String TABLE_PROFILE = "profile";

    public static final String PROFILE_COLUMN_ID = "";

    public static final String PROFILE_COLUMN_NAME = "";

    public static final String PROFILE_COLUMN_COMPANY = "";

    public static final String PROFILE_COLUMN_DESIGNATION = "";

    public static final String PROFILE_COLUMN_EMAIL = "";

    public static final String PROFILE_COLUMN_EXPERIENCE = "";

    public static final String PROFILE_COLUMN_LOCATION = "";

    public static final String PROFILE_COLUMN_IMAGE = "";

    private static final String CREATE_TABLE_PROFILE = "CREATE TABLE `"+TABLE_PROFILE + "` ( `"
                        + PROFILE_COLUMN_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT, `"
                        + PROFILE_COLUMN_NAME + "` TEXT, `"
                        + PROFILE_COLUMN_COMPANY + "` TEXT, `"
                        + PROFILE_COLUMN_DESIGNATION + "` TEXT, `"
                        + PROFILE_COLUMN_EMAIL + "` TEXT, `"
                        + PROFILE_COLUMN_EXPERIENCE + "` TEXT, `"
                        + PROFILE_COLUMN_LOCATION + "` TEXT, `"
                        + PROFILE_COLUMN_IMAGE + "` TEXT );";

    public RefMeSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
      database.execSQL(CREATE_TABLE_PROFILE);
      Log.d(TAG,"Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
