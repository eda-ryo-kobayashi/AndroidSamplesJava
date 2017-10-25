package jp.edainc.androidsamplesjava.di;

import android.os.StrictMode;

import com.facebook.stetho.Stetho;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import jp.edainc.androidsamplesjava.App;
import timber.log.Timber;

/**
 * Created by kobayashiryou on 2017/10/25.
 *
 * アプリケーション設定モジュール
 */

@Module
public class AppConfigModule {

    DebugModule(App app) {

        Picasso.Builder pb = new Picasso.Builder(app);
        pb.downloader(new OkHttp3Downloader(app, 1024 * 1024 * 50));
        Picasso.setSingletonInstance(pb.build());

    }

}
