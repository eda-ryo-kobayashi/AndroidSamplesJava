package jp.edainc.androidsamplesjava.utility;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.view.View;

import com.uber.autodispose.LifecycleScopeProvider;
import com.uber.autodispose.SingleScoper;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by kobayashiryou on 2017/10/26.
 *
 * View系のUtility
 */

public final class ViewUtility {
    private ViewUtility(){}

    /**
     * 指定した時間Viewをdisableにする
     * @param millisecond 時間
     * @param v View
     * @param provider スコープ
     */
    public static <T> void disableFor(long millisecond, View v, LifecycleScopeProvider<T> provider) {
        if(v == null) return;
        v.setEnabled(false);
        Single.timer(millisecond, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .to(new SingleScoper<>(provider))
            .subscribe(unused -> v.setEnabled(true));
    }

    public static void retrieveAttributes(Context context, @Nullable AttributeSet attrs, @StyleableRes int[] styleableRes, int defStyleAttr, Consumer<TypedArray> callback) {
        TypedArray res = context.getTheme().obtainStyledAttributes(attrs, styleableRes, defStyleAttr, 0);
        try {
            callback.accept(res);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            res.recycle();
        }
    }

    public static float dpToPx(Context context, int dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
