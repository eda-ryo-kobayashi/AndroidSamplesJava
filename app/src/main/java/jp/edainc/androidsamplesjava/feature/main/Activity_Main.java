package jp.edainc.androidsamplesjava.feature.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import jp.edainc.androidsamplesjava.R;
import jp.edainc.androidsamplesjava.databinding.ActivityMainBinding;
import jp.edainc.androidsamplesjava.ui.activity.Activity_Base;

/**
 * Created by kobayashiryou on 2017/10/24.
 *
 * メイン画面
 */

public class Activity_Main extends Activity_Base {

    private ActivityMainBinding binding;

    public static Intent createIntent(Context context) {
        return new Intent(context, Activity_Main.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.toolbar.setTitle("e.d.a");
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorIcons));
    }
}
