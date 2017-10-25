package jp.edainc.androidsamplesjava.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.edainc.androidsamplesjava.App;
import jp.edainc.androidsamplesjava.data.DaggerDataAccessComponent;
import jp.edainc.androidsamplesjava.data.DataAccessComponent;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * アプリケーションモジュール
 */

@Module
class AppModule {

    @Provides
    @Singleton
    DataAccessComponent providesDataAccessComponent(App app) {
        return DaggerDataAccessComponent
            .builder()
            .application(app)
            .build();
    }

}
