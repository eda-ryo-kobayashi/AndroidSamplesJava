package jp.edainc.androidsamplesjava.data;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import jp.edainc.androidsamplesjava.App;
import jp.edainc.androidsamplesjava.data.db.DatabaseModule;
import jp.edainc.androidsamplesjava.data.network.ApiModule;
import jp.edainc.androidsamplesjava.data.preference.PreferenceModule;
import jp.edainc.androidsamplesjava.di.NetworkModule;
import jp.edainc.androidsamplesjava.repository.UserRepository;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * データアクセス系コンポーネント
 */

@Singleton
@Component(modules = {
    NetworkModule.class,
    ApiModule.class,
    PreferenceModule.class,
    DatabaseModule.class
})
public interface DataAccessComponent {

    @Component.Builder
    interface Builder {
        // 必要なインスタンスがあるなら追加
        @BindsInstance
        Builder application(App app);

        DataAccessComponent build();
    }

    void inject(UserRepository repo);
}
