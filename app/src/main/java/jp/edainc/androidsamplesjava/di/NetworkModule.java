package jp.edainc.androidsamplesjava.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.edainc.androidsamplesjava.App;
import jp.edainc.androidsamplesjava.BuildConfig;
import jp.edainc.androidsamplesjava.data.network.ServerInfo;
import jp.edainc.androidsamplesjava.utility.JsonPrettyPrintLogger;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * 通信処理モジュール
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Gson providesGson(App app) {
        return new GsonBuilder()
            .setDateFormat("yyyy-MM-dd kk:mm")
            .create();
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(App app) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new JsonPrettyPrintLogger());
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(interceptor);
        }
        return clientBuilder.build();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
            .baseUrl(ServerInfo.URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    }
}
