package com.zzj.jianshi.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Region;

import com.zzj.jianshi.utils.DisplayUtil;

/**
 * Created by zhuzhejun on 2017/5/19.
 */

public class ViewClipProcess {
    float mProcess = 0;
    ClipInfo mStart;
    int mMaxRadius;
    ActivityClipAnimView mClipAnimView;

    public ViewClipProcess(Context context, ActivityClipAnimView clipAnimView) {
        mClipAnimView = clipAnimView;
        mStart = ActivityTransControl.getStartTransInfo(context);
        calculateEndInfo(context);
        ActivityTransControl.clear();
    }

    private void calculateEndInfo(Context context) {
        int screenX = DisplayUtil.getScreenWidth(context);
        int screenY = DisplayUtil.getScreenHeight(context);
        int maxX = mStart.x > (screenX / 2) ? mStart.x : screenX - mStart.x;
        int maxY = mStart.y > (screenY / 2) ? mStart.y : screenY - mStart.y;
        mMaxRadius = maxX > maxY ? maxX : maxY;
    }

    public void setProcess(float process) {
        mProcess = process;
        mClipAnimView.invalidate();
    }

    public void doClip(Canvas canvas, Path path) {
        path.reset();
        canvas.clipPath(path);
        int radius = (int) (mStart.radius + mProcess * (mMaxRadius - mStart.radius));
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG));
        path.addCircle(mStart.x, mStart.y, radius, Path.Direction.CCW);
        canvas.clipPath(path, Region.Op.REPLACE);
    }

    public boolean isAniming() {
        if (mProcess > 0f && mProcess < 1f) {
            return true;
        }
        return false;
    }
}
