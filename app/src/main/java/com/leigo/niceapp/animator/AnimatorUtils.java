package com.leigo.niceapp.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.leigo.niceapp.R;

/**
 * Created by Administrator on 2015/3/20.
 */
public class AnimatorUtils {

    public static Animator animViewFadeIn(View view) {
        return animViewFadeIn(view, 200L, null);
    }

    public static Animator animViewFadeIn(View view, long duration, Animator.AnimatorListener animatorListener) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0F, 1.0F});
        objectAnimator.setDuration(duration);
        if (animatorListener != null) {
            objectAnimator.addListener(animatorListener);
        }
        objectAnimator.start();
        return objectAnimator;
    }

    public static Animator animViewFadeOut(View view) {
        return animViewFadeOut(view, 200L, null);
    }

    public static Animator animViewFadeOut(View view, long duration, Animator.AnimatorListener animatorListener) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0F, 0.0F});
        objectAnimator.setDuration(duration);
        if (animatorListener != null) {
            objectAnimator.addListener(animatorListener);
        }
        objectAnimator.start();
        return objectAnimator;
    }

    public static void animateHeartbeat(final View view) {
        ValueAnimator valueAnimator1 = ValueAnimator.ofFloat(new float[]{1.0F, 1.5F});
        valueAnimator1.setInterpolator(new OvershootInterpolator());
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (Float) animation.getAnimatedValue();
                view.setScaleX(f);
                view.setScaleY(f);
            }
        });
        ValueAnimator valueAnimator2 = ValueAnimator.ofFloat(new float[]{1.5F, 1.0F});
        valueAnimator2.setInterpolator(new DecelerateInterpolator());
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (Float) animation.getAnimatedValue();
                view.setScaleX(f);
                view.setScaleY(f);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(valueAnimator2).after(valueAnimator1);
        animatorSet.setDuration(200L);
        animatorSet.start();
    }

    public static void animateShake(View view) {
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = view.getY();
        arrayOfFloat[1] = (view.getY() + view.getContext().getResources().getDimensionPixelSize(R.dimen.shake_spacing));
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(view, "Y", arrayOfFloat);
        localObjectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        localObjectAnimator.setRepeatMode(2);
        localObjectAnimator.setRepeatCount(-1);
        localObjectAnimator.setDuration(500L);
        localObjectAnimator.start();
    }

    public static void cancelAnimation(View view) {
        Animation animation = view.getAnimation();
        if (animation != null) {
            animation.cancel();
            view.clearAnimation();
        }
    }

    public static ScaleAnimation getIconScaleAnimation(float fromX, float toX, float fromY, float toY,
                                                       float pivotX, float pivotY) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromX, toX, fromY, toY, pivotX, pivotY);
        scaleAnimation.setInterpolator(new DecelerateInterpolator());
        scaleAnimation.setDuration(200L);
        return scaleAnimation;
    }

    public static TranslateAnimation getIconTranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        TranslateAnimation translateAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        translateAnimation.setDuration(200L);
        return translateAnimation;
    }

    public static Animator moveScrollViewToX(View view, int paramInt1, long duration, int startDelay, boolean paramBoolean) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(view, "scrollX", new int[]{paramInt1});
        objectAnimator.setDuration(duration);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.setStartDelay(startDelay);
        if (paramBoolean) {
            objectAnimator.start();
        }
        return objectAnimator;
    }

    public static void showBackgroundColorAnimation(View view, int from, int to, int duration) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(view, "backgroundColor", from, to);
        objectAnimator.setDuration(duration);
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.start();
    }

    public static Animator showUpAndDownBounce(View view, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) {
        float[] arrayOfFloat = new float[1];
        arrayOfFloat[0] = paramInt1;
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(view, "translationY", arrayOfFloat);
        if (paramBoolean2) {
            localObjectAnimator.setInterpolator(new OvershootInterpolator());
        }
        localObjectAnimator.setDuration(paramInt2);
        if (paramBoolean1) {
            localObjectAnimator.start();
        }
        return localObjectAnimator;
    }
}
