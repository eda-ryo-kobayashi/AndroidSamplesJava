package jp.edainc.androidsamplesjava.data.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * APIモジュール
 */

@Module
public class ApiModule {

    @Singleton
    @Provides
    LoginApi providesLoginApi() {
        return new LoginApiMock();
    }

    @Singleton
    @Provides
    GetRepositoriesApi providesGetRepositoriesApi() {
        return new GetRepositoriesApiMock();
    }
}
