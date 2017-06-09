package com.zzj.jianshi.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.wingjay.jianshi.R;
import com.wingjay.jianshi.ui.base.BaseActivity;
import com.zzj.jianshi.Constants;

/**
 * Created by zhuzhejun on 2017/5/19.
 */

public class ActivityClipAnimView extends FrameLayout {
    private Path mPath = new Path();

    private ViewClipProcess mViewClipProcess;

    public ActivityClipAnimView(@NonNull Context context) {
        super(context);
        init();
    }

    public ActivityClipAnimView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                startAnim();
                return false;
            }
        });
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        setId(R.id.activity_layout_trans);
    }

    private void startAnim() {
        mViewClipProcess = new ViewClipProcess(getContext(), this);
        ObjectAnimator oa = ObjectAnimator.ofFloat(mViewClipProcess, "Process", 0f, 1f);
        oa.setDuration(Constants.ANIM_DURATION_SHORT);
        oa.start();
    }

    public void dismissAnim(final BaseActivity baseActivity) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(mViewClipProcess, "Process", 1f, 0f);
        oa.setDuration(Constants.ANIM_DURATION_SHORT);
        oa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setVisibility(INVISIBLE);
                baseActivity.trueFinish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                setVisibility(INVISIBLE);
                baseActivity.trueFinish();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        oa.start();
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (mViewClipProcess.isAniming()) {
            mViewClipProcess.doClip(canvas, mPath);
        }
        if(mViewClipProcess.mProcess<=0){
            return  false;
        }
        return super.drawChild(canvas, child, drawingTime);
    }
}
