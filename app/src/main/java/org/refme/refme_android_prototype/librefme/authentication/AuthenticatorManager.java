package org.refme.refme_android_prototype.librefme.authentication;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import org.refme.refme_android_prototype.R;
import org.refme.refme_android_prototype.refme_android.RefMeApp;

/**
 * Created by prashant on 13/9/15.
 */
public class AuthenticatorManager {

    private static final String TAG = AuthenticatorManager.class.getSimpleName();

    public final static int CONFIG_TEST_RESULT_TYPE_SUCCESS = 1;

    public final static int CONFIG_TEST_RESULT_TYPE_LOGOUT = 2;

    public final static int CONFIG_TEST_RESULT_TYPE_COMMERROR = 3;

    public final static int CONFIG_TEST_RESULT_TYPE_INVALIDCREDS = 4;

    public final static int CONFIG_TEST_RESULT_TYPE_ACCOUNTEXPIRED = 5;


    private static class Holder{
       private static final AuthenticatorManager instance = new AuthenticatorManager();
    }

    public static AuthenticatorManager getInstance(){
        return Holder.instance;
    }

    public static class ConfigTestResultEvent {

        public int mType;

        public String mMessage;
    }


    public static void showToast(ConfigTestResultEvent event) {
        String message;
        switch (event.mType) {
            case CONFIG_TEST_RESULT_TYPE_SUCCESS:
                message = RefMeApp.getContext().getString(R.string.auth_logged_in);
                break;
            case CONFIG_TEST_RESULT_TYPE_LOGOUT:
                message = RefMeApp.getContext().getString(R.string.auth_logged_out);
                break;
            case CONFIG_TEST_RESULT_TYPE_INVALIDCREDS:
                message = RefMeApp.getContext().getString(R.string.error_invalid_credentials);
                break;
            case CONFIG_TEST_RESULT_TYPE_COMMERROR:
                message = RefMeApp.getContext().getString(R.string.error_communication);
                break;
            case CONFIG_TEST_RESULT_TYPE_ACCOUNTEXPIRED:
                message = RefMeApp.getContext().getString(R.string.error_account_expired);
                break;
            default:
                message = "default message";
        }
        final String msg = message;

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RefMeApp.getContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
