package jp.edainc.androidsamplesjava.data.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * APIモジュール
 */

@Module
public class ApiModule {

    @Singleton
    @Provides
    LoginApi providesLoginApi(Retrofit r) {
        return r.create(LoginApi.class);
    }

    @Singleton
    @Provides
    GetRepositoriesApi providesGetRepositoriesApi(Retrofit r) {
        return r.create(GetRepositoriesApi.class);
    }
}
