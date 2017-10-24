package jp.edainc.androidsamplesjava;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by kobayashiryou on 2017/10/24.
 *
 * アプリケーション
 * 起動時に一番最初に呼ばれる
 */

public class App extends Application implements AppContext, HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> daInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        if(LeakCanary.isInAnalyzerProcess(this)) {
            // このプロセスではアプリの初期化はしない
            return;
        }

        LeakCanary.install(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return daInjector;
    }
}
