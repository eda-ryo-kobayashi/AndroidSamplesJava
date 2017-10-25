package jp.edainc.androidsamplesjava.di;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import jp.edainc.androidsamplesjava.App;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * アプリケーションコンポーネント
 */

@Singleton
@Component(modules = {
    AppModule.class,
    AndroidInjectionModule.class,
    ActivityBuilder.class,
    AppConfigModule.class
})
interface AppComponent {

    @Component.Builder
    interface Builder {
        // 必要なインスタンスがあるなら追加
        @BindsInstance
        Builder application(App app);

        @BindsInstance
        Builder appConfig(AppConfigModule debug);

        AppComponent build();
    }

    void inject(App app);

}
