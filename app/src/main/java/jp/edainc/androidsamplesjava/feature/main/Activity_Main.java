package jp.edainc.androidsamplesjava.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import jp.edainc.androidsamplesjava.R;
import jp.edainc.androidsamplesjava.ui.activity.Activity_Base;

/**
 * Created by kobayashiryou on 2017/10/24.
 *
 * メイン画面
 */

public class Activity_Main extends Activity_Base {

    public static Intent createIntent(Context context) {
        return new Intent(context, Activity_Main.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
