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
 * デバッグモジュール
 */

@Module
public class DebugModule {

    DebugModule(App app) {

        if(Timber.treeCount() <= 0) {
            Timber.plant(new Timber.DebugTree());
        }

        Stetho.initializeWithDefaults(app);

        Picasso.Builder pb = new Picasso.Builder(app);
        // 50MiB
        pb.downloader(new OkHttp3Downloader(app, 1024 * 1024 * 50));
        Picasso pic = pb.build();
        pic.setIndicatorsEnabled(true);
        pic.setLoggingEnabled(true);
        Picasso.setSingletonInstance(pic);

        // メインスレッドでの処理を検知
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
            .detectDiskReads()  // ディスク読み込み
            .detectDiskWrites() // ディスク書き込み
            .detectNetwork()    // 通信
            .penaltyLog()
            .build());

        // VMの処理を検知
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
            .detectActivityLeaks()          // Activity内のリーク
            .detectLeakedClosableObjects()  // closeが必要なオブジェクトの開放忘れ
            //.detectLeakedRegistrationObjects()  // BroadcastReceiverなどの解除忘れ
            .penaltyLog()
            .build());
    }

    // TODO Debug用の機能を提供する場合は下に書く

}
