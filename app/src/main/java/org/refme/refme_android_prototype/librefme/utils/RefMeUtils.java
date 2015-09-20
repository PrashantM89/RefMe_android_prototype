package org.refme.refme_android_prototype.librefme.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.refme.refme_android_prototype.refme_android.RefMeApp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by prashant on 15/9/15.
 */
public class RefMeUtils {

    public static String TAG = RefMeUtils.class.getSimpleName();

    public static Date stringToDate(String rawDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = null;
        try {
            date = dateFormat.parse(rawDate);
        } catch (ParseException e) {
            Log.e(TAG, "stringToDate: " + e.getClass() + ": " + e.getLocalizedMessage());
        }
        return date;
    }

//    public static String inputStreamToString(InputStream inputStream) throws IOException {
//        String text;
////        InputStreamReader reader = new InputStreamReader(inputStream, Charsets.UTF_8);
////        boolean threw = true;
////        try {
////            text = CharStreams.toString(reader);
////            threw = false;
////        } finally {
////            Closeables.close(reader, threw);
////        }
//        return text;
//    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                RefMeApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static boolean isWifiAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                RefMeApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting()
                && activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
    }

}
