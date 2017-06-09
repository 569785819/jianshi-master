package com.zzj.jianshi.aspect;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import timber.log.Timber;

import static com.zzj.jianshi.Constants.ANIM_DURATION_SHORT;

@Aspect
public class ClickAnimApt {

    @Pointcut("execution(com.wingjay.jianshi.ui.widget.TextPointView.new(..))")
    public void whenViewSetClick() {

    }

    @Around("whenViewSetClick()")//在连接点进行方法替换
    public void doSetTouchAnim(final ProceedingJoinPoint joinPoint) {
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        final ViewGroup view = (ViewGroup) joinPoint.getTarget();
        view.setClipToPadding(false);
        view.setClipChildren(false);
        view.setOnTouchListener(new View.OnTouchListener() {
            AnimatorSet mSet;
            boolean isFirst = true;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Timber.e("ClickAnimApt ACTION_DOWN");
                        if (mSet != null) {
                            mSet.cancel();
                        }
                        if (isFirst) {
                            isFirst = false;
                            setViewOutDraw(view);
                        }
                        mSet = new AnimatorSet();
                        mSet.setDuration(ANIM_DURATION_SHORT);
                        mSet.playTogether(ObjectAnimator.ofFloat(view, "ScaleX", 1f, 1.5f),
                                ObjectAnimator.ofFloat(view, "ScaleY", 1f, 1.5f));
                        mSet.start();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        Timber.e("ClickAnimApt ACTION_UP ACTION_CANCEL");
                        if (mSet != null) {
                            mSet.cancel();
                        }
                        mSet = new AnimatorSet();
                        mSet.setDuration(ANIM_DURATION_SHORT);
                        mSet.playTogether(ObjectAnimator.ofFloat(view, "ScaleX", view.getScaleX(), 1f),
                                ObjectAnimator.ofFloat(view, "ScaleY", view.getScaleY(), 1f));
                        mSet.start();
                        break;
                    }
                }
                return false;
            }
        });
    }

    private static void setViewOutDraw(View view) {
        if (!(view.getParent() instanceof ViewGroup)) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.setClipToPadding(false);
            viewGroup.setClipChildren(false);
        }
        setViewOutDraw(viewGroup);
    }
}
