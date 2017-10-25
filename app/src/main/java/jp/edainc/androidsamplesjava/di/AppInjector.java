package jp.edainc.androidsamplesjava.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import dagger.android.AndroidInjection;
import jp.edainc.androidsamplesjava.App;
import timber.log.Timber;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * アプリケーション用のインジェクター
 */

public final class AppInjector {
    private AppInjector() {}

    public static void inject(App app) {
        DaggerAppComponent
            .builder()
            .application(app)
            .appConfig(new AppConfigModule(app))
            .build()
            .inject(app);

        app.registerActivityLifecycleCallbacks(LIFECYCLE_CALLBACKS);
    }

    private static final Application.ActivityLifecycleCallbacks LIFECYCLE_CALLBACKS = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Timber.d("onActivityCreated");
            // ここでインジェクトできるクラスかどうかチェック
            if(activity instanceof Injectable) {
                // もっといい感じにならないか
                AndroidInjection.inject(activity);
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };
}
