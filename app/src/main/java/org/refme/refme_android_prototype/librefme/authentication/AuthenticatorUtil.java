package org.refme.refme_android_prototype.librefme.authentication;

import android.app.Activity;

/**
 * Created by prashant on 13/9/15.
 */
public abstract class AuthenticatorUtil {
    private String mPrettyName;

    private String mId;

    protected AuthenticatorUtil(String id, String prettyName) {
        mId = id;
        mPrettyName = prettyName;
    }
    public String getPrettyName() {
        return mPrettyName;
    }

    public String getId() {
        return mId;
    }

    public abstract String getDescription();

    public abstract int getIconResourceId();

    public abstract int getUserIdEditTextHintResId();

    public abstract void register(String name, String password, String email);

    public abstract void login(Activity activity, String email, String password);

    public abstract void logout(Activity activity);

    public abstract boolean isLoggedIn();

    public abstract String getUserName();

    public void login(String email, String password) {
        login(null, email, password);
    }

    public void logout() {
        logout(null);
    }

    public abstract boolean doesAllowRegistration();
}
