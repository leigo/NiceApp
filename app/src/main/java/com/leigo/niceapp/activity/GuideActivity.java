package com.leigo.niceapp.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.leigo.niceapp.R;
import com.leigo.niceapp.adapter.GuideAdapter;
import com.leigo.niceapp.animator.AnimatorUtils;
import com.leigo.niceapp.control.ViewPagerScroller;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2015/3/20.
 */
public class GuideActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    public static final int ANIMATE_DURATION = 600;
    private ViewPager mViewPager;
    private LinearLayout mIndicatorArea;
    private ImageView mImgIndecator1, mImgIndecator2, mImgIndecator3;
    private Button mStartBtn;

    private int mLastPosition;

    private void animateBtnIn() {
        AnimatorUtils.animViewFadeIn(mStartBtn, 200L, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mStartBtn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        AnimatorUtils.animViewFadeOut(mIndicatorArea, 200L, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIndicatorArea.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void animateBtnOut() {
        AnimatorUtils.animViewFadeIn(mIndicatorArea, 200, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mIndicatorArea.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        AnimatorUtils.animViewFadeOut(mStartBtn, 200, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mStartBtn.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.guide_activity);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mIndicatorArea = (LinearLayout) findViewById(R.id.indicator_area);
        mImgIndecator1 = (ImageView) findViewById(R.id.img_indecator1);
        mImgIndecator2 = (ImageView) findViewById(R.id.img_indecator2);
        mImgIndecator3 = (ImageView) findViewById(R.id.img_indecator3);
        mStartBtn = (Button) findViewById(R.id.btn_start);

        mStartBtn.setOnClickListener(this);

        mViewPager.setAdapter(new GuideAdapter(getFragmentManager()));
        setViewPagerScrollSpeed(mViewPager, ANIMATE_DURATION);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                float f1 = position * (position * position);
                ViewGroup viewGroup = (ViewGroup) page;
                float f2 = viewGroup.getWidth() / 2;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    viewGroup.getChildAt(i).setTranslationX(f2 * f1);
                    if (childCount < 5) {
                        f2 = (float) (3.0 * f2);
                    } else {
                        f2 = (float) (1.6 * f2);
                    }
                }
            }
        });
    }

    private void setViewPagerScrollSpeed(ViewPager viewPager, int duration) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            ViewPagerScroller scroller = new ViewPagerScroller(viewPager.getContext(), new OvershootInterpolator(0.6F));
            field.set(viewPager, scroller);
            scroller.setDuration(duration);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mLastPosition = 0;
                if (mIndicatorArea.getAlpha() == 0.0F) {
                    animateBtnOut();
                }
                mImgIndecator1.setImageResource(R.drawable.new_pagecontrol_one);
                mImgIndecator2.setImageResource(R.drawable.new_pagecontrol_point);
                mImgIndecator3.setImageResource(R.drawable.new_pagecontrol_point);
                AnimatorUtils.showBackgroundColorAnimation(mViewPager, getResources().getColor(R.color.guide_page_2_bg), getResources().getColor(R.color.guide_page_1_bg), ANIMATE_DURATION);
                break;
            case 1:
                if (mIndicatorArea.getAlpha() == 0.0F) {
                    animateBtnOut();
                }
                mImgIndecator1.setImageResource(R.drawable.new_pagecontrol_point);
                mImgIndecator2.setImageResource(R.drawable.new_pagecontrol_two);
                mImgIndecator3.setImageResource(R.drawable.new_pagecontrol_point);
                if (mLastPosition < position) {
                    AnimatorUtils.showBackgroundColorAnimation(mViewPager, getResources().getColor(R.color.guide_page_1_bg), getResources().getColor(R.color.guide_page_2_bg), ANIMATE_DURATION);
                } else {
                    AnimatorUtils.showBackgroundColorAnimation(mViewPager, getResources().getColor(R.color.guide_page_3_bg), getResources().getColor(R.color.guide_page_2_bg), ANIMATE_DURATION);
                }
                break;
            case 2:
                mLastPosition = 2;
                if (mIndicatorArea.getAlpha() > 0.0F) {
                    animateBtnIn();
                }
                AnimatorUtils.showBackgroundColorAnimation(mViewPager, getResources().getColor(R.color.guide_page_2_bg), getResources().getColor(R.color.guide_page_3_bg), ANIMATE_DURATION);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
