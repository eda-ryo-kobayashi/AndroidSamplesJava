package jp.edainc.androidsamplesjava.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import jp.edainc.androidsamplesjava.utility.AppUpdateChecker;

/**
 * Created by kobayashiryou on 2017/10/24.
 *
 * アプリケーションが更新されたかどうかを受け取る
 */

public class UpdateAppReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if(Intent.ACTION_MY_PACKAGE_REPLACED.equals(action)) {
            AppUpdateChecker.onUpdated(context);
        }
    }
}
