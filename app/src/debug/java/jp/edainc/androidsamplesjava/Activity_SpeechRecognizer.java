package jp.edainc.androidsamplesjava;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;

import java.util.List;

import jp.edainc.androidsamplesjava.ui.activity.Activity_Base;
import timber.log.Timber;

/**
 * Created by kobayashiryou on 2017/10/27.
 *
 * 音声認識テスト
 */

public class Activity_SpeechRecognizer extends Activity_Base {

    private boolean isListening = false;
    private SpeechRecognizer sr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_recognizer);

        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                Timber.d("onReadyForSpeech");
            }

            @Override
            public void onBeginningOfSpeech() {
                Timber.d("onBeginningOfSpeech");
            }

            @Override
            public void onRmsChanged(float rmsdB) {
                Timber.d("onRmsChanged");
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                Timber.d("onBufferReceived");
            }

            @Override
            public void onEndOfSpeech() {
                Timber.d("onEndOfSpeech");
            }

            @Override
            public void onError(int error) {
                Timber.d("onError : %d", error);
            }

            @Override
            public void onResults(Bundle results) {
                Timber.d("onResults");
                List<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if(data == null) return;
                for (String d :
                    data) {
                    Timber.d(d);
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                Timber.d("onPartialResults");
                List<String> data = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if(data == null) return;
                for (String d :
                    data) {
                    Timber.d(d);
                }
            }

            @Override
            public void onEvent(int eventType, Bundle params) {
                Timber.d("onEvent : %d", eventType);
            }
        });

        findViewById(R.id.recognize).setOnClickListener(v -> {
            if(isListening) {
                sr.stopListening();
            } else {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
                sr.startListening(intent);
            }
            isListening = !isListening;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sr.destroy();
    }
}
