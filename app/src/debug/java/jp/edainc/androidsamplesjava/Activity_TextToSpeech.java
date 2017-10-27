package jp.edainc.androidsamplesjava;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import jp.edainc.androidsamplesjava.ui.activity.Activity_Base;

/**
 * Created by kobayashiryou on 2017/10/27.
 *
 * テキストを音声再生する
 */

public class Activity_TextToSpeech extends Activity_Base implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        tts = new TextToSpeech(this, this);

        findViewById(R.id.say_hello).setOnClickListener(v -> {
            String txt = ((TextView)findViewById(R.id.tts)).getText().toString();
            tts.speak(txt, TextToSpeech.QUEUE_FLUSH, null, "asj_tts");
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(tts != null) {
            tts.shutdown();
        }
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS) {
            findViewById(R.id.say_hello).setEnabled(true);
            Toast.makeText(this, "TTS準備完了", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "TTS失敗", Toast.LENGTH_SHORT).show();
        }
    }
}
