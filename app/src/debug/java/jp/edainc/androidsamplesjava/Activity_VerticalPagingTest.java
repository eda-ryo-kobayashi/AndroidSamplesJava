package jp.edainc.androidsamplesjava;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.edainc.androidsamplesjava.ui.activity.Activity_Base;
import jp.edainc.androidsamplesjava.ui.view.VerticalViewPager;
import timber.log.Timber;

/**
 * Created by kobayashiryou on 2018/01/26.
 *
 * VerticalViewPagerのテスト
 */

public class Activity_VerticalPagingTest extends Activity_Base {

    private VerticalViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_paging_test);
        pager = findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if(position == 3) {
                    pager.setScrollEnabled(false);
                } else {
                    pager.setScrollEnabled(true);
                }
            }
        });
    }

    public static class Page extends Fragment {

        public static Page newInstance(int color) {
            Page f = new Page();
            Bundle args = new Bundle();
            args.putInt("color", color);
            f.setArguments(args);
            return f;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_page, container, false);
            v.setBackgroundColor(getArguments().getInt("color", 0xFF000000));
            v.findViewById(R.id.button).setOnClickListener(v1 -> {
                Timber.d("クリック");
                ((Activity_VerticalPagingTest)getActivity()).pager.setCurrentItem(0);
            });
            return v;
        }
    }

    private static class PagerAdapter extends FragmentPagerAdapter {

        private static final int[] COLORS = {
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW
        };

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Page.newInstance(COLORS[position]);
        }

        @Override
        public int getCount() {
            return COLORS.length;
        }

    }
}
