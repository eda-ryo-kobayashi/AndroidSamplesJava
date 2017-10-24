package jp.edainc.androidsamplesjava.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import jp.edainc.androidsamplesjava.AppContext;
import jp.edainc.androidsamplesjava.lifecycle.ActivityLifecycleProvider;

/**
 * Created by kobayashiryou on 2017/10/24.
 *
 * 各Activityの基底クラス
 */

public class Activity_Base extends AppCompatActivity implements ActivityContext {

    private ActivityLifecycleProvider provider = new ActivityLifecycleProvider();
    private RxPermissions permissions;

    @Override
    public ActivityLifecycleProvider lifecycleProvider() {
        return provider;
    }

    @Override
    public AppContext appContext() {
        return (AppContext)getApplication();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissions = new RxPermissions(this);
        provider.onCreate().reset();
    }

    @Override
    protected void onStart() {
        super.onStart();
        provider.onStart().reset();
    }

    @Override
    protected void onResume() {
        super.onResume();
        provider.onResume().reset();
    }

    @Override
    protected void onPause() {
        super.onPause();
        provider.onResume().onChangeLifecycle();
    }

    @Override
    protected void onStop() {
        super.onStop();
        provider.onStart().onChangeLifecycle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        provider.onCreate().onChangeLifecycle();
    }

}
