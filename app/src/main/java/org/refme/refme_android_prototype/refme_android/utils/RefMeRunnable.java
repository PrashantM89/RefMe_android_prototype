package org.refme.refme_android_prototype.refme_android.utils;

/**
 * Created by prashant on 14/9/15.
 */
public abstract class RefMeRunnable implements Runnable,Comparable<RefMeRunnable>{

    private int mPriority;

    public RefMeRunnable(int priority){
        mPriority = priority;
    }

    public int getPriority(){
        return mPriority;
    }

    @Override
    public int compareTo(RefMeRunnable other) {
        return other.getPriority() - mPriority;
    }
}
