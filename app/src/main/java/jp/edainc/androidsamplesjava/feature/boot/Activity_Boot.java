package jp.edainc.androidsamplesjava.feature.boot;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.uber.autodispose.SingleScoper;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import jp.edainc.androidsamplesjava.R;
import jp.edainc.androidsamplesjava.databinding.ActivityBootBinding;
import jp.edainc.androidsamplesjava.feature.main.Activity_Main;
import jp.edainc.androidsamplesjava.ui.activity.Activity_Base;
import jp.edainc.androidsamplesjava.utility.AppUpdateChecker;

/**
 * Created by kobayashiryou on 2017/10/24.
 *
 * 起動画面
 */

public class Activity_Boot extends Activity_Base {

    private ActivityBootBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO アプリケーション更新チェック
        if(AppUpdateChecker.isFirstLaunchFromLastUpdate(this)) {

            binding = DataBindingUtil.setContentView(this, R.layout.activity_boot);
            // layout observable tree登録
            // スプラッシュアニメーション遅延実行

            AppUpdateChecker.onActivityCreated(this);

        } else {

            // ログイン
            login();
            finish();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isFinishing()) return;
        // ログイン処理
        Single.timer(750, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .to(new SingleScoper<>(lifecycleProvider().onStart()))
            .subscribe(ms -> {
                if(isFinishing()) return;
                // ログイン
                login();
                finish();
            });
    }

    private void login() {
        startActivity(Activity_Main.createIntent(this));
    }
}
