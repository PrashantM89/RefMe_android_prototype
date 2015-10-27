package org.refme.refme_android_prototype.refme_android.services;

import android.app.IntentService;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.refme.refme_android_prototype.librefme.model.ProfileItems;
import org.refme.refme_android_prototype.refme_android.Events.ProfileEvent;
import org.refme.refme_android_prototype.refme_android.RefMeApp;

import de.greenrobot.event.EventBus;

/**
 * Created by prashant on 11/10/15.
 */
public class InitService extends IntentService {
    private RequestQueue queue;

    private ProfileItems profileItem = null;

    private ProfileEvent profileEvent = null;

    private ProgressDialog progressDialog;

    private EventBus bus = EventBus.getDefault();

    private static final String TAG = InitService.class.getSimpleName();

    public InitService() {
        super("InitService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, TAG + "--> Started");

        String URL = intent.getStringExtra("url");
        Bundle bundle = new Bundle();

        if(!TextUtils.isEmpty(URL)){
            queue = Volley.newRequestQueue(RefMeApp.getContext());
            getOnlineDate(URL);
        }

        Log.d(TAG,TAG+"--> Completed");
        this.stopSelf();
    }

    private void getOnlineDate(String url) {
        Log.d(TAG,"URL"+url);

        //showProgress();

        JsonArrayRequest request = new JsonArrayRequest(url,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        profileItem = new ProfileItems();
                        profileEvent = new ProfileEvent();
                        profileEvent.setName(object.getString("refererName"));
                        profileEvent.setCompany(object.getString("refererCompanyName"));
                        profileEvent.setEmail(object.getString("refererEmailId"));
                        profileEvent.setExperience(object.getInt("refererTotalExperience"));
                        profileEvent.setDesignation(object.getString("refererDesignation"));
                        profileEvent.setImage(object.getString("refererImageString"));

                        Log.d(TAG,object.getString("refererName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                bus.post(profileEvent);
                Toast.makeText(RefMeApp.getContext(),"Event Posted",Toast.LENGTH_SHORT).show();
            //    hideProgress();
            }
        },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
          //              hideProgress();
                        Toast.makeText(RefMeApp.getContext(), "Cannot connect to server. Check your internet connection.", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"Error:"+volleyError.getMessage());
                    }
                });

        queue.add(request);
    }

    private void showProgress(){
        progressDialog = new ProgressDialog(RefMeApp.getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    private void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}


