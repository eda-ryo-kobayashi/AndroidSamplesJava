package jp.edainc.androidsamplesjava.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kobayashiryou on 2017/10/24.
 *
 * アプリケーションが更新されたかどうかチェックする
 */

public final class AppUpdateChecker {

    private AppUpdateChecker() {}

    private static final String PREFS_NAME = "app_update_checker.prefs";
    private static final String KEY_IS_UPDATED = "is_updated";

    public static boolean isFirstLaunchFromLastUpdate(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_IS_UPDATED, true);
    }

    public static void onUpdated(Context context) {
        setUpdatedFlag(context, true);
    }

    public static void onActivityCreated(Activity activity) {
        setUpdatedFlag(activity, false);
    }

    private static void setUpdatedFlag(Context context, boolean updated) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(KEY_IS_UPDATED, updated);
        editor.apply();
    }
}
