package jp.edainc.androidsamplesjava;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import io.reactivex.functions.Function;
import jp.edainc.androidsamplesjava.databinding.ActivityTestBinding;
import jp.edainc.androidsamplesjava.feature.boot.Activity_Boot;
import jp.edainc.androidsamplesjava.feature.main.Activity_Main;

/**
 * Created by kobayashiryou on 2017/09/15.
 *
 * テストアクティビティ
 * デバッグ実行時にアプリ起動時に表示する
 * デバッグのマニフェストにランチャーとして登録すれば
 * ホーム画面からこの画面を起動できる
 */

public class Activity_Test extends AppCompatActivity {

    private enum Item {

        // テスト用の挙動をここに追加していく
        BOOT("アプリ起動", a -> {
            a.finish();
            return new Intent(a, Activity_Boot.class);
        }),
        MAIN("メイン画面起動", a -> new Intent(a, Activity_Main.class)),
        TTS("テキストスピーチ画面起動", a -> new Intent(a, Activity_TextToSpeech.class)),
        SPEECH_RECOGNIZER("音声認識画面起動", a -> new Intent(a, Activity_SpeechRecognizer.class)),
        ;

        final String label;
        final Function<Activity_Test, Intent> creator;
        Item(String l, Function<Activity_Test, Intent> f) {
            label = l;
            creator = f;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_test);

        binding.list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Item.values()));
        binding.list.setOnItemClickListener((adapterView, view, i, l) -> {
            Item item = (Item)adapterView.getAdapter().getItem(i);
            if(item == null) return;
            try {
                startActivity(item.creator.apply(this));
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
