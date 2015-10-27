package org.refme.refme_android_prototype.refme_android.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by prashant on 27/9/15.
 */
public class ReferalDownloadService extends IntentService{

    private static final int STATUS_RUNNNG = 0;
    private static final int STATUS_FINISHED = 1;
    private static final int STATUS_ERROR = 2;

    private static final String TAG = ReferalDownloadService.class.getSimpleName();



    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *  Used to name the worker thread, important only for debugging.
     */
    public ReferalDownloadService() {
        super("ReferalDownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG,TAG+" Started");

        String URL = intent.getStringExtra("url");
        Bundle bundle = new Bundle();

        if(!TextUtils.isEmpty(URL)){


            String result = "My Online Data";
            try{

                if(result != null && result.length()>0){
                    bundle.putString("result",result);

                }
            }catch(Exception exceptionObj){

                bundle.putString(Intent.EXTRA_TEXT, exceptionObj.toString());

            }
        }

        Log.d(TAG,TAG+" Completed");
        this.stopSelf();
    }
}
