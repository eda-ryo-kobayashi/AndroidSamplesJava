package jp.edainc.androidsamplesjava.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by kobayashiryou on 2018/01/26.
 *
 * 縦方向にスクロールするViewPager
 * 参考 : https://stackoverflow.com/questions/13477820/android-vertical-viewpager
 */

public class VerticalViewPager extends ViewPager {

    private boolean scrollEnabled = true;

    public VerticalViewPager(Context context) {
        this(context, null);
    }
    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        setPageTransformer(true, new VerticalPageTransformer());

        // 縦スクロールで横にover scrollが描画されてしまうので、描画しないように設定
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    public void setScrollEnabled(boolean enabled) {
        scrollEnabled = enabled;
    }

    /**
     * MotionEventのX、Yを入れ替える
     * @param ev motion event
     * @return motion event
     */
    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();
        if(width == 0 || height == 0) return ev;
        // x > y, y > x変換
        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;
        ev.setLocation(newX, newY);
        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(scrollEnabled) {
            boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
            // 子ビューが使用するので、x,yを元に戻しておく
            swapXY(ev);
            return intercepted;
        } else {
            // このビューにはイベントが伝播するように設定
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(scrollEnabled) {
            return super.onTouchEvent(swapXY(ev));
        } else {
            return true;
        }
    }

    // ページ内のViewの座標変換
    private static class VerticalPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {

            if(position < -1) {
                // -1より位置が前の時は透明
                page.setAlpha(0);

            } else if(position <= 1) {
                // 位置が-1から1の時は描画する
                page.setAlpha(1);

                // X座標デフォルトのスライドアニメーション
                page.setTranslationX(page.getWidth() *  -position);

                // Y座標アニメーション
                page.setTranslationY(page.getHeight() * position);

            } else {
                // 位置が1より大きかったら透明
                page.setAlpha(0);

            }
        }
    }
}
