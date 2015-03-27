package com.leigo.niceapp.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leigo.niceapp.R;
import com.leigo.niceapp.animator.AnimatorUtils;


/**
 * Created by Administrator on 2015/3/23.
 */
public class GuidePopWindow extends PopupWindow {

    private static final String TAG = GuidePopWindow.class.getSimpleName();

    private int mBgResId;
    private Context mContext;
    private String mPopText;

    public GuidePopWindow(Context context, String text, int resId) {
        this.mContext = context;
        this.mPopText = text;
        this.mBgResId = resId;
        init();
    }

    private void init() {
        TextView textView = new TextView(mContext);
        if (mBgResId != 0) {
            textView.setBackgroundResource(mBgResId);
        }
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10.0F, displayMetrics);
        textView.setPadding(padding, padding, padding, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 17.0F, displayMetrics));
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(16.0F);
        textView.setText(mPopText);
        RelativeLayout relativeLayout = new RelativeLayout(mContext);
        relativeLayout.addView(textView);
        setContentView(relativeLayout);
        setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT, 0);
        textView.measure(0, 0);
        setHeight(textView.getMeasuredHeight() + mContext.getResources().getDimensionPixelSize(R.dimen.shake_spacing));
        setBackgroundDrawable(new BitmapDrawable());
        setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                Log.e(TAG, "pop guide dismiss~~~~~~~~~~~~~~");
            }
        });
        AnimatorUtils.animateShake(textView);
    }
}
