package jp.edainc.androidsamplesjava.ui.activity;

import jp.edainc.androidsamplesjava.AppContext;
import jp.edainc.androidsamplesjava.lifecycle.ActivityLifecycleProvider;

/**
 * Created by kobayashiryou on 2017/10/24.
 *
 * Activity操作のコンテキスト
 */

public interface ActivityContext {

    /**
     * LifecycleProvider取得
     * @return LifecycleProvider
     */
    ActivityLifecycleProvider lifecycleProvider();

    /**
     * アプリケーションコンテキスト取得
     * @return アプリケーションコンテキスト
     */
    AppContext appContext();

}
