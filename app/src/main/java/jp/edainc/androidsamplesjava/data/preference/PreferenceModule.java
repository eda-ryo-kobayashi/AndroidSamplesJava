package jp.edainc.androidsamplesjava.data.preference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.edainc.androidsamplesjava.App;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * ローカルデータモジュール
 */

@Module
public class PreferenceModule {

    @Singleton
    @Provides
    UserData providesUserData(App app) {
        return new UserData(app);
    }
}
