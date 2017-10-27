package jp.edainc.androidsamplesjava;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jp.edainc.androidsamplesjava.ui.activity.Activity_Base;
import timber.log.Timber;

/**
 * Created by kobayashiryou on 2017/10/27.
 *
 * 音声認識テスト
 */

public class Activity_SpeechRecognizer extends Activity_Base {

    private static final SparseArray<String> ERRORS = new SparseArray<>();

    private boolean isListening = false;
    private SpeechRecognizer sr;

    private final List<String> resultData = new ArrayList<>();

    static {
        ERRORS.append(SpeechRecognizer.ERROR_NETWORK_TIMEOUT, "ERROR_NETWORK_TIMEOUT");
        ERRORS.append(SpeechRecognizer.ERROR_NETWORK, "ERROR_NETWORK");
        ERRORS.append(SpeechRecognizer.ERROR_AUDIO, "ERROR_AUDIO");
        ERRORS.append(SpeechRecognizer.ERROR_SERVER, "ERROR_SERVER");
        ERRORS.append(SpeechRecognizer.ERROR_CLIENT, "ERROR_CLIENT");
        ERRORS.append(SpeechRecognizer.ERROR_SPEECH_TIMEOUT, "ERROR_SPEECH_TIMEOUT");
        ERRORS.append(SpeechRecognizer.ERROR_NO_MATCH, "ERROR_NO_MATCH");
        ERRORS.append(SpeechRecognizer.ERROR_RECOGNIZER_BUSY, "ERROR_RECOGNIZER_BUSY");
        ERRORS.append(SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS, "ERROR_INSUFFICIENT_PERMISSIONS");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_recognizer);

        requestRecordPermission(
            () -> {
                final Button recognize = findViewById(R.id.recognize);
                recognize.setOnClickListener(v -> {
                    if(isListening) {
                        sr.stopListening();
                        endRecognition();
                    } else {
                        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
                        sr.startListening(intent);
                        recognize.setText("認識終了");
                        isListening = true;
                    }
                });
            },
            this::finish
        );
        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                Timber.d("onReadyForSpeech");
                resultData.clear();
            }

            @Override
            public void onBeginningOfSpeech() {
                Timber.d("onBeginningOfSpeech");
            }

            int count = 0;
            @Override
            public void onRmsChanged(float rmsdB) {
                if(count == 0) {
                    Timber.d("onRmsChanged : %f", rmsdB);
                }
                count++;
                count %= 5;
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
                Timber.e("onError : %s", ERRORS.get(error));
                endRecognition();
            }

            @Override
            public void onResults(Bundle results) {
                Timber.d("onResults");
                List<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if(data == null) return;
                resultData.addAll(data);
                StringBuilder sb = new StringBuilder();
                for (String d :
                    data) {
                    Timber.d(d);
                    sb.append(d).append("/");
                }
                ((TextView)findViewById(R.id.result)).setText(sb.toString());
                // TODO ここで結果処理する
                endRecognition();
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sr.destroy();
    }

    private void endRecognition() {
        final Button recognize = findViewById(R.id.recognize);
        recognize.setText("認識スタート");
        isListening = false;
    }
}
