package jp.edainc.androidsamplesjava.feature.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.ArrayList;

import jp.edainc.androidsamplesjava.R;
import jp.edainc.androidsamplesjava.databinding.ActivityMainBinding;
import jp.edainc.androidsamplesjava.feature.detail.Activity_RepositoryDetail;
import jp.edainc.androidsamplesjava.model.Repository;
import jp.edainc.androidsamplesjava.ui.activity.Activity_Base;
import jp.edainc.androidsamplesjava.utility.ViewUtility;
import jp.edainc.androidsamplesjava.viewmodel.RepositoryViewModel;

/**
 * Created by kobayashiryou on 2017/10/24.
 *
 * メイン画面
 */

public class Activity_Main extends Activity_Base {

    private ActivityMainBinding binding;
    private RepositoryViewModel repoVM;
    private RepositoryListAdapter adapter;

    public static Intent createIntent(Context context) {
        return new Intent(context, Activity_Main.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.toolbar.setTitle("e.d.a");
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorIcons));

        adapter = new RepositoryListAdapter(this);
        adapter.setClickItemListener(item -> {
            ViewUtility.disableFor(1000, binding.content.list, lifecycleProvider().onStart());
            startActivity(Activity_RepositoryDetail.createIntent(this, item.repo));
        });
        binding.content.list.setAdapter(adapter);
        binding.content.list.setNestedScrollingEnabled(false);

        repoVM = new RepositoryViewModel(appContext(), lifecycleProvider().onCreate());
        repoVM.getRepositories(
            repositories -> {
                final ArrayList<RepositoryListItem> items = new ArrayList<>();
                for (Repository repo: repositories) {
                    items.add(new RepositoryListItem(repo));
                }
                adapter.setItems(items);
                binding.content.loading.setVisibility(View.GONE);
            },
            null);
    }
}
