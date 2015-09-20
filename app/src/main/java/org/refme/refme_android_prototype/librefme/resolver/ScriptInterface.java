package org.refme.refme_android_prototype.librefme.resolver;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

import org.refme.refme_android_prototype.refme_android.RefMeApp;

/**
 * Created by prashant on 14/9/15.
 */
public class ScriptInterface {

    private final static String TAG = ScriptInterface.class.getSimpleName();

    @JavascriptInterface
    public void showToastInterface(){
        Toast.makeText(RefMeApp.getContext(), "Called by JavaScript", Toast.LENGTH_LONG).show();
    }
}
