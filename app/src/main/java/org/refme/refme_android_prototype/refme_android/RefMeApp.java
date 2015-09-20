package org.refme.refme_android_prototype.refme_android;

import android.app.Application;
import android.content.Context;


/**
 * Created by prashant on 14/9/15.
 */
public class RefMeApp extends Application{

private static Context mApplicationContext;

    public RefMeApp(Context context){
        mApplicationContext = context;
    }

    public static Context getContext(){
        return mApplicationContext;
    }
}
