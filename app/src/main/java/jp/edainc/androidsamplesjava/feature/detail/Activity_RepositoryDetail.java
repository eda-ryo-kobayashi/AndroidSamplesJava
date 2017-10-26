package jp.edainc.androidsamplesjava.feature.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;

import com.jakewharton.rxbinding2.view.RxView;
import com.uber.autodispose.ObservableScoper;
import com.uber.autodispose.SingleScoper;

import jp.edainc.androidsamplesjava.R;
import jp.edainc.androidsamplesjava.databinding.ActivityRepositoryDetailBinding;
import jp.edainc.androidsamplesjava.model.Repository;
import jp.edainc.androidsamplesjava.ui.activity.Activity_Base;

/**
 * Created by kobayashiryou on 2017/10/26.
 *
 * リポジトリ詳細画面
 */

public class Activity_RepositoryDetail extends Activity_Base {

    private static final String KEY_REPOSITORY = "repository";

    private ActivityRepositoryDetailBinding binding;
    private Repository repo;

    public static Intent createIntent(Context context, Repository repo) {
        return new Intent(context, Activity_RepositoryDetail.class)
            .putExtra(KEY_REPOSITORY, repo);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_repository_detail);

        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorIcons));

        repo = (Repository)getIntent().getSerializableExtra(KEY_REPOSITORY);
        binding.setRepository(repo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RxView.clicks(binding.content.showRepositoryPage)
            .doOnNext(unused -> {
                CustomTabsIntent intent = new CustomTabsIntent.Builder()
                    .setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
                    .build();
                intent.launchUrl(this, Uri.parse(repo.url));
            })
            .to(new ObservableScoper<>(lifecycleProvider().onResume()))
            .subscribe();
    }
}
