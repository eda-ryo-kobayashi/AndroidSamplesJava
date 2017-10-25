package jp.edainc.androidsamplesjava.utility;

import android.os.Looper;

import timber.log.Timber;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * スレッドユーティリティ
 */

public final class ThreadUtility {
    private ThreadUtility(){}

    public static void sleep(long millisecond) {
        assertMain();
        try {
            Thread.sleep(millisecond);
        } catch(InterruptedException e) {}
    }

    public static void assertMain() {
        if(Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new IllegalStateException("このメソッドをMain Threadから呼んではいけない");
        }
    }

    public static void dumpName() {
        Timber.i("Thread : %s", Thread.currentThread().getName());
    }
}
