package jp.edainc.androidsamplesjava.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import jp.edainc.androidsamplesjava.feature.main.Activity_Main;
import jp.edainc.androidsamplesjava.feature.main.MainModule;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * Activity生成処理
 */

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract Activity_Main contributeActivityMain();

}
