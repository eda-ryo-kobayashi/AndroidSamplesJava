package jp.edainc.androidsamplesjava.utility;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import jp.edainc.androidsamplesjava.R;

/**
 * Created by kobayashiryou on 2017/10/26.
 *
 * DataBindingのアダプター
 */

public final class BindingAdapters {
    private BindingAdapters() {}

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView view, int resId) {
        view.setImageResource(resId);
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView view, String url) {
        Picasso p = Picasso.with(view.getContext());
        RequestCreator rc;
        if(url == null || url.isEmpty()) {
            rc = p.load(R.drawable.ic_unknown);
        } else {
            rc = p.load(url);
        }
        rc.error(R.drawable.ic_unknown)
            .into(view);
    }

}
