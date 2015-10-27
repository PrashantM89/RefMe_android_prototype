package org.refme.refme_android_prototype.librefme.model;

import java.io.Serializable;

/**
 * Created by prashant on 4/10/15.
 */
public class NavigationItems implements Serializable{

    private String mTitle;
    private String mSubtitle;
    private int mImage;

    public NavigationItems(String mTitle, String mSubtitle, int mImage) {
        this.mTitle = mTitle;
        this.mSubtitle = mSubtitle;
        this.mImage = mImage;
    }

    public int getmImage() {
        return mImage;
    }

    public String getmSubtitle() {
        return mSubtitle;
    }

    public String getmTitle() {
        return mTitle;
    }
}
