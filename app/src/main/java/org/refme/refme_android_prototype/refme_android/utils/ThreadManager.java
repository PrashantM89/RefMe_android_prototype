package org.refme.refme_android_prototype.refme_android.utils;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by prashant on 14/9/15.
 */
public final class ThreadManager {

    /*
     * Gets the number of available cores
     * (not always the same as the maximum number of cores)
     */
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    // Sets the amount of time an idle thread waits before terminating
    private static final int KEEP_ALIVE_TIME = 1;

    // Sets the Time Unit to seconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    private static class Holder {

        private static final ThreadManager instance = new ThreadManager();

    }

    private ThreadPoolExecutor mThreadPool;

    private ThreadManager() {

        mThreadPool = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES,
                KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, new PriorityBlockingQueue<Runnable>());
    }

    public static ThreadManager getInstance() {
        return Holder.instance;
    }

    public void execute(Runnable r) {
        mThreadPool.execute(r);
    }



    public boolean stop(Runnable r) {
        boolean success = false;
        mThreadPool.remove(r);
        return success;
    }



    public boolean isActive() {
        return mThreadPool.getActiveCount() > 0
                || mThreadPool.getQueue().size() > 0;
    }

}
