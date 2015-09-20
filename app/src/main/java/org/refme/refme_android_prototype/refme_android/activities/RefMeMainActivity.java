package org.refme.refme_android_prototype.refme_android.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.refme.refme_android_prototype.R;
import org.refme.refme_android_prototype.librefme.authentication.AuthenticatorManager;
import org.refme.refme_android_prototype.librefme.resolver.ScriptResolver;
import org.refme.refme_android_prototype.refme_android.RefMeApp;
import org.refme.refme_android_prototype.refme_android.utils.RefMeRunnable;
import org.refme.refme_android_prototype.refme_android.utils.ThreadManager;


public class RefMeMainActivity extends ActionBarActivity{

    private static final String TAG = RefMeMainActivity.class.getSimpleName();

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mTitle;

    private CharSequence mDrawerTitle;

    private ScriptResolver scriptResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refme_main_activity);
        final RefMeApp refMeApp = new RefMeApp(this.getApplicationContext());
        scriptResolver = new ScriptResolver();
        scriptResolver.getWebView().loadUrl("file:///android_asset/index.html");


        RefMeRunnable r = new RefMeRunnable(1) {
            @Override
            public void run() {


            }
        };

        ThreadManager.getInstance().execute(r);
    }

    public static class RefMeMainReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
              boolean internetConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);
                Log.d(TAG,"Internet Connectivity :==========="+internetConnectivity);
                if(internetConnectivity){
                    Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
