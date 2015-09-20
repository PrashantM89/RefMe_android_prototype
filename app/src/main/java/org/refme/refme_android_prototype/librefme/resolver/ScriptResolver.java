package org.refme.refme_android_prototype.librefme.resolver;

import android.webkit.WebSettings;
import android.webkit.WebView;

import org.refme.refme_android_prototype.refme_android.RefMeApp;

/**
 * Created by prashant on 14/9/15.
 */
public class ScriptResolver {

    private final static String TAG = ScriptResolver.class.getSimpleName();

    private WebView mWebView;

    public WebView getWebView(){
        if(mWebView == null) {
            mWebView = new WebView(RefMeApp.getContext());
        }
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new ScriptInterface(),"WebInterface");
        return mWebView;
    }
}
